package library.example.libraryManagementSystem.service;


import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.dto.UserRegisterRequestDto;
import library.example.libraryManagementSystem.dto.UserRegisterResponseDto;
import library.example.libraryManagementSystem.entity.Book;
import library.example.libraryManagementSystem.entity.User;
import library.example.libraryManagementSystem.mapper.UserMapper;
import library.example.libraryManagementSystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void addUser(UserRegisterRequestDto userRegisterRequestDto){
        User user = userMapper.toEntity(userRegisterRequestDto);
        userRepository.save(user);
    }

    public Optional<UserRegisterResponseDto> getUserById(@PathVariable Long id){
        return  userRepository.findByIdAndIsDeletedFalse(id)
                .map(userMapper::toDto);

    }

public List<UserRegisterResponseDto> getAllUser(){
    return userRepository.findAllByIsDeletedFalse()
            .stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());

}

public UserRegisterResponseDto updateUser(Long id,UserRegisterRequestDto userRegisterRequestDto){
    User user =  userRepository.findByIdAndIsDeletedFalse(id).get();
    userMapper.updateUser(user, userRegisterRequestDto);
    User usersaved = userRepository.save(user);
    return userMapper.toDto(usersaved);
}

public void deleteUser(Long id){
    User user =  userRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new RuntimeException("Book not found with this id" + id));

    user.setIsDeleted(true);
    userRepository.save(user);

}
}

