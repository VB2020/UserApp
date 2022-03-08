package com.example.useroperationsapp.controller;

import com.example.useroperationsapp.model.UserDto;
import com.example.useroperationsapp.model.UserResponse;
import com.example.useroperationsapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@Api(description = "Работа с пользователями")
public class UserController {
    private final UserService userService;

    /** Создание пользователя */
    @PutMapping("/create")
    @Operation(description = "Создание пользователя")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.saveNewUser(request));
    }

    /** Обновление пользователя */
    @PutMapping("/update/{userId}")
    @Operation(description = "Обновление пользователя")
    public ResponseEntity<UserResponse> updateUser(@PathVariable(name = "userId") Long userId, @Valid @RequestBody(required = false) UserDto request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    /** Удаление пользователя */
    @DeleteMapping("/delete")
    @Operation(description = "Удаление пользователя")
    public void deleteUser(@RequestParam(name = "userId") Long userId) {
        userService.deleteUser(userId);
    }

    /** Отображение пользователей */
    @GetMapping("/get-all")
    @Operation(description = "Отображение всех пользователей")
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    /** Отображение пользователя по id */
    @GetMapping("/get-by-id")
    @Operation(description = "Отображение пользователя по id")
    public UserResponse getById(@RequestParam(name = "userId") Long userId) {
        return userService.getById(userId);
    }

    /** Отображение всех пользователей с пагинацией и фильтрацией */
    @GetMapping("/get-all-paged")
    @Operation(description = "Отображение всех пользователей с пагинацией и фильтрацией")
    public Page<UserResponse> getAllPaged(Pageable pageable,
                                          @RequestParam(required = false, name = "age") Integer age,
                                          @RequestParam(required = false, name = "phone") String phone,
                                          @RequestParam(required = false, name = "name") String name,
                                          @RequestParam(required = false, name = "eMail") String eMail) {
        return userService.getAllUserPageable(pageable, age, phone, name, eMail);
    }
}