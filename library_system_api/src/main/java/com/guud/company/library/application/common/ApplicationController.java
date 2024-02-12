package com.guud.company.library.application.common;

import com.guud.company.library.application.borrow.BorrowService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequestMapping(value = "/api/v1/library/application")
@CrossOrigin
@RestController
public class ApplicationController extends AbstractApplicationController {
    @Autowired
    private BorrowService service;

    // Static Attributes
    ////////////////////
    private static final Logger log = Logger.getLogger(ApplicationController.class);

    @RequestMapping(value = "/{appType}/new/{parentId}", method = RequestMethod.POST)
    public ResponseEntity<Object> newApplication(@PathVariable String appType, @PathVariable String parentId) {
        log.debug("newApp");
        return super.newApp(appType, parentId);
    }

    @RequestMapping(value = "/{appType}", method = RequestMethod.POST)
    public ResponseEntity<Object> createApp(@PathVariable String appType, @RequestBody String object) {
        log.debug("newApp");
        return super.createApp(appType, object);
    }

    @RequestMapping(value = "/{appType}/{appId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getApp(@PathVariable String appType, @PathVariable String appId) {
        log.debug("getApp");
        return super.getApp(appType, appId);
    }

    @RequestMapping(value = "/{appType}/list", method = RequestMethod.GET)
    public ResponseEntity<Object> getApps(@PathVariable String appType, @RequestParam Map<String, String> params) {
        log.debug("getApps");
        return super.getApps(appType, params);
    }

    @RequestMapping(value = "/{appType}/{appId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateApp(@PathVariable String appType, @PathVariable String appId,
                                            @RequestBody String object) {
        log.debug("updateApp");
        return super.updateApp(appType, appId, object);
    }

    @RequestMapping(value = "/{appType}/{appId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteApp(@PathVariable String appType, @PathVariable String appId) {
        log.debug("updateApp");
        return super.deleteApp(appType, appId);
    }

    @RequestMapping(value = "/confirm/{appType}", method = RequestMethod.POST)
    public ResponseEntity<Object> confirmApp(@PathVariable String appType, @RequestBody String object) {
        return super.confirmApp(appType, object);
    }
}
