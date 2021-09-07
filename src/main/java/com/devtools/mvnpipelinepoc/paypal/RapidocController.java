package com.devtools.mvnpipelinepoc.paypal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class RapidocController {

    @GetMapping("/test")
    public String getDocs(){
        return "rapidoc-test.json";
    }
}
