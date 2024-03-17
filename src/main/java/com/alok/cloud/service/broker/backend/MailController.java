package com.alok.cloud.service.broker.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/osb")
@RestController
public class MailController {

    @GetMapping("/mail-dashboard/{mailSystemId}")
    public String dashboard(@PathVariable("mailSystemId") String mailSystemId) {
        return "Mail Dashboard - " + mailSystemId;
    }

    @GetMapping("/mail-system/{mailSystemId}")
    public String mailSystem(@PathVariable("mailSystemId") String mailSystemId) {
        return "Mail System - " + mailSystemId;
    }
}
