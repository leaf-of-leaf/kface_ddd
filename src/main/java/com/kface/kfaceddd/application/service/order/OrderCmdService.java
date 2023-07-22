package com.kface.kfaceddd.application.service.order;

import com.kface.kfaceddd.application.cqrs.cmd.OrderCreateCmd;
import com.kface.kfaceddd.application.cqrs.cmd.OrderPriceUpdateCmd;
import com.kface.kfaceddd.application.cqrs.cmd.OrderUpdateCmd;

import javax.xml.bind.ValidationException;

public interface OrderCmdService {

    void modify(OrderUpdateCmd orderUpdateCmd) throws ValidationException;

    void create(OrderCreateCmd orderCreateCmd) throws ValidationException;

    void modify(OrderPriceUpdateCmd orderPriceUpdateCmd) throws ValidationException;

}
