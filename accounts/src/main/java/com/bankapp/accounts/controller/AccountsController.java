package com.bankapp.accounts.controller;

import com.bankapp.accounts.dto.AccountsDto;
import com.bankapp.accounts.dto.CustomerDto;
import com.bankapp.accounts.dto.ResponseDto;
import com.bankapp.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
    private IAccountService service;

    @GetMapping("/fetch")
    public ResponseEntity<AccountsDto> fetch(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber) {
        return ResponseEntity.ok(service.fetchAccount(mobileNumber));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CustomerDto dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(201, "Account created successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody AccountsDto dto) {
        service.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(204, "Account updated successfully"));
    }
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto> delete(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber) {
        service.delete(mobileNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(204, "Account deleted successfully"));
    }

}
