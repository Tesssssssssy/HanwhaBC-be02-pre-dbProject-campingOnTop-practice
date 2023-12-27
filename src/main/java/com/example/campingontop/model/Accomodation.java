package com.example.campingontop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accomodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // private Integer user_id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 400, nullable = false)
    private String content;

    private Integer price;
    private String img;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 30, nullable = false)
    private String latitude;

    @Column(length = 30, nullable = false)
    private String longitude;

    @Max(20)
    private Integer max_user;

    @Max(1)
    private Integer hasAirConditioner;

    @Max(1)
    private Integer hasWashingMachine;

    @Max(1)
    private Integer is_active;

    private LocalDateTime register_time;
}
