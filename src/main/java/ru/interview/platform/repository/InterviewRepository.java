package ru.interview.platform.repository;

import ru.interview.platform.domain.entity.Employer;
import ru.interview.platform.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findAllByEmployer(Employer employer);
}
