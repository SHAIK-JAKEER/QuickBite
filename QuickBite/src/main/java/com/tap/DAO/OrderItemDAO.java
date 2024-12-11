package com.tap.DAO;

import java.util.List;

import com.tap.model.OrderItem;

public interface OrderItemDAO {
	int insertOrderItem(OrderItem oi);
    int deleteOrderItem(int orderItemId);
    List<OrderItem> fetchAll();
    OrderItem fetchOrderItem(int orderItemId);
    int updateOrderItem(OrderItem oi);
}
