package com.example.campingontop.model;

import com.example.campingontop.model.enums.Gender;
import com.example.campingontop.model.enums.isActive;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickName;

    @Column(length = 50, nullable = false)
    private String phoneNum;

    @Column(length = 10, nullable = false)
    private Gender gender;

    @ColumnDefault("'ACTIVE")
    @Column(length = 10, nullable = false)
    private isActive is_active;

    private LocalDate birthday;
    private LocalDateTime register_time;
}
