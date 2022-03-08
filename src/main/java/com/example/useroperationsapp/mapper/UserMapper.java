package com.example.useroperationsapp.mapper;

import com.example.useroperationsapp.entity.UserEntity;
import com.example.useroperationsapp.model.UserDto;
import com.example.useroperationsapp.model.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);
    UserResponse mapToResponse(UserEntity userEntity);
    UserEntity map(UserDto dto);
}