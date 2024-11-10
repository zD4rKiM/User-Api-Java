package it.test.demo.handler;

import com.amazonaws.services.lambda.runtime.Context;
import it.test.demo.model.User;
import it.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLambdaHandler {

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user, Context context) {
        userRepository.saveUser(user);
        return "User created successfully.";
    }

    public User getUserByEmail(String email, Context context) {
        return userRepository.getUserByEmail(email);
    }
}
