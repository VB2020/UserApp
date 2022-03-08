package com.example.useroperationsapp.model;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private BigDecimal cash;
    //private BigDecimal initialCash;
}