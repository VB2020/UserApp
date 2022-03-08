package com.example.useroperationsapp.mapper;

import com.example.useroperationsapp.entity.ProfileEntity;
import com.example.useroperationsapp.entity.UserEntity;
import com.example.useroperationsapp.model.ProfileDto;
import com.example.useroperationsapp.model.UserDto;
import com.example.useroperationsapp.model.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-08T11:04:30+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto map(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( userEntity.getName() );
        userDto.setAge( userEntity.getAge() );
        userDto.setEMail( userEntity.getEMail() );
        userDto.setProfile( profileEntityToProfileDto( userEntity.getProfile() ) );

        return userDto;
    }

    @Override
    public UserResponse mapToResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( userEntity.getId() );
        userResponse.setName( userEntity.getName() );
        userResponse.setAge( userEntity.getAge() );
        userResponse.setEMail( userEntity.getEMail() );
        userResponse.setProfile( profileEntityToProfileDto( userEntity.getProfile() ) );

        return userResponse;
    }

    @Override
    public UserEntity map(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName( dto.getName() );
        userEntity.setAge( dto.getAge() );
        userEntity.setEMail( dto.getEMail() );
        userEntity.setProfile( profileDtoToProfileEntity( dto.getProfile() ) );

        return userEntity;
    }

    protected ProfileDto profileEntityToProfileDto(ProfileEntity profileEntity) {
        if ( profileEntity == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        profileDto.setCash( profileEntity.getCash() );

        return profileDto;
    }

    protected ProfileEntity profileDtoToProfileEntity(ProfileDto profileDto) {
        if ( profileDto == null ) {
            return null;
        }

        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setCash( profileDto.getCash() );

        return profileEntity;
    }
}
