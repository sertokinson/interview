package ru.interview.platform.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.interview.platform.domain.entity.Employer;
import ru.interview.platform.repository.EmployerRepository;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final String EMAIL = "email";

    private final EmployerRepository employerRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        saveEmployer(getEmail(authentication));
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public static String getEmail(Authentication authentication) {
        return ((DefaultOidcUser) authentication.getPrincipal()).getAttribute(EMAIL);
    }

    private void saveEmployer(String email) {
        final Optional<Employer> optionalEmployer = employerRepository.findByEmail(email);
        if (optionalEmployer.isPresent()) {
            log.info("Exists employer success login with email = {}", email);
        } else {
            log.info("Save new employer with email = {}", email);
            employerRepository.save(new Employer(email));
        }
    }
}
