package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderDAO;
import com.tap.DBUtils.DBUtils;
import com.tap.model.Order;

public class OrderDAOImpl implements OrderDAO {
    
    private Connection con;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;
    private Order order;
    
    private ArrayList<Order> orderList = new ArrayList<>();
    
    private final String INSERT_ORDER = "insert into `order`(`restaurantId`, `userId`, `orderDate`, `totalAmount`, `status`, `paymentMode`) values (?, ?, ?, ?, ?, ?)";
    private final String DELETE_ORDER = "delete from `order` where `orderId`=?";
    private final String FETCH_ALL_ORDERS = "select * from `order`";
    private final String FETCH_ON_ORDER_ID = "select * from `order` where `orderId`=?";
    private final String UPDATE_ORDER_ON_ID = "update `order` set `restaurantId`=?, `userId`=?, `orderDate`=?, `totalAmount`=?, `status`=?, `paymentMode`=? where `orderId`=?";
    
    public OrderDAOImpl() {
        try {
            con = DBUtils.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int insertOrder(Order order) {
        try {
            pstmt = con.prepareStatement(INSERT_ORDER);
            pstmt.setInt(1, order.getRestaurantId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setString(3, order.getOrderDate());
            pstmt.setString(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(DELETE_ORDER);
            pstmt.setInt(1, orderId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Order> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_ORDERS);
            orderList = extractOrderFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int updateOrder(Order order) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_ON_ID);
            pstmt.setInt(1, order.getRestaurantId());
            pstmt.setInt(2, order.getUserId());
            pstmt.setString(3, order.getOrderDate());
            pstmt.setString(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());
            pstmt.setInt(7, order.getOrderId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private ArrayList<Order> extractOrderFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderList.add(new Order(
                    resultSet.getInt("orderId"),
                    resultSet.getInt("restaurantId"),
                    resultSet.getInt("userId"),
                    resultSet.getString("orderDate"),
                    resultSet.getString("totalAmount"),
                    resultSet.getString("status"),
                    resultSet.getString("paymentMode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order fetchOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(FETCH_ON_ORDER_ID);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            orderList = extractOrderFromResultSet(resultSet);
            if (!orderList.isEmpty()) {
                order = orderList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
