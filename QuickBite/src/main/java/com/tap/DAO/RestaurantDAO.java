package com.tap.DAO;

import java.util.List;

import com.tap.model.Restaurant;

public interface RestaurantDAO {

	int insertRestaurant(Restaurant r);
    int deleteRestaurant(int restaurantId);
    List<Restaurant> fetchAll();
    Restaurant fetchRestaurant(int restaurantId);
    int updateRestaurant(Restaurant r);

}
