package com.example.useroperationsapp.service;

import com.example.useroperationsapp.model.UserDto;
import com.example.useroperationsapp.model.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    /**
     * Создание пользователя
     * @param dto - модель юзера
     * @return dto
     */
    UserDto saveNewUser(UserDto dto);

    /**
     * Обновление пользователя
     * @param dto - модель юзера
     * @return dto
     */
    UserResponse updateUser(Long userId, UserDto dto);

    /**
     * Удаление пользователя
     * @param id - числовой идентификатор пользователя
     */
    void deleteUser(Long userId);

    /**
     * Получение списка пользователей
     *
     * @return список dto
     */
    List<UserResponse> getAll();

    /**
     * Получение списка заявок на возврат с пагинацией
     *
     * @param pageable параметры пагинации
     * @return список dto-заявок с пагинацией
     */
    Page<UserResponse> getAllUserPageable(Pageable pageable, Integer age, String phone, String name, String eMail);

    /**
     * Получение информации о пользователе
     * @param userId - id юзера
     * @return UserResponse
     */
    UserResponse getById(Long userId);

    /**
     * Увеличение счета пользователя на 10% каждые 20 сек.
     */
    void increaseUserCashOnTenPercents();
}
