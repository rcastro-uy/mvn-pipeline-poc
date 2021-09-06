package com.devtools.mvnpipelinepoc.paypal;

import com.paypal.core.PayPalEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayPalConfig {

    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Bean
    public Map<String, String>  paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("clientId", clientId);
        configMap.put("clientSecret", clientSecret);
        return configMap;
    }

    /**
     * En producción, se usaría PayPalEnvironment.Live() en lugar de PayPalEnvironment.Sandbox()
     */

    @Bean
    public PayPalEnvironment getPayPalEnvironment(){
        PayPalEnvironment payPalEnvironment = new PayPalEnvironment.Sandbox(
                paypalSdkConfig().get("clientId"),
                paypalSdkConfig().get("clientSecret")
        );
        return payPalEnvironment;
    }

}
