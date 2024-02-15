package ru.interview.platform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.interview.platform.domain.entity.Employer;
import ru.interview.platform.domain.exception.ServerException;
import ru.interview.platform.domain.exception.ValidateException;
import ru.interview.platform.repository.EmployerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Employer get(String email) {
        log.info("Get employer by email = {}", email);
        return getEmployer(email);
    }

    public void update(Employer employer) {
        log.info("Update employer = {}", employer);
        employerRepository.findById(employer.getId()).orElseThrow(() -> new ValidateException("Employer by id = %s not found", employer.getId()));
        employerRepository.save(employer);
    }

    public Employer getEmployer(String email) {
        return employerRepository.findByEmail(email).orElseThrow((() -> new ServerException("Employer with email = %s not found", email)));
    }

}
