package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.DBUtils.DBUtils;
import com.tap.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO{
	private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private int status;
    private Restaurant restaurant;
    
    // List to hold restaurants
    private List<Restaurant> restaurantList = new ArrayList<>();
    
    // SQL Queries
    private static final String INSERT_RESTAURANT = "INSERT INTO `restaurant`(`restaurantName`, `deliveryTime`, `cuisineType`, `address`, `ratings`, `isActive`, `imgPath`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_RESTAURANT = "DELETE FROM `restaurant` WHERE `restaurantId`=?";
    private static final String FETCH_ALL_RESTAURANTS = "SELECT * FROM `restaurant`";
    private static final String FETCH_ON_ID = "SELECT * FROM `restaurant` WHERE `restaurantId`=?";
    private static final String UPDATE_RESTAURANT_ON_ID = "UPDATE `restaurant` SET `restaurantName`=?, `deliveryTime`=?, `cuisineType`=?, `address`=?, `ratings`=?, `isActive`=?, `imgPath`=? WHERE `restaurantId`=?";
    
    public RestaurantDAOImpl() {
        try {
            con = DBUtils.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertRestaurant(Restaurant r) {
        try {
            pstmt = con.prepareStatement(INSERT_RESTAURANT);
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setInt(2, r.getDeliveryTime());
            pstmt.setString(3, r.getCuisineType());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRatings());
            pstmt.setString(6, r.getIsActive());
            pstmt.setString(7, r.getImgPath());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return status;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {
        try {
            pstmt = con.prepareStatement(DELETE_RESTAURANT);
            pstmt.setInt(1, restaurantId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return status;
    }

    @Override
    public List<Restaurant> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_RESTAURANTS);
            restaurantList = extractRestaurantFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return restaurantList;
    }

    @Override
    public Restaurant fetchRestaurant(int restaurantId) {
        try {
            pstmt = con.prepareStatement(FETCH_ON_ID);
            pstmt.setInt(1, restaurantId);
            resultSet = pstmt.executeQuery();
            List<Restaurant> restaurantList = extractRestaurantFromResultSet(resultSet);
            if (!restaurantList.isEmpty()) {
                restaurant = restaurantList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return restaurant;
    }

    @Override
    public int updateRestaurant(Restaurant r) {
        try {
            pstmt = con.prepareStatement(UPDATE_RESTAURANT_ON_ID);
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setInt(2, r.getDeliveryTime());
            pstmt.setString(3, r.getCuisineType());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRatings());
            pstmt.setString(6, r.getIsActive());
            pstmt.setString(7, r.getImgPath());
            pstmt.setInt(8, r.getRestaurantId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return status;
    }

    private List<Restaurant> extractRestaurantFromResultSet(ResultSet resultSet) {
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                restaurantList.add(new Restaurant(
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("restaurantName"),
                    resultSet.getInt("deliveryTime"),
                    resultSet.getString("cuisineType"),
                    resultSet.getString("address"),
                    resultSet.getFloat("ratings"),
                    resultSet.getString("isActive"),
                    resultSet.getString("imgPath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    private void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
