package com.example.useroperationsapp.mapper;
import com.example.useroperationsapp.entity.ProfileEntity;
import com.example.useroperationsapp.model.ProfileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto map(ProfileEntity profileEntity);
    ProfileEntity map(ProfileDto dto);
}