package com.tf.demo.dubbo;

import com.tf.demo.dubbo.api.CalculateService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;

public class ClientBootstrap {
    public static void main(String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("calculationClient");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("localhost:2181");
        registry.setUsername("naget-c");
        registry.setPassword("password");
        registry.setProtocol("zookeeper");

// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

// 引用远程服务
        ReferenceConfig<CalculateService> reference = new ReferenceConfig<CalculateService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(CalculateService.class);
        reference.setVersion("1.0.0");

// 和本地bean一样使用xxxService
        CalculateService xxxService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

        System.out.println("1+2，通过rpc获取结果："+xxxService.sum(1,2));
        System.in.read();
    }
}
