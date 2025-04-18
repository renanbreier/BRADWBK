package br.edu.ifsp.user_api.util;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsp.user_api.model.User;

@Component
public class UserInitDataSource implements CommandLineRunner {
    @Autowired
    UserDataSource uDataSource;

    int countId = 1;
    @Override
    public void run(String... args) throws Exception {
        
        Stream.generate(() -> {
            User user = new User();
            user.setId(++countId); 
            user.setLogin("IFSP " + user.getId()); 
            user.setPassword("1234"); 
            uDataSource.add(user);
            return user;
        }).limit(20);
    }
}
