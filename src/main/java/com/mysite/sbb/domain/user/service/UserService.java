package com.mysite.sbb.domain.user.service;

import com.mysite.sbb.domain.question.exception.DataNotFoundException;
import com.mysite.sbb.domain.user.entity.SiteUser;
import com.mysite.sbb.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public SiteUser getUser(String username){
        Optional<SiteUser> siteUser = userRepository.findByusername(username);
        if (siteUser.isPresent()){
            return siteUser.get();
        }else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
