package com.example.useroperationsapp.model;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponse {
    private Long userId;
    private String value;
}