package ru.interview.platform.repository;

import ru.interview.platform.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
