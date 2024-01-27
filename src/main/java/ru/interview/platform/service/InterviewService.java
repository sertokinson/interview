package ru.interview.platform.service;

import lombok.extern.slf4j.Slf4j;
import ru.interview.platform.domain.entity.Interview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.interview.platform.repository.InterviewRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewService {
    private final InterviewRepository interviewRepository;

    public List<Interview> getAll() {
        return interviewRepository.findAll();
    }

    public void create(Interview interview) {
        log.info("save interview = {}", interview);
        interviewRepository.save(interview);
    }


}
