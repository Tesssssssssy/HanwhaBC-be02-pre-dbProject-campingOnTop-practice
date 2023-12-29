package com.example.campingontop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import com.example.campingontop.model.enums.hasAirConditioner;
import com.example.campingontop.model.enums.hasWashingMachine;
import com.example.campingontop.model.enums.isActive;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class Accomodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private Integer user_id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 400, nullable = false)
    private String content;

    private Integer price;
    private String img;

    @Column(length = 100, nullable = false, unique = true)
    private String address;

    @Column(length = 30, nullable = false)
    private String latitude;

    @Column(length = 30, nullable = false)
    private String longitude;

    @Max(20)
    private Integer max_user;

    @Enumerated(value = EnumType.STRING)
    private hasWashingMachine has_WashingMachine;

    @Enumerated(value = EnumType.STRING)
    private hasAirConditioner has_AirConditioner;

    @ColumnDefault("'ACTIVE")
    @Enumerated(value = EnumType.STRING)
    private isActive is_active;

    private LocalDateTime register_time;
}
