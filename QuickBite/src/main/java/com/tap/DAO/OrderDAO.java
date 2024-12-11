package com.tap.DAO;

import java.util.List;

import com.tap.model.Order;

public interface OrderDAO {
	int insertOrder(Order order);
    int deleteOrder(int orderId);
    List<Order> fetchAll();
    Order fetchOrder(int orderId);
    int updateOrder(Order order);
}
