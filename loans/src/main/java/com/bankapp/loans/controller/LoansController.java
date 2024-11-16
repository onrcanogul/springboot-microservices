package com.bankapp.loans.controller;

import com.bankapp.loans.dto.LoansDto;
import com.bankapp.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoansController {
    private final ILoanService service;
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
}
