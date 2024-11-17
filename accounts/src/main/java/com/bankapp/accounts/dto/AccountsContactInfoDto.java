package com.bankapp.accounts.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.List;

@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class AccountsContactInfoDto{
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
