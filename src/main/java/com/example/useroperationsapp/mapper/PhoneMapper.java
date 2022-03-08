package com.example.useroperationsapp.mapper;
import com.example.useroperationsapp.entity.PhoneEntity;
import com.example.useroperationsapp.model.PhoneDto;
import com.example.useroperationsapp.model.PhoneResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneDto map(PhoneEntity phoneEntity);
    PhoneResponse mapToResponse(PhoneEntity phoneEntity);
    PhoneEntity map(PhoneDto dto);
}
