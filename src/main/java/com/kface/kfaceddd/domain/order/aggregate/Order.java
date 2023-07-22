package com.kface.kfaceddd.domain.order.aggregate;

import com.kface.kfaceddd.common.constant.OrderConstant;
import com.kface.kfaceddd.common.exception.Assert;
import com.kface.kfaceddd.domain.common.AbstractBaseAggregate;
import com.kface.kfaceddd.domain.order.entity.OrderDetail;
import com.kface.kfaceddd.domain.order.event.OrderCreateEvent;
import com.kface.kfaceddd.domain.order.event.OrderStatusChangeEvent;
import com.kface.kfaceddd.domain.order.vo.PriceVO;
import com.kface.kfaceddd.domain.order.vo.UidVO;
import org.apache.commons.lang3.StringUtils;
import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

import javax.xml.bind.ValidationException;
import java.util.List;

@TypeName("order")
public class Order extends AbstractBaseAggregate<String> {

    private Long id;

    @Id
    private String orderNo;

    private List<OrderDetail> orderDetails;

    private Integer status;

    private PriceVO priceVO;

    private UidVO uidVO;

    public Order(PriceVO priceVO, UidVO uidVO) {
        this.priceVO = priceVO;
        this.uidVO = uidVO;
    }

    @Override
    public String getIdentify() {
        return this.orderNo;
    }

    public Order() {}

    public Order(String orderNo) {
        // 参数校验
        Assert.notNull(orderNo, "orderNo is null");
        this.orderNo = orderNo;
    }

    public void init() {
        this.status = OrderConstant.OrderStatus.UNPAID;
        OrderCreateEvent.InnerData innerData = new OrderCreateEvent.InnerData(this.orderNo);
        OrderCreateEvent orderCreateEvent = new OrderCreateEvent(this, innerData);
        this.registerEvent(orderCreateEvent);
    }

    public void init(String orderNo) throws ValidationException {
        if (StringUtils.isBlank(orderNo)) {
            throw new ValidationException("orderNo is blank");
        }
        this.init();
        this.orderNo = orderNo;
    }

    public void statusChange(Integer status) throws ValidationException {
        if (status == null || !OrderConstant.getValidOrderStatus().contains(status)) {
            throw new ValidationException("status incorrect");
        }
        this.status = status;
        OrderStatusChangeEvent.InnerData innerData = new OrderStatusChangeEvent.InnerData(status, orderNo);
        OrderStatusChangeEvent orderStatusChangeEvent = new OrderStatusChangeEvent(this, innerData);
        this.registerEvent(orderStatusChangeEvent);
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void priceChange(Integer newPrice) throws ValidationException {
        this.priceVO = new PriceVO(newPrice, this.priceVO.getOriginPrice());
        // 价格变更事件
//        this.registerEvent(orderStatusChangeEvent);
    }

    public Long getId() {
        return id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public Integer getStatus() {
        return status;
    }

    public PriceVO getPriceVO() {
        return priceVO;
    }

    public UidVO getUidVO() {
        return uidVO;
    }
}
