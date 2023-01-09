package org.example;

import org.example.pojo.CreateUserAPI;
import org.example.service.BankService;

public class application {
    public static void main(String[] args) {
        CreateUserAPI api = new CreateUserAPI();
        BankService.remoteCall(api);
    }
}
