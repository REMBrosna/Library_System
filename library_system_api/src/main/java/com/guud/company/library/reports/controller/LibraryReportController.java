package com.guud.company.library.reports.controller;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.configuration.principal.AccountPrincipalService;
import com.guud.company.library.infrastructure.api.ApiResponse;
import com.guud.company.library.reports.payload.BookRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@RequestMapping(value = "/api/v1/library/report")
@CrossOrigin
@RestController
@AllArgsConstructor
@Slf4j
public class LibraryReportController extends AbstractLibraryReportController {

    private final AccountPrincipalService principalService;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public ResponseEntity<Object> exportExcel(@RequestBody BookRequest filter) {

        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {
            AppUser accountPrincipal = principalService.getAccountPrincipal();
            if (Objects.isNull(accountPrincipal)) {
                throw new EntityNotFoundException(" AccountPrincipal is null");
            }
            return super.exportExcel(filter);
        } catch (Exception ex) {
            log.error("exportExcel error", ex);
            serviceStatus.setMessage(ex.getMessage());
            serviceStatus.setCode(ApiResponse.BAD_REQUEST);
            return new ResponseEntity<>(serviceStatus, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public ResponseEntity<Object> exportPdf(@RequestBody BookRequest filter) {

        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {
            AppUser accountPrincipal = principalService.getAccountPrincipal();
            if (Objects.isNull(accountPrincipal)) {
                throw new EntityNotFoundException(" AccountPrincipal is null");
            }
            return super.exportPdf(filter);
        } catch (Exception ex) {
            log.error("exportPdf error", ex);
            serviceStatus.setMessage(ex.getMessage());
            serviceStatus.setCode(ApiResponse.BAD_REQUEST);
            return new ResponseEntity<>(serviceStatus, HttpStatus.BAD_REQUEST);
        }
    }
}
