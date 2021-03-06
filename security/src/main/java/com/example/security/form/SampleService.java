package com.example.security.form;

import com.example.security.account.Account;
import com.example.security.account.AccountContext;
import com.example.security.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("============");
        System.out.println(userDetails.getUsername());

    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async Service is called");
    }
}
