package com.routeplanner.smartrouteplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.routeplanner.smartrouteplanner.model.ContactMessage;
import com.routeplanner.smartrouteplanner.service.EmailService;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody ContactMessage message) {

        emailService.sendConfirmationEmail(
                message.getEmail(),
                "Smart Route Planner - Message Received",
                "Hello " + message.getName() +
                ",\n\nWe received your message:\n\n" +
                message.getMessage() +
                "\n\nOur team will respond soon.\n\nSmart Route Planner Team"
        );

        return "Message sent successfully";
    }
}