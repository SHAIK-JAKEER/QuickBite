package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderHistoryDAO;
import com.tap.DBUtils.DBUtils;
import com.tap.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    
    private Connection con;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;
    private OrderHistory orderHistory;
    
    private ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
    
    private final String INSERT_ORDER_HISTORY = "insert into `order_history`(`orderId`, `userId`, `orderDate`, `totalAmount`, `status`) values (?, ?, ?, ?, ?)";
    private final String DELETE_ORDER_HISTORY = "delete from `order_history` where `orderHistoryId`=?";
    private final String FETCH_ALL_ORDER_HISTORY = "select * from order_history";
    private final String FETCH_ON_ORDER_HISTORY_ID = "select * from order_history where `orderHistoryId`=?";
    private final String UPDATE_ORDER_HISTORY_ON_ID = "update `order_history` set `orderId`=?, `userId`=?, `orderDate`=?, `totalAmount`=?, `status`=? where `orderHistoryId`=?";
    
    public OrderHistoryDAOImpl() {
        try {
            con = DBUtils.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int insertOrderHistory(OrderHistory oh) {
        try {
            pstmt = con.prepareStatement(INSERT_ORDER_HISTORY);
            pstmt.setInt(1, oh.getOrderId());
            pstmt.setInt(2, oh.getUserId());
            pstmt.setString(3, oh.getOrderDate());
            pstmt.setFloat(4, oh.getTotalAmount());
            pstmt.setString(5, oh.getStatus());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteOrderHistory(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(DELETE_ORDER_HISTORY);
            pstmt.setInt(1, orderHistoryId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderHistory> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_ORDER_HISTORY);
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public int updateOrderHistory(OrderHistory oh) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_HISTORY_ON_ID);
            pstmt.setInt(1, oh.getOrderId());
            pstmt.setInt(2, oh.getUserId());
            pstmt.setString(3, oh.getOrderDate());
            pstmt.setFloat(4, oh.getTotalAmount());
            pstmt.setString(5, oh.getStatus());
            pstmt.setInt(6, oh.getOrderHistoryId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderHistoryList.add(new OrderHistory(
                    resultSet.getInt("orderHistoryId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("userId"),
                    resultSet.getString("orderDate"),
                    resultSet.getFloat("totalAmount"),
                    resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory fetchOrderHistory(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(FETCH_ON_ORDER_HISTORY_ID);
            pstmt.setInt(1, orderHistoryId);
            resultSet = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
            if (!orderHistoryList.isEmpty()) {
                orderHistory = orderHistoryList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

}
