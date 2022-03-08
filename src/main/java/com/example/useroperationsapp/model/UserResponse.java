package com.example.useroperationsapp.model;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
    private String eMail;
    private ProfileDto profile;
    private List<PhoneResponse> userPhones;
}