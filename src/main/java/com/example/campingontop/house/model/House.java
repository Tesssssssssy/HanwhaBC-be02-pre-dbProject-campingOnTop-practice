package com.example.campingontop.house.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private Integer user_id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 300, nullable = false)
    private String content;

    @Min(0)
    @Column(nullable = false)
    private Integer price;

    @Column(length = 200)
    private String img;

    @Column(length = 100, nullable = false, unique = true)
    private String address;

    @Column(length = 50, nullable = false)
    private String latitude;

    @Column(length = 50, nullable = false)
    private String longitude;

    @Min(1)
    private Integer max_user;

    @ColumnDefault("1")
    @Comment("0: 미보유 | 1: 보유")
    private Boolean hasWashingMachine;

    @ColumnDefault("1")
    @Comment("0: 미보유 | 1: 보유")
    private Boolean hasAirConditioner;

    @ColumnDefault("1")
    @Comment("0: 비활성화 | 1: 활성화")
    private Boolean isActive;


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
