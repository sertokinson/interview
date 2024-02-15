package ru.interview.platform.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.interview.platform.domain.exception.ValidateException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ValidatorAspect {

    @Around("execution(* ru.interview.platform.service.InterviewService.create(..))")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();
        Object object = joinPoint.getArgs()[0];
        Set<ConstraintViolation<Object>> validate = validator.validate(object);

        String error = validate.stream()
                .map(value -> value.getPropertyPath() + " " + value.getMessage())
                .collect(Collectors.joining(", "));

        if (!validate.isEmpty()) {
            throw new ValidateException(error);
        }

        return joinPoint.proceed();
    }
}
