package com.example.useroperationsapp.mapper;

import com.example.useroperationsapp.entity.PhoneEntity;
import com.example.useroperationsapp.model.PhoneDto;
import com.example.useroperationsapp.model.PhoneResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-08T11:04:30+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class PhoneMapperImpl implements PhoneMapper {

    @Override
    public PhoneDto map(PhoneEntity phoneEntity) {
        if ( phoneEntity == null ) {
            return null;
        }

        PhoneDto phoneDto = new PhoneDto();

        phoneDto.setValue( phoneEntity.getValue() );

        return phoneDto;
    }

    @Override
    public PhoneResponse mapToResponse(PhoneEntity phoneEntity) {
        if ( phoneEntity == null ) {
            return null;
        }

        PhoneResponse phoneResponse = new PhoneResponse();

        phoneResponse.setValue( phoneEntity.getValue() );

        return phoneResponse;
    }

    @Override
    public PhoneEntity map(PhoneDto dto) {
        if ( dto == null ) {
            return null;
        }

        PhoneEntity phoneEntity = new PhoneEntity();

        phoneEntity.setValue( dto.getValue() );

        return phoneEntity;
    }
}
