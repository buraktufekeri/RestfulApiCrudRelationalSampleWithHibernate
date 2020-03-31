package com.api.services;

import com.api.entity.DomainFeature;
import com.api.exceptions.DomainFeatureNotFoundException;
import com.api.exceptions.DomainNotFoundException;
import com.api.repository.DomainFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainFeatureService {

    private final DomainFeatureRepository domainFeatureRepository;

    @Autowired
    public DomainFeatureService(DomainFeatureRepository domainFeatureRepository) {
        this.domainFeatureRepository = domainFeatureRepository;
    }

    @Autowired
    DomainService domainService;

    public DomainFeature saveDomainFeature(DomainFeature domainFeature){
        return domainFeatureRepository.save(domainFeature);
    }

    public List<DomainFeature> saveDomainFeatures(List<DomainFeature> domainFeatures){
        return domainFeatureRepository.saveAll(domainFeatures);
    }

    public List<DomainFeature> getDomainFeatures() {
        return domainFeatureRepository.findAll();
    }

    public DomainFeature getDomainFeatureById(long id) throws DomainFeatureNotFoundException {
        Optional<DomainFeature> domainFeatures = domainFeatureRepository.findById(id);
        if (!domainFeatures.isPresent())
            throw new DomainFeatureNotFoundException("DomainFeature Not Found in DomainFeature Repository DomainFeatureId:" + id);

        return domainFeatures.get();
    }

    public DomainFeature updateDomainFeature(long id, DomainFeature domainFeature) throws DomainFeatureNotFoundException, DomainNotFoundException {
        Optional<DomainFeature> optionalDomainFeature = domainFeatureRepository.findById(id);
        if (!optionalDomainFeature.isPresent())
            throw new DomainFeatureNotFoundException("DomainFeature Not Found in DomainFeature Repository DomainFeatureId:" + id + ", Provide The Correct DomainFeatureId!");

        domainService.getDomainById(domainFeature.getDomain().getId());
        optionalDomainFeature.get().setProductGroup(domainFeature.getProductGroup());
        optionalDomainFeature.get().setDomainArchitect(domainFeature.getDomainArchitect());
        optionalDomainFeature.get().setTestAutomationAgents(domainFeature.getTestAutomationAgents());
        optionalDomainFeature.get().setBankFunctionalTestResource(domainFeature.getBankFunctionalTestResource());
        optionalDomainFeature.get().setBankAutomationTestResource(domainFeature.getBankAutomationTestResource());
        optionalDomainFeature.get().setSofttechTestAutomationFriendly(domainFeature.getSofttechTestAutomationFriendly());
        optionalDomainFeature.get().setLocation(domainFeature.getLocation());
        optionalDomainFeature.get().setScopeAnalysisStudy(domainFeature.getScopeAnalysisStudy());
        optionalDomainFeature.get().setNotes(domainFeature.getNotes());
        optionalDomainFeature.get().setDomain(domainFeature.getDomain());

        return domainFeatureRepository.save(optionalDomainFeature.get());
    }

    public String deleteDomainFeature(long id) throws DomainFeatureNotFoundException {
        Optional<DomainFeature> optionalDomainFeature = domainFeatureRepository.findById(id);
        if (!optionalDomainFeature.isPresent())
            throw new DomainFeatureNotFoundException("DomainFeature Not Found in DomainFeature Repository DomainFeatureId:" + id + ", Provide The Correct DomainFeatureId!");

        domainFeatureRepository.deleteById(id);

        return "DomainFeature Removed! DomainFeature ID:" + id;
    }
}
