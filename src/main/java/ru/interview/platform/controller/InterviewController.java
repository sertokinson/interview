package ru.interview.platform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.interview.platform.domain.entity.Interview;
import ru.interview.platform.service.InterviewService;

import java.util.List;

import static ru.interview.platform.handler.AuthenticationSuccessHandler.getEmail;

@RestController
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @GetMapping("/interview/getAll")
    public ResponseEntity<List<Interview>> getAll(OAuth2AuthenticationToken token) {
        return ResponseEntity.ok(interviewService.getAll(getEmail(token)));
    }

    @PostMapping("/interview/create")
    public void create(@RequestBody Interview interview, OAuth2AuthenticationToken token) {
        interviewService.create(interview, getEmail(token));
    }

    /**
     * Необходимо передать весь объект с текущими и измененнными полями
     * @param interview
     */
    @PostMapping("/interview/edit")
    public void edit(@RequestBody Interview interview) {
        interviewService.update(interview);
    }
}
