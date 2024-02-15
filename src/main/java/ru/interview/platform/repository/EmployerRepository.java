package ru.interview.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.interview.platform.domain.entity.Employer;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByEmail(String email);
}
