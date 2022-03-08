package com.example.useroperationsapp.mapper;

import com.example.useroperationsapp.entity.ProfileEntity;
import com.example.useroperationsapp.model.ProfileDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-08T11:04:29+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDto map(ProfileEntity profileEntity) {
        if ( profileEntity == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        profileDto.setCash( profileEntity.getCash() );

        return profileDto;
    }

    @Override
    public ProfileEntity map(ProfileDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setCash( dto.getCash() );

        return profileEntity;
    }
}
