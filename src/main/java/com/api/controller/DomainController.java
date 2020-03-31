package com.api.controller;

import com.api.entity.Domain;
import com.api.entity.DomainFeature;
import com.api.exceptions.DomainExistsException;
import com.api.exceptions.DomainNotFoundException;
import com.api.services.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/domain")
public class DomainController {

    private final DomainService domainService;

    @Autowired
    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping(value = "/addDomain", method = RequestMethod.POST, headers = "Accept=application/json")
    public Domain addDomain(@RequestBody Domain domain){
        try {
            return domainService.saveDomain(domain);
        } catch (DomainExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/addDomainSetLocationHeader", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Void> addDomainSetLocationHeader(@RequestBody Domain domain, UriComponentsBuilder uriComponentsBuilder){
        try {
            domainService.saveDomain(domain);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uriComponentsBuilder.path("addDomainSetLocationHeader/{id}").buildAndExpand(domain.getId()).toUri());

            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        } catch (DomainExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/addDomains", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Domain> addDomains(@RequestBody List<Domain> domains){
        try {
            return domainService.saveDomains(domains);
        } catch (DomainExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findAllDomains", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Domain> findAllDomains(){
        return domainService.getDomains();
    }

    @RequestMapping(value = "/findDomainById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Domain findDomainById(@PathVariable("id") long id){
        try {
            return domainService.getDomainById(id);
        } catch (DomainNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findDomainByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Domain findDomainByName(@PathVariable("name") String name){
        return domainService.getDomainByName(name);
    }

    @RequestMapping(value = "/updateDomain/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Domain updateDomain(@PathVariable("id") long id, @RequestBody Domain domain){
        try {
            return domainService.updateDomain(id, domain);
        } catch (DomainNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDomain/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteDomain(@PathVariable("id") long id){
        try {
            return domainService.deleteDomain(id);
        } catch (DomainNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findAllDomainFeaturesByDomainId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Map<Long, List<DomainFeature>> findAllDomainFeaturesByDomainId(@PathVariable("id") long id){
        return domainService.getAllDomainFeaturesByDomainId(id);
    }

    @RequestMapping(value = "/findAllDomainFeaturesByDomain", method = RequestMethod.GET, headers = "Accept=application/json")
    public Map<Long, List<DomainFeature>> findAllDomainFeaturesByDomain(){
        return domainService.getAllDomainFeaturesByDomain();
    }
}
