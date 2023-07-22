package com.kface.kfaceddd.infrastructure.order.repositoryImpl;

import com.kface.kfaceddd.common.utils.ObjectTrackingUtil;
import com.kface.kfaceddd.domain.common.RepositorySnapshotSupper;
import com.kface.kfaceddd.domain.order.aggregate.Order;
import com.kface.kfaceddd.domain.order.entity.OrderDetail;
import com.kface.kfaceddd.domain.order.repository.OrderRepository;
import com.kface.kfaceddd.domain.order.vo.OrderNoVO;
import com.kface.kfaceddd.domain.order.vo.PriceVO;
import com.kface.kfaceddd.domain.order.vo.UidVO;
import com.kface.kfaceddd.infrastructure.converter.order.OrderConverter;
import com.kface.kfaceddd.infrastructure.dao.order.OrderDetailPO;
import com.kface.kfaceddd.infrastructure.dao.order.OrderPO;
import com.kface.kfaceddd.infrastructure.dao.order.mapper.OrderDao;
import com.kface.kfaceddd.infrastructure.dao.order.mapper.OrderDetailDao;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class OrderDbRepositoryImpl extends RepositorySnapshotSupper<String, Order> implements OrderRepository {

    @Resource
    private OrderDetailDao orderDetailDao;

    @Resource
    private OrderDao orderDao;

    @Override
    protected void saveImpl(Order order) {
        OrderPO orderPO = OrderConverter.INSTANCE.toOrderPO(order);
        List<OrderDetailPO> orderDetailPOS = order.getOrderDetails().stream().map(OrderConverter.INSTANCE::toOrderDetailPO)
                .collect(Collectors.toList());

        // order insert

        // order details insert

    }

    @Override
    protected void removeImpl(String id) {

    }

    @Override
    protected Order findByIdImpl(String orderNo) {

        return null;
    }

    @Override
    protected void modifyImpl(Order order, Diff diff) {

        Iterator<Change> iterator = ObjectTrackingUtil.getCommonChange(diff).iterator();

        // order modify or orderDetail DDL
        orderDetailDdlHandler(order, iterator);
        if (iterator.hasNext()) {
            orderDao.updateById(OrderConverter.INSTANCE.toOrderPO(order));
        }

    }

    @SuppressWarnings("all")
    private void orderDetailDdlHandler(Order order, Iterator<Change> iterator) {
        Set<OrderDetail> waitModifyOrderDetails = new HashSet<>();
        List<OrderDetail> waitSaveOrderDetails = new ArrayList<>();
        List<String> waitDeleteOrderDetails = new ArrayList<>();
        while (iterator.hasNext()) {
            Change change = iterator.next();
            Optional<Object> affectedObject = change.getAffectedObject();
            if (!affectedObject.isPresent() || !(affectedObject.get() instanceof OrderDetail)) {
                continue;
            }
            OrderDetail od = (OrderDetail) affectedObject.get();
            if (change instanceof ValueChange) {
                waitModifyOrderDetails.add(od);
                continue;
            } else if (change instanceof NewObject) {
                waitSaveOrderDetails.add(od);
                continue;
            } else if (change instanceof ObjectRemoved) {
                waitDeleteOrderDetails.add(od.getOrderNo());
            }
            iterator.remove();
        }

        // mybatis插件生成对于唯一凭证去更新

    }

    @Override
    public Order find(OrderNoVO orderNo) {
        return this.findByIdImpl(orderNo.getOrderNo());
    }

    public static void main(String[] args) throws ValidationException {
        Order order = new Order(new PriceVO(1, 2), new UidVO("111"));
        order.init("o1");
        order.setOrderDetails(new ArrayList<OrderDetail>(){
            {
                add(new OrderDetail(1L, "11"));
                add(new OrderDetail(3L, "33"));
            }
        });
        Order order2 = new Order(new PriceVO(10, 20), new UidVO("111"));
        order2.init("o1");
        order2.setOrderDetails(new ArrayList<OrderDetail>(){
            {
                add(new OrderDetail(2L, "22"));
                add(new OrderDetail(13L, "11"));
                add(new OrderDetail(14L, "66"));
            }
        });

        List<Change> commonChange = ObjectTrackingUtil.getCommonChange(ObjectTrackingUtil.compare(order, order2));
        for (Change change : commonChange) {
            System.out.println(change);

        }
    }
}
