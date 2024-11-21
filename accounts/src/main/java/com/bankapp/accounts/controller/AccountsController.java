package com.bankapp.accounts.controller;

import com.bankapp.accounts.dto.AccountsContactInfoDto;
import com.bankapp.accounts.dto.AccountsDto;
import com.bankapp.accounts.dto.CustomerDto;
import com.bankapp.accounts.dto.ResponseDto;
import com.bankapp.accounts.service.IAccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {
    @Autowired
    private IAccountService service;
    @Autowired
    private Environment environment;
    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;
    private Logger logger = LoggerFactory.getLogger(AccountsController.class);
    @GetMapping("/fetch")
    public ResponseEntity<AccountsDto> fetch(@RequestHeader("correlation-id") String correlationId, @RequestParam @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber) {
        logger.debug("correlation-id found: {} ", correlationId);
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
    @Retry(name = "getVersion", fallbackMethod = "getVersionInfoFallback") //ex retry
    @GetMapping("/build-info")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok("1");
    }

    public ResponseEntity<String> getVersionInfoFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("0.9");
    }
    @RateLimiter(name = "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }
    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity.ok("Java 17");
    }


    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(accountsContactInfoDto);
    }

}
