package ru.interview.platform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.interview.platform.domain.entity.Employer;
import ru.interview.platform.service.EmployerService;

import static ru.interview.platform.handler.AuthenticationSuccessHandler.getEmail;

@RestController
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping("/profile/get")
    public ResponseEntity<Employer> get(OAuth2AuthenticationToken token) {
        return ResponseEntity.ok(employerService.get(getEmail(token)));
    }

    @PostMapping("/profile/edit")
    public void edit(@RequestBody Employer employer) {
        employerService.update(employer);
    }
}
