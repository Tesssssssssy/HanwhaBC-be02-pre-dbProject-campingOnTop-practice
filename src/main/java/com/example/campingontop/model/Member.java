package com.example.campingontop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String nickName;

    @Column(length = 50, nullable = false)
    private String phoneNum;

    @Column(length = 10, nullable = false)
    private Integer gender;

    @Column(length = 10, nullable = false)
    private Integer is_active;

    private LocalDate birthday;
    private LocalDateTime register_time;
}
