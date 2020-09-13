package com.tf.demo.dubbo;

import com.tf.demo.dubbo.api.CalculateService;
import com.tf.demo.dubbo.impl.CalculateServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class ServerBootstrap {

    public static void main(String[] args) throws IOException {
        // 服务实现
        CalculateService xxxService = new CalculateServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("calculationServer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("localhost:2181");
        registry.setUsername("naget-s");
        registry.setPassword("password");
        registry.setProtocol("zookeeper");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        protocol.setThreads(200);

// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        ServiceConfig<CalculateService> service = new ServiceConfig<CalculateService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(CalculateService.class);
        service.setRef(xxxService);
        service.setVersion("1.0.0");

// 暴露及注册服务
        service.export();
        System.in.read();
    }

}
