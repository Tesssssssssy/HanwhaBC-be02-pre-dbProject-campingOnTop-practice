package com.example.campingontop.orders.controller;

import com.example.campingontop.house.service.HouseService;
import com.example.campingontop.orders.model.Orders;
import com.example.campingontop.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class OrderController {

    private final HouseService houseService;
    private final OrderService orderService;



    @RequestMapping(method = RequestMethod.GET, value = "/order/list")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(orderService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validation")
    public ResponseEntity validation(String impUid) {
        try {
            if (orderService.paymentValidation(impUid)) {

                return ResponseEntity.ok().body("ok");
            } else {
                return ResponseEntity.ok().body("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body("error");
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/order/update/{orderId}")
    public ResponseEntity updateOrders(Long orderId, Orders updatedOrders) {
        orderService.updateOrders(orderId, updatedOrders);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/order/delete/{orderId}")
    public ResponseEntity deleteOrders(Long orderId) {
        orderService.deleteOrders(orderId);
        return ResponseEntity.ok().build();
    }

}
