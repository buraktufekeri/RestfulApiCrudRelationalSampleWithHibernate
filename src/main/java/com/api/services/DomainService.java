package com.api.services;

import com.api.entity.Domain;
import com.api.entity.DomainFeature;
import com.api.exceptions.DomainExistsException;
import com.api.exceptions.DomainNotFoundException;
import com.api.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomainService {

    private final DomainRepository domainRepository;

    @Autowired
    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Autowired
    DomainFeatureService domainFeatureService;

    public Domain saveDomain(Domain domain) throws DomainExistsException {
        Domain existingDomain = domainRepository.findByName(domain.getName());
        if (existingDomain != null)
            throw new DomainExistsException("Domain Already Exists in Domain Repository DomainName:" + domain.getName());

        return domainRepository.save(domain);
    }

    public List<Domain> saveDomains(List<Domain> domains) throws DomainExistsException {
        List<Domain> existingDomainList = domainRepository.findAll();
        for (int i = 0; i < domains.size()-1; i++)
            if (domains.get(i).getName().equals(domains.get(i+1).getName()))
                throw new DomainExistsException("Domain Names Cannot Be The Same! DomainName:" + domains.get(i).getName());

        for (Domain paramDomain : domains)
            for (Domain existingDomain : existingDomainList)
                if (paramDomain.getName().equals(existingDomain.getName()))
                    throw new DomainExistsException("Domain Already Exists in Domain Repository DomainName:" + paramDomain.getName());

        return domainRepository.saveAll(domains);
    }

    public List<Domain> getDomains(){
        return domainRepository.findAll();
    }

    public Domain getDomainById(long id) throws DomainNotFoundException {
        Optional<Domain> domain = domainRepository.findById(id);
        if (!domain.isPresent())
            throw new DomainNotFoundException("Domain Not Found in Domain Repository DomainId:" + id);

        return domain.get();
    }

    public Domain getDomainByName(String name){
        return domainRepository.findByName(name);
    }

    public Domain updateDomain(long id, Domain domain) throws DomainNotFoundException {
        Optional<Domain> optionalDomain = domainRepository.findById(id);
        if (!optionalDomain.isPresent())
            throw new DomainNotFoundException("Domain Not Found in Domain Repository DomainId:" + id + ", Provide The Correct DomainId!");

        optionalDomain.get().setName(domain.getName());

        return domainRepository.save(optionalDomain.get());
    }

    public String deleteDomain(long id) throws DomainNotFoundException {
        Optional<Domain> domain = domainRepository.findById(id);
        if (!domain.isPresent())
            throw new DomainNotFoundException("Domain Not Found in Domain Repository DomainId:" + id + ", Provide The Correct DomainId!");
        else if (getAllDomainFeaturesByDomainId(id).get(id).size() != 0)
            throw new DomainNotFoundException("DomainFeature With Domain Id Are Available! DomainId:" + id);

        domainRepository.deleteById(id);

        return "Domain Removed! Domain ID:" + id;
    }

    public Map<Long, List<DomainFeature>> getAllDomainFeaturesByDomainId(long id){
        List<DomainFeature> domainFeaturesByDomainIdList = new ArrayList<>();
        Map<Long, List<DomainFeature>> domainFeaturesByDomainIdMap = new HashMap<>();

        for (DomainFeature domainFeature : domainFeatureService.getDomainFeatures())
            if (domainFeature.getDomain().getId() == id)
                domainFeaturesByDomainIdList.add(domainFeature);

        domainFeaturesByDomainIdMap.put(id, domainFeaturesByDomainIdList);

        return domainFeaturesByDomainIdMap;
    }

    public Map<Long, List<DomainFeature>> getAllDomainFeaturesByDomain(){
        List<DomainFeature> domainFeaturesByDomainList = new ArrayList<>();;
        Map<Long, List<DomainFeature>> domainFeaturesByDomainMap = new HashMap<>();

        for (int i = 0; i < getDomains().size(); i++){
            for (int j = 0; j < domainFeatureService.getDomainFeatures().size(); j++){
                if (getDomains().get(i).getId().equals(domainFeatureService.getDomainFeatures().get(j).getDomain().getId())){
                    domainFeaturesByDomainList.add(domainFeatureService.getDomainFeatures().get(j));
                    domainFeaturesByDomainMap.put(getDomains().get(i).getId(), domainFeaturesByDomainList);
                }
            }
            domainFeaturesByDomainList = new ArrayList<>();
        }

        return domainFeaturesByDomainMap;
    }
}
