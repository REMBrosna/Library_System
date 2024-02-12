package com.guud.company.library.master.controller;


import com.guud.company.library.master.service.PediMstMiscService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/pedi/mst")
@CrossOrigin
@RestController
public class PediMasterListController {
    // Static Attributes
    ////////////////////
    private static Logger log = Logger.getLogger(PediMasterListController.class);

    @Autowired
    @Qualifier("pediMstMiscService")
    private PediMstMiscService pediMstMiscService;

    @RequestMapping(value = "/numberProvince", method = RequestMethod.GET)
    public ResponseEntity<Object> listNumberProvince() throws Exception {
        log.info("listNumberProvince");
        return pediMstMiscService.getNumberProvince();
    }

    @RequestMapping(value = "/numberApplication", method = RequestMethod.GET)
    public ResponseEntity<Object> listNumberEngine() throws Exception {
        log.info("listNumberApplication");
        return pediMstMiscService.getNumberApplication();
    }

    @RequestMapping(value = "/numberUsers", method = RequestMethod.GET)
    public ResponseEntity<Object> listNumberUsers() throws Exception {
        log.info("listNumberUsers");
        return pediMstMiscService.getNumberUsers();
    }

    @RequestMapping(value = "/numberCustomers", method = RequestMethod.GET)
    public ResponseEntity<Object> getNumberCustomers() throws Exception {
        log.info("listNumberCustomers");
        return pediMstMiscService.getNumberCustomers();
    }
    @RequestMapping(value = "/numberBooks", method = RequestMethod.GET)
    public ResponseEntity<Object> listNumberBooks() throws Exception {
        log.info("listNumberBooks");
        return pediMstMiscService.getNumberBooks();
    }

    @RequestMapping(value = "/numberItems/{applicationId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getNumberItems(@PathVariable String applicationId) throws Exception {
        log.info("listNumberBooks");
        return pediMstMiscService.getNumberItems(applicationId);
    }

}
