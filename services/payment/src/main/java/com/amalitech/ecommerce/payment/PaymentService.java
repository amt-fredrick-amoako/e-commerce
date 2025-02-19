package com.amalitech.ecommerce.payment;

import com.amalitech.ecommerce.dtos.PaymentRequest;
import com.amalitech.ecommerce.notification.NotificationProducer;
import com.amalitech.ecommerce.notification.PaymentNotificationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(@Valid PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
         notificationProducer.sendNotification(new PaymentNotificationRequest(
                 paymentRequest.orderReference(),
                 paymentRequest.amount(),
                 paymentRequest.paymentMethod(),
                 paymentRequest.customer().firstname(),
                 paymentRequest.customer().lastname(),
                 paymentRequest.customer().email()
         ));
        return payment.getId();
    }
}
