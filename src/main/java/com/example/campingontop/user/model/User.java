package com.example.campingontop.user.model;

import com.example.campingontop.enums.Gender;
import com.example.campingontop.houseLike.model.HouseLike;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickName;

    @Column(length = 50, nullable = false, unique = true)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Gender gender;

    @Column(length = 30)
    private String birthDay;

    @ColumnDefault("1")
    @Comment("0: 비활성화 | 1: 활성화")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<HouseLike> houseLikes = new ArrayList<>();

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
        this.updatedAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
