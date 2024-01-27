package ru.interview.platform.domain.entity;

import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@ToString
@Setter
@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Денчик")
    String header;

    @NotNull
    String description;

    @NotNull
    Integer price;

    @NotNull
    String level;

    String resumeFile;
    String reviewFile;

    @CreationTimestamp
    OffsetDateTime createDateTime;

    @UpdateTimestamp
    OffsetDateTime changeDateTime;
}
