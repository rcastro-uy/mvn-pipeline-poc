package com.devtools.mvnpipelinepoc.paypal;

import org.springframework.context.annotation.Bean;

import java.net.URI;

public interface PaymentService {

    CreatedOrder orderRequest(Double totalAmount, URI returnUrl);

    void captureOrder(String orderId);
}
