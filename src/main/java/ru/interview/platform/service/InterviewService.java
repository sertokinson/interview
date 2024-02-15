package ru.interview.platform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.interview.platform.domain.entity.Interview;
import ru.interview.platform.domain.exception.ValidateException;
import ru.interview.platform.repository.InterviewRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final EmployerService employerService;

    public List<Interview> getAll(String email) {
        log.info("Get all interview by employer with email = {}", email);
        return interviewRepository.findAllByEmployer(employerService.getEmployer(email));
    }

    public void create(Interview interview, String email) {
        log.info("Save interview = {}", interview);
        interview.setEmployer(employerService.getEmployer(email));
        interviewRepository.save(interview);
    }

    public void update(Interview interview) {
        log.info("Update interview = {}", interview);
        if (interview.getId() == null) {
            throw new ValidateException("interview id is null");
        }
        interviewRepository.findById(interview.getId()).orElseThrow(() -> new ValidateException("interview by id = %s not found", interview.getId()));
        interviewRepository.save(interview);
    }

}
