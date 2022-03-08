package com.example.useroperationsapp.entity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "phones")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Номер телефона юзера
     */
    @Column(name = "value", length = 30, nullable = false, unique = true)
    private String value;

    /**
     * Связь с юзером
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}