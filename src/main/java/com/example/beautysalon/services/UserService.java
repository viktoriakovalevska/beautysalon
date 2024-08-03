package com.example.beautysalon.services;

import com.example.beautysalon.common.Role;
import com.example.beautysalon.dto.UserRequestDTO;
import com.example.beautysalon.dto.UserResponseDTO;
import com.example.beautysalon.entities.User;
import com.example.beautysalon.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SequenceGeneratorService sequenceGeneratorService;

    private UserResponseDTO saveUser(User user) throws Exception {
        validateUniqueFields(user.getUsername(), user.getPhone());
        user.setId(sequenceGeneratorService.generateSequence(User.class.getSimpleName()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserResponseDTO(userRepository.save(user));
    }
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) throws Exception {
        User user = User.builder()
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .birthDay(userRequestDTO.getBirthDay())
                .phone(userRequestDTO.getPhone())
                .roles(Set.of(Role.USER))
                .build();
        return saveUser(user);
    }

    public UserResponseDTO findUserById(Long id) {
        return userRepository.findById(id).map(UserResponseDTO::new)
                .orElse(new UserResponseDTO());
    }

    public UserResponseDTO findUserByPhone(String phone) {
        return Optional.ofNullable(userRepository.findByPhone(phone)).map(UserResponseDTO::new)
                .orElse(new UserResponseDTO());
    }

    private <T> T getIfChanged(T oldValue, T newValue) {
        T result = oldValue;
        if (Objects.nonNull(newValue) && !newValue.equals(oldValue)) {
            result = newValue;
        }
        return result;
    }

    public UserResponseDTO editUser(Long id, UserResponseDTO userDTO) throws Exception {
        validateUniqueFields(userDTO.getUsername(), userDTO.getPhone());
        User user = getUser(id);
        user.setUsername(getIfChanged(user.getUsername(), userDTO.getUsername()));
        user.setBirthDay(getIfChanged(user.getBirthDay(), userDTO.getBirthDay()));
        user.setPhone(getIfChanged(user.getPhone(), userDTO.getPhone()));
        return new UserResponseDTO(userRepository.save(user));
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(String.format("User with id %d not found", id)));
    }

    public void validateUniqueFields(String userName, String userPhone) throws Exception {
        if (Objects.nonNull(userRepository.findByUsername(userName)) || Objects.nonNull(userRepository.findByPhone(userPhone))) {
            throw new Exception("User name or phone is not unique");
        }
    }

    public UserResponseDTO setRoles(Long id, Set<Role> roles) throws Exception {
        User user = getUser(id);
        user.setRoles(roles);
        return new UserResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO removeUser(Long id) throws Exception {
        UserResponseDTO userDTO = new UserResponseDTO(getUser(id));
        userRepository.deleteById(id);
        return userDTO;
    }
}

