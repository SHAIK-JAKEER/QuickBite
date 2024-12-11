package com.tap.DAO;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDAO {
	 int insertMenu(Menu menu);
	    int deleteMenu(int menuId);
	    List<Menu> fetchAll();
	    Menu fetchMenu(int restaurantId);
	    int updateMenu(Menu menu);
		List<Menu> fetchAll(int restaurantId);
}
