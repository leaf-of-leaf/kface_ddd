package com.kface.kfaceddd.infrastructure.id;

import com.kface.kfaceddd.domain.id.GlobalIdService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("id:orderNo:service")
public class OrderNoGenerateServiceImpl implements GlobalIdService {

    @Override
    public String generate(String bizType) {
        // 生成分库分表ID
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
