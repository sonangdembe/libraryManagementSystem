package library.example.libraryManagementSystem.controller;

import library.example.libraryManagementSystem.dto.BookRequestDto;
import library.example.libraryManagementSystem.dto.BookResponseDto;
import library.example.libraryManagementSystem.dto.UserRegisterRequestDto;
import library.example.libraryManagementSystem.dto.UserRegisterResponseDto;
import library.example.libraryManagementSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        userService.addUser(userRegisterRequestDto);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterResponseDto> getUserById(@PathVariable Long id) {
        UserRegisterResponseDto userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);

    }
    @GetMapping
    public ResponseEntity<List<UserRegisterResponseDto>>  getAllUsers(){
        List<UserRegisterResponseDto>  allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserRegisterResponseDto> updateUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto, @PathVariable Long id){
        UserRegisterResponseDto getUser = userService.updateUser( id, userRegisterRequestDto);

        return ResponseEntity.ok(getUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return  ResponseEntity.ok("user deleted successfully");
    }
}
