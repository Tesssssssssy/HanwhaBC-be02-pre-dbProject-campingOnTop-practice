package com.example.campingontop.orders.model;


import com.example.campingontop.house.model.House;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Timestamp paymentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Orders_id")
    Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "House_id")
    House house;
}
