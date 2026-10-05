package library.example.libraryManagementSystem.mapper;

import library.example.libraryManagementSystem.dto.UserRegisterRequestDto;
import library.example.libraryManagementSystem.dto.UserRegisterResponseDto;
import library.example.libraryManagementSystem.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    // dto to new Entity

    public User toEntity(UserRegisterRequestDto userRegisterRequestDto){
        User user = new User();
        updateUser(user, userRegisterRequestDto);
        user.setIsDeleted(false);
        return user;
    }

    // Entity toDto

    public UserRegisterResponseDto toDto(User user){

     UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();
        userRegisterResponseDto.setId(user.getId());
        userRegisterResponseDto.setFirstName(user.getFirstName());
        userRegisterResponseDto.setLastName(user.getLastName());
        userRegisterResponseDto.setEmail(user.getEmail());
        userRegisterResponseDto.setIsDeleted(false);

        return  userRegisterResponseDto;
    }

    // dto to entity

    public void updateUser(User user, UserRegisterRequestDto userRegisterRequestDto){
        user.setId(user.getId());
        user.setFirstName(userRegisterRequestDto.getFirstName());
        user.setLastName(userRegisterRequestDto.getLastName());
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPassword(userRegisterRequestDto.getPassword());
        user.setIsDeleted(false);
    }
}
