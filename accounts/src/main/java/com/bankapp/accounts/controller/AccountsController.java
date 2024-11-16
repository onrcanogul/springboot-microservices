package com.bankapp.accounts.controller;

import com.bankapp.accounts.dto.AccountsDto;
import com.bankapp.accounts.dto.CustomerDto;
import com.bankapp.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody CustomerDto dto) {



        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(201, "Account created successfully"));
    }

}
