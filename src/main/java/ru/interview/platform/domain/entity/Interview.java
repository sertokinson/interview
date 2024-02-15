package ru.interview.platform.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Data
@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String header;

    @NotNull
    String description;

    @NotNull
    Integer price;

    @NotNull
    String level;

    @ManyToOne
    @JoinColumn(name = "employer_id", updatable = false)
    Employer employer;

    @NotNull
    String status;

    String resumeFile;
    String reviewFile;

    @CreationTimestamp
    @Column(updatable = false)
    OffsetDateTime createDateTime;

    @UpdateTimestamp
    OffsetDateTime changeDateTime;
}
