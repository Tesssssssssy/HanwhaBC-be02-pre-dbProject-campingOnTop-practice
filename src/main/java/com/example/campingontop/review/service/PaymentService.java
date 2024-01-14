package com.example.campingontop.review.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public boolean hasPaymentHistory(Long userId) {
        // 실제 결제 이력 확인 로직 구현
        return true; // 여기서는 모든 사용자가 결제 이력이 있다고 가정
    }
}