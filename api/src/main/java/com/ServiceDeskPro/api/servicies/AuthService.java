package com.ServiceDeskPro.api.servicies;

import com.ServiceDeskPro.api.Dtos.EmailDTO;
import com.ServiceDeskPro.api.Dtos.NewPasswordDTO;
import com.ServiceDeskPro.api.entities.PasswordRecover;
import com.ServiceDeskPro.api.entities.User;
import com.ServiceDeskPro.api.repositories.PasswordRecoverRepository;
import com.ServiceDeskPro.api.repositories.UserRepository;
import com.ServiceDeskPro.api.servicies.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;
    @Value("${email.password-recover.uri}")
    private String recoverUri;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;
    @Autowired
    private EmailService emailService;
    @Transactional
    public void createdRecoverToken(EmailDTO body) {
        User user = userRepository.findByEmail(body.getEmail());
        if(user == null){
            throw new ObjectNotFound("Email nao encontrado");
        }
        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity = passwordRecoverRepository.save(entity);

        String text = "Acesse o link para definir uma nova senha\n\n"
                + recoverUri + token + ". Validade de " + tokenMinutes + " minutos";
        emailService.sendEmail(body.getEmail(), "Recuperaçao de Senha", text);
    }
    @Transactional
    public void saveNewPassword(NewPasswordDTO body) {
        List<PasswordRecover> result = passwordRecoverRepository.searchValidTokens(body.getToken(), Instant.now());
        if(result.size() == 0){
            throw new ObjectNotFound("Token invalido");
        }

        User user = userRepository.findByEmail(result.get(0).getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user = userRepository.save(user);
    }

    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

}
