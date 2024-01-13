package com.example.campingontop.orders.model;


import com.example.campingontop.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="User_id")
    private User user;


    private String impUid;

    private Timestamp paymentTime;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderProducts> orderProductsList = new ArrayList<>();

}
