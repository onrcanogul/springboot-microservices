package com.bankapp.accounts.functions;

import com.bankapp.accounts.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);
    @Bean
    public Consumer<Long> getCommunication(IAccountService accountService) {
        return accountNumber -> {
            //we can use account service to process
            log.info("Updating Communication status for the account number : " + accountNumber.toString());
        };
    }
}
