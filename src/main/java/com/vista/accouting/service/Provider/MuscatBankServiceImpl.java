package com.vista.accouting.service.Provider;


import org.springframework.stereotype.Component;

@Component
public class MuscatBankServiceImpl {

    private String patternDate="([0-9]+(/[0-9]+)+)";
    private String cardNumber="X+";
    private String value="[0-9]*\\.[0-9]+";


}
