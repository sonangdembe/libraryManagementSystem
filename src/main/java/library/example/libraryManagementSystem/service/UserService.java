package library.example.libraryManagementSystem.service;


import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.dto.UserRegisterRequestDto;
import library.example.libraryManagementSystem.dto.UserRegisterResponseDto;
import library.example.libraryManagementSystem.entity.Book;
import library.example.libraryManagementSystem.entity.User;
import library.example.libraryManagementSystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(UserRegisterRequestDto userRegisterRequestDto){
        User user = new User();

        user.setFirstName(userRegisterRequestDto.getFirstName());
        user.setLastName(userRegisterRequestDto.getLastName());
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPassword(userRegisterRequestDto.getPassword());
        user.setIsDeleted(false);
        userRepository.save(user);
    }

    public Optional<UserRegisterResponseDto> getUserById(@PathVariable Long id){
        return  userRepository.findByIdAndIsDeletedFalse(id)
                .map( user ->{
                    UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
                    userRegisterResponseDto.setId(user.getId());
                    userRegisterResponseDto.setFirstName(user.getFirstName());
                    userRegisterResponseDto.setLastName(user.getLastName());
                    userRegisterResponseDto.setEmail(user.getEmail());

                    return  userRegisterResponseDto;
                });

    }

    public List<UserRegisterResponseDto> getAllUser(){
        return userRepository.findAllByIsDeletedFalse()
                .stream()
                .map(user -> {
                    UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
                    userRegisterResponseDto.setId(user.getId());
                    userRegisterResponseDto.setFirstName(user.getFirstName());
                    userRegisterResponseDto.setLastName(user.getLastName());
                    userRegisterResponseDto.setEmail(user.getEmail());

                    return userRegisterResponseDto;
                })
                .collect(Collectors.toList());

    }

    public UserRegisterResponseDto updateUser(Long id,UserRegisterRequestDto userRegisterRequestDto){
        User user =  userRepository.findByIdAndIsDeletedFalse(id).get();
        user.setFirstName(userRegisterRequestDto.getFirstName());
        user.setLastName(userRegisterRequestDto.getLastName());
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPassword(userRegisterRequestDto.getPassword());
        user.setIsDeleted(false);
       User usersaved = userRepository.save(user);

       UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
       userRegisterResponseDto.setId(usersaved.getId());
       userRegisterResponseDto.setFirstName(user.getFirstName());
       userRegisterResponseDto.setLastName(user.getLastName());
       userRegisterResponseDto.setEmail(user.getEmail());


       return userRegisterResponseDto;
    }

    public void deleteUser(Long id){
       User user =  userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Book not found with this id" + id));

        user.setIsDeleted(true);
        userRepository.save(user);

    }
}
