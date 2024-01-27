package ru.interview.platform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.interview.platform.domain.entity.Interview;
import ru.interview.platform.service.InterviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    public void auth() {
    }

    @GetMapping("/interview/getAll")
    public ResponseEntity<List<Interview>> getAll() {
        return ResponseEntity.ok(interviewService.getAll());
    }

    @PostMapping("/interview/create")
    public void create(@Valid @RequestBody Interview interview) {
        interviewService.create(interview);
    }

    public void edit(Interview interview) {
    }
}
