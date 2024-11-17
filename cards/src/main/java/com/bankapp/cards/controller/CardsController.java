package com.bankapp.cards.controller;

import com.bankapp.cards.dto.CardDto;
import com.bankapp.cards.dto.CardsContactInfoDto;
import com.bankapp.cards.dto.ResponseDto;
import com.bankapp.cards.service.ICardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {
    private ICardService service;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDto cardsContactInfoDto;

    public CardsController(ICardService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDto> get(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber){
        return ResponseEntity.ok(service.fetch(mobileNumber).getFirst()); // because of list example
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber) {
        service.create(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(201, "created"));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody CardDto dto) {
        service.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(204,"updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber) {
        service.delete(mobileNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(204,"deleted"));
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
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(cardsContactInfoDto);
    }
}
