package com.kface.kfaceddd.domain.order.factory;

import com.kface.kfaceddd.client.constant.BizTypeEnum;
import com.kface.kfaceddd.domain.id.GlobalIdService;
import com.kface.kfaceddd.domain.order.aggregate.Order;
import com.kface.kfaceddd.domain.order.vo.PriceVO;
import com.kface.kfaceddd.domain.order.vo.UidVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;

@Component
public class OrderFactory {

    @Resource
    @Qualifier("id:orderNo:service")
    private GlobalIdService globalIdService;

    public Order newInstance(PriceVO priceVO, UidVO uidVO) throws ValidationException {
        Order order = new Order(priceVO, uidVO);
        order.init(globalIdService.generate(BizTypeEnum.ORDER.getBizType()));
        return order;
    }

}
