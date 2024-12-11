package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.DBUtils.DBUtils;
import com.tap.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
    
    private Connection con;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;
    private OrderItem orderItem;
    
    private ArrayList<OrderItem> orderItemList = new ArrayList<>();
    
    private final String INSERT_ORDER_ITEM = "insert into `order_item`(`orderId`, `menuId`, `quantity`, `subTotal`) values (?, ?, ?, ?)";
    private final String DELETE_ORDER_ITEM = "delete from `order_item` where `orderItemId`=?";
    private final String FETCH_ALL_ORDER_ITEMS = "select * from order_item";
    private final String FETCH_ON_ORDER_ITEM_ID = "select * from order_item where `orderItemId`=?";
    private final String UPDATE_ORDER_ITEM_ON_ID = "update `order_item` set `orderId`=?, `menuId`=?, `quantity`=?, `subTotal`=? where `orderItemId`=?";
    
    public OrderItemDAOImpl() {
        try {
            con = DBUtils.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int insertOrderItem(OrderItem oi) {
        try {
            pstmt = con.prepareStatement(INSERT_ORDER_ITEM);
            pstmt.setInt(1, oi.getOrderId());
            pstmt.setInt(2, oi.getMenuId());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setFloat(4, oi.getSubTotal());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(DELETE_ORDER_ITEM);
            pstmt.setInt(1, orderItemId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderItem> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_ORDER_ITEMS);
            orderItemList = extractOrderItemFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public int updateOrderItem(OrderItem oi) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_ITEM_ON_ID);
            pstmt.setInt(1, oi.getOrderId());
            pstmt.setInt(2, oi.getMenuId());
            pstmt.setInt(3, oi.getQuantity());
            pstmt.setFloat(4, oi.getSubTotal());
            pstmt.setInt(5, oi.getOrderItemId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private ArrayList<OrderItem> extractOrderItemFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderItemList.add(new OrderItem(
                    resultSet.getInt("orderItemId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("menuId"),
                    resultSet.getInt("quantity"),
                    resultSet.getFloat("subTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public OrderItem fetchOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(FETCH_ON_ORDER_ITEM_ID);
            pstmt.setInt(1, orderItemId);
            resultSet = pstmt.executeQuery();
            orderItemList = extractOrderItemFromResultSet(resultSet);
            if (!orderItemList.isEmpty()) {
                orderItem = orderItemList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

}
