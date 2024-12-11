package com.tap.DAO;

import java.util.List;

import com.tap.model.OrderHistory;

public interface OrderHistoryDAO {
	int insertOrderHistory(OrderHistory oh);
    int deleteOrderHistory(int orderHistoryId);
    List<OrderHistory> fetchAll();
    OrderHistory fetchOrderHistory(int orderHistoryId);
    int updateOrderHistory(OrderHistory oh);
}
