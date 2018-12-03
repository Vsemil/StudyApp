package ru.firstline.studyapp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;
import ru.firstline.studyapp.model.UserEntity;
import ru.firstline.studyapp.repository.UserRepository;

import java.util.Map;

@Component
public class MyPrincipalExtractor implements PrincipalExtractor {
    @Autowired
    UserRepository userRepository;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String email = (String) map.get("email");
        UserEntity user = userRepository.findByEmail(email).orElseGet(()->{
            UserEntity newUser = new UserEntity();
            newUser.setPrincipalId((String)map.get("sub"));
            newUser.setUsername((String)map.get("name"));
            newUser.setEmail((String)map.get("email"));
            return newUser;
        });
        return userRepository.save(user);
    }
}
