package com.example.useroperationsapp.entity;
import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usersTable")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "eMail", nullable = false, unique = true)
    private String eMail;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private ProfileEntity profile;
}