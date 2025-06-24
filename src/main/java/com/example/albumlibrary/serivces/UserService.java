package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.UserRequestDto;
import com.example.albumlibrary.models.UserEntity;
import com.example.albumlibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserEntityByUsername(username);

        System.out.println(user);

        return new User(user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("user")));
    }

    public UserEntity addUser(UserRequestDto userRequestDto) {
        var user = new UserEntity();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        return userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findUserEntityByUsername(username);
    }


}
