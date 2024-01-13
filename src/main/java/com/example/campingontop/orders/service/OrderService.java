package com.example.campingontop.orders.service;


import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.response.HouseReadDtoRes;
import com.example.campingontop.house.service.HouseService;
import com.example.campingontop.orders.model.OrderProducts;
import com.example.campingontop.orders.model.Orders;
import com.example.campingontop.orders.model.PaymentHouses;
import com.example.campingontop.orders.model.response.GetOrdersRes;
import com.example.campingontop.orders.repository.OrderProductsRepository;
import com.example.campingontop.orders.repository.OrdersRepository;
import com.example.campingontop.user.model.User;
import com.google.gson.Gson;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IamportClient iamportClient;
    private final HouseService houseService;
    private final OrdersRepository ordersRepository;
    private final OrderProductsRepository orderProductsRepository;


    public List<GetOrdersRes> list() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<GetOrdersRes> response = new ArrayList<>();

        for (Orders orders : ordersList) {
            List<HouseReadDtoRes> houseReadResList = new ArrayList<>();
            for (OrderProducts orderProducts : orders.getOrderProductsList()) {

                HouseReadDtoRes productReadRes = HouseReadDtoRes.builder()
                        .id(orderProducts.getHouse().getId())
                        .name(orderProducts.getHouse().getName())
                        .price(orderProducts.getHouse().getPrice())
                        .build();

                houseReadResList.add(productReadRes);
            }

            GetOrdersRes getOrdersRes = GetOrdersRes.builder()
                    .id(orders.getId())
                    .userName(orders.getUser().getName())
                    .houses(houseReadResList)
                    .build();
            response.add(getOrdersRes);
        }

        return response;
    }

    public void createOrders(String impUid, PaymentHouses paymentProducts) {
        Orders orders = Orders.builder()
                .user(User.builder().id(1L).build())
                .impUid(impUid)
                .build();
        orders = ordersRepository.save(orders);

        for (House house : paymentProducts.getHouses()) {
            orderProductsRepository.save(
                    OrderProducts.builder()
                            .orders(orders)
                            .house(house)
                            .build()
            );
        }
    }


    public Boolean paymentValidation(String impUid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = getPaymentInfo(impUid);
        Integer amount = response.getResponse().getAmount().intValue();

        String customDataString = response.getResponse().getCustomData();
        Gson gson = new Gson();
        PaymentHouses paymentHouses = gson.fromJson(customDataString, PaymentHouses.class);

        Integer totalPrice = houseService.getTotalPrice(paymentHouses);

        if (amount.equals(totalPrice)) {

            createOrders(impUid, paymentHouses);
            return true;
        }

        return false;
    }

    public IamportResponse getPaymentInfo(String impUid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);

        return response;
    }

    public void cancelPayment(String impUid) throws IamportResponseException, IOException {
        CancelData cancelData = new CancelData(impUid, true);
        iamportClient.cancelPaymentByImpUid(cancelData);

    }

    public void updateOrders(Long orderId, Orders updatedOrders) {
        Orders existingOrders = ordersRepository.findById(orderId).orElse(null);

        if (existingOrders != null) {
            existingOrders.setUser(updatedOrders.getUser());
            existingOrders.setImpUid(updatedOrders.getImpUid());
            existingOrders.setPaymentTime(updatedOrders.getPaymentTime());
            existingOrders.setCheckInTime(updatedOrders.getCheckInTime());
            existingOrders.setCheckOutTime(updatedOrders.getCheckOutTime());


            ordersRepository.save(existingOrders);
        }
    }


    public void deleteOrders(Long orderId) {
        Orders existingOrders = ordersRepository.findById(orderId).orElse(null);

        if (existingOrders != null) {
            List<OrderProducts> orderProductsList = existingOrders.getOrderProductsList();

            for (OrderProducts orderProducts : orderProductsList) {
                orderProductsRepository.delete(orderProducts);
            }

            ordersRepository.delete(existingOrders);
        }
    }


}
