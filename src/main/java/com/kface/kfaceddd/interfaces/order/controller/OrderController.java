package com.kface.kfaceddd.interfaces.order.controller;

import com.kface.kfaceddd.application.service.order.OrderCmdService;
import com.kface.kfaceddd.application.service.order.OrderQryService;
import com.kface.kfaceddd.application.cqrs.cmd.OrderUpdateCmd;
import com.kface.kfaceddd.application.cqrs.qry.OrderQry;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderCmdService orderCmdService;

    @Resource
    private OrderQryService orderQryService;

    @GetMapping("/queryOrder")
    public void queryOrder(@RequestParam OrderQry orderQry) {

    }

    @PostMapping("/updateOrder")
    public void updateOrder(@RequestBody OrderUpdateCmd orderUpdateCmd) throws Exception {
        orderCmdService.modify(orderUpdateCmd);
    }

}
