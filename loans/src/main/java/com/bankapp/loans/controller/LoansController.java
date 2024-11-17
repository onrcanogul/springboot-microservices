package com.bankapp.loans.controller;

import com.bankapp.loans.dto.LoansContactInfoDto;
import com.bankapp.loans.dto.LoansDto;
import com.bankapp.loans.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoansController {
    @Autowired
    private ILoanService service;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> get(@RequestParam String mobileNumber) {
        return ResponseEntity.ok(service.get(mobileNumber));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestParam String mobileNumber) {
       service.create(mobileNumber);
       return ResponseEntity.status(201).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody LoansDto dto) {
        service.update(dto);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam String mobileNumber) {
        service.delete(mobileNumber);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok("1"); //todo
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(loansContactInfoDto);
    }
}
