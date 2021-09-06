package com.devtools.mvnpipelinepoc.paypal;

import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.PurchaseUnitRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final PaymentService paymentService;
    private String orderId = "";

    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/capture")
    public String captureOrder(@RequestParam String token){
        //FIXME --> Guardar en DB o similar
        orderId = token;
        paymentService.captureOrder(token);
        return "redirect:/orders";
    }

    @PostMapping
    public String orderRequest(@RequestParam Double total, HttpServletRequest request){
        log.info("Total: " + total);
        final URI callbackUrl = callbackUrl(request);
        CreatedOrder createdOrder = paymentService.orderRequest(total, callbackUrl);
        return "redirect:"+createdOrder.getApprovalLink();
    }

    private URI callbackUrl(HttpServletRequest request) {
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(),
                    requestUri.getUserInfo(),
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/orders/capture",
                    null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
