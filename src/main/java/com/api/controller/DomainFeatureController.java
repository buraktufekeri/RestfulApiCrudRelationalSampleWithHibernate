package com.api.controller;

import com.api.entity.DomainFeature;
import com.api.exceptions.DomainFeatureNotFoundException;
import com.api.exceptions.DomainNotFoundException;
import com.api.services.DomainFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/domainFeature")
public class DomainFeatureController {

    private final DomainFeatureService domainFeatureService;

    @Autowired
    public DomainFeatureController(DomainFeatureService domainFeatureService) {
        this.domainFeatureService = domainFeatureService;
    }

    @RequestMapping(value = "/addDomainFeature", method = RequestMethod.POST, headers = "Accept=application/json")
    public DomainFeature addDomainFeature(@RequestBody DomainFeature domainFeature) {
        return domainFeatureService.saveDomainFeature(domainFeature);
    }

    @RequestMapping(value = "/addDomainFeatures", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<DomainFeature> addDomainFeatures(@RequestBody List<DomainFeature> domainFeatures){
        return domainFeatureService.saveDomainFeatures(domainFeatures);
    }

    @RequestMapping(value = "/findAllDomainFeatures", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<DomainFeature> findAllDomainFeatures(){
        return domainFeatureService.getDomainFeatures();
    }

    @RequestMapping(value = "/findDomainFeatureById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public DomainFeature findDomainFeatureById(@PathVariable("id") long id){
        try {
            return domainFeatureService.getDomainFeatureById(id);
        } catch (DomainFeatureNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/updateDomainFeature/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public DomainFeature updateDomainFeature(@PathVariable("id") long id, @RequestBody DomainFeature domainFeature){
        try {
            return domainFeatureService.updateDomainFeature(id, domainFeature);
        } catch (DomainFeatureNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (DomainNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDomainFeature/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteDomainFeature(@PathVariable("id") long id){
        try {
            return domainFeatureService.deleteDomainFeature(id);
        } catch (DomainFeatureNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
