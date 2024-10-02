package bts.sio.api.controller;

import bts.sio.api.model.Site;
import bts.sio.api.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class SiteController {


    @Autowired
    private SiteService siteService;

    /**
     * Create - Add a new site
     * @param site An object site
     * @return The site object saved
     */
    @PostMapping("/site")
    public Site createSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }


    /**
     * Read - Get one site
     * @param id The id of the site
     * @return An site object full filled
     */
    @GetMapping("/site/{id}")
    public Site getSite(@PathVariable("id") final Long id) {
        Optional<Site> site = siteService.getSite(id);
        if(site.isPresent()) {
            return site.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all site
     * @return - An Iterable object of site full filled
     */
    @GetMapping("/sites")
    public Iterable<Site> getSites() {
        return siteService.getSites();
    }

    /**
     * Update - Update an existing site
     * @param id - The id of the site to update
     * @param site - The site object updated
     * @return
     */
    @PutMapping("/site/{id}")
    public Site updateSite(@PathVariable("id") final Long id, @RequestBody Site site) {
        Optional<Site> e = siteService.getSite(id);
        if(e.isPresent()) {
            Site currentSite = e.get();

            String nom = site.getNom();
            if(nom != null) {
                currentSite.setNom(nom);
            }
            String rue = site.getRue();
            if(rue != null) {
                currentSite.setRue(rue);
            }
           String codepostal = site.getCodepostal();
            if(codepostal != null) {
                currentSite.setCodepostal(codepostal);
            }

            siteService.saveSite(currentSite);
            return currentSite;
        } else {
            return null;
        }
    }


    /**
     * Delete - Delete an site
     * @param id - The id of the site to delete
     */
    @DeleteMapping("/site/{id}")
    public void deleteSite(@PathVariable("id") final Long id) {
        siteService.deleteSite(id);
    }
}
