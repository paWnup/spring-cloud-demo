package com.ftx.solution.management.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-example", fallback = ExampleService.ServiceClientFallback.class)
public interface ExampleService {

    @GetMapping(value = "/")
    String printService();

    @Component
    class ServiceClientFallback implements ExampleService {

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceClientFallback.class);

        @Override
        public String printService() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE FAILED! - FALLING BACK";
        }
    }
}