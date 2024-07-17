package com.example.beautysalon.services;

import com.example.beautysalon.dto.UserDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public UserDTO saveUser(User user) throws Exception {
        validateUniqueFields(user.getUsername(), user.getPhone());
        user.setId(sequenceGeneratorService.generateSequence(User.class.getSimpleName()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserDTO(userRepository.save(user));
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id).map(UserDTO::new).orElse(new UserDTO());
    }

    public UserDTO findUserByPhone(String phone) {
        return Optional.ofNullable(userRepository.findByPhone(phone)).map(UserDTO::new).orElse(new UserDTO());
    }

    private <T> T getIfChanged(T oldValue, T newValue) {
        T result = oldValue;
        if (Objects.nonNull(newValue) && !newValue.equals(oldValue)) {
            result = newValue;
        }
        return result;
    }

    public UserDTO editUser(Long id, UserDTO userDTO) throws Exception{
        validateUniqueFields(userDTO.getUsername(), userDTO.getPhone());
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException(String.format("User with id {} not found", id)));
        user.setUsername(getIfChanged(user.getUsername(), userDTO.getUsername()));
        user.setBirthDay(getIfChanged(user.getBirthDay(), userDTO.getBirthDay()));
        user.setPhone(getIfChanged(user.getPhone(), userDTO.getPhone()));
        return new UserDTO(userRepository.save(user));
    }

    public void validateUniqueFields(String userName, String userPhone) throws Exception {
        if (Objects.nonNull(userRepository.findByUsername(userName)) || Objects.nonNull(userRepository.findByPhone(userPhone))) {
            throw new Exception("User name or phone is not unique");
        }
    }


}

