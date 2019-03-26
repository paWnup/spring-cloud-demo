package com.ftx.solution.management.example.controller;

import com.ftx.solution.common.base.controller.BaseController;
import com.ftx.solution.common.base.util.ResultMap;
import com.ftx.solution.common.rabbitmq.producer.MessageProducer;
import com.ftx.solution.common.redis.util.RedisUtil;
import com.ftx.solution.management.example.config.MqConfig;
import com.ftx.solution.management.example.service.ExampleService;
import com.ftx.solution.model.example.vo.ExampleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RefreshScope
@Controller
@Api(description = "management例子API")
public class ManagementExampleController extends BaseController {

    @Value("${name:unknown}")
    private String name;

    @Resource
    EurekaDiscoveryClient discoveryClient;

    @Resource
    private ExampleService serviceClient;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private MessageProducer messageProducer;

    @GetMapping(value = "/print")
    @ResponseBody
    @ApiOperation(value = "打印服务器信息")
    public String printServiceA() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getServiceId()
                + " (" + serviceInstance.getHost()
                + ":" + serviceInstance.getPort() + ")"
                + "===>name:" + name + "<br/>"
                + serviceClient.printService();
    }

    @GetMapping(path = "/current")
    @ResponseBody
    @ApiOperation(value = "不知道")
    public Principal getCurrentAccount(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "跳转首页")
    public ModelAndView home() {
        return new ModelAndView("/index");
    }

    @PostMapping(value = "/valid")
    @ApiOperation(value = "验证传入参数")
    @ResponseBody
    public ResultMap valid(@Valid ExampleVo exampleVo, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            return ResultMap.getFailedResultMap(list.get(0).getDefaultMessage());
        }
        return ResultMap.getSuccessResultMap(exampleVo);
    }

    @PostMapping(value = "/redisTest")
    @ApiOperation(value = "测试redis")
    @ResponseBody
    public ResultMap redisTest(String str) {
        if (redisUtil.set("test", str)) {
            return ResultMap.getSuccessResultMap();
        }
        return ResultMap.getFailedResultMap();
    }

    @PostMapping(value = "/mqTest")
    @ApiOperation(value = "测试RabbitMQ")
    @ResponseBody
    public ResultMap mqTest(String str) {
        messageProducer.sendMessage("测试0", "exchange", MqConfig.ROUTINGKEY_A);
        messageProducer.sendMessage("测试1", "exchange", MqConfig.ROUTINGKEY_B);
        messageProducer.sendMessage("测试2", MqConfig.EXCHANGE_A, MqConfig.ROUTINGKEY_A);
        messageProducer.sendMessage("测试3", MqConfig.EXCHANGE_A, MqConfig.ROUTINGKEY_B);
        return ResultMap.getSuccessResultMap();
    }

}