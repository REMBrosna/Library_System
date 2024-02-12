package com.guud.company.library.application.borrow.controller;

import com.guud.company.library.application.borrow.BorrowService;
import com.guud.company.library.application.returns.model.TReturnForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/api/v1/application")
@CrossOrigin
@RestController
public class ApplicationBorrowController {

    @Autowired
    private BorrowService borrowService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Object> getReturn() throws Exception {
        try {
            List<TReturnForm> tReturnFormList = borrowService.findAllReturn();
            if (tReturnFormList == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(borrowService.findAllReturn(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "return/{appId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReturn(@PathVariable String appId) throws Exception {
        try {
            TReturnForm tReturnFormList = borrowService.findReturnFormByApplicationId(appId);
            if (tReturnFormList == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tReturnFormList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
