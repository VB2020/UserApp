package com.example.useroperationsapp.entity;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profiles")
@Accessors(chain = true)
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Депозит
     */
    @Column(name = "cash", nullable = false)
    private BigDecimal cash;

    /**
     * Стартовый депозит
     */
    @Column(name = "initial_cash", nullable = false)
    private BigDecimal initialCash;

    /**
     * Флаг возможности увеличения депозита
     */
    @Column(name = "able_to_increase", nullable = false)
    private Boolean ableToIncrease;

    /**
     * Связь с юзером
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}