package tech.samuel.auth.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getMessage() {
        return "Auth Server is alive with service layer!";
    }
}
