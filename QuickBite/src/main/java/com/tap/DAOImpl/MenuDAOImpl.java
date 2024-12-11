package com.tap.DAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.DBUtils.DBUtils;
import com.tap.model.Menu;

public class MenuDAOImpl implements MenuDAO{
	private Connection con;
    private PreparedStatement pstmt;
    private int status;
    private Statement stmt;
    private ResultSet resultSet;
    private Menu menu;
    
    private ArrayList<Menu> menuList = new ArrayList<>();
    
    private final String INSERT_MENU = "insert into `menu`(`restaurantId`, `menuName`, `Price`, `description`, `isAvailable`, `imgPath`) values (?, ?, ?, ?, ?, ?)";
    private final String DELETE_MENU = "delete from `menu` where `menuId`=?";
    private final String FETCH_ALL_MENUS = "select * from menu";
    private final String FETCH_ON_MENU_ID = "select * from menu where `menuId`=?";
    private final String UPDATE_MENU_ON_ID = "update `menu` set `restaurantId`=?, `menuName`=?, `Price`=?, `description`=?, `isAvailable`=?, `imgPath`=? where `menuId`=?";
    
    public MenuDAOImpl() {
        try {
            con = DBUtils.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int insertMenu(Menu menu) {
        try {
            pstmt = con.prepareStatement(INSERT_MENU);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setFloat(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setString(5, menu.getIsAvailable());
            pstmt.setString(6, menu.getImgPath());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteMenu(int menuId) {
        try {
            pstmt = con.prepareStatement(DELETE_MENU);
            pstmt.setInt(1, menuId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Menu> fetchAll() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(FETCH_ALL_MENUS);
            menuList = extractMenuFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public int updateMenu(Menu menu) {
        try {
            pstmt = con.prepareStatement(UPDATE_MENU_ON_ID);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setFloat(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setString(5, menu.getIsAvailable());
            pstmt.setString(6, menu.getImgPath());
            pstmt.setInt(7, menu.getMenuId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private ArrayList<Menu> extractMenuFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                menuList.add(new Menu(
                    resultSet.getInt("menuId"),
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("menuName"),
                    resultSet.getFloat("Price"),
                    resultSet.getString("description"),
                    resultSet.getString("isAvailable"),
                    resultSet.getString("imgPath")
                ));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return menuList;
    }

    @Override
    public Menu fetchMenu(int menuId) {
        try {
            pstmt = con.prepareStatement(FETCH_ON_MENU_ID);
            pstmt.setInt(1, menuId);
            resultSet = pstmt.executeQuery();
            menuList = extractMenuFromResultSet(resultSet);
            if (!menuList.isEmpty()) {
                menu = menuList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

	@Override
	public List<Menu> fetchAll(int restaurantId) {
		try {
			pstmt=con.prepareStatement("select * from menu where restaurantId=?");
			pstmt.setInt(1, restaurantId);
			resultSet= pstmt.executeQuery();
			menuList = extractMenuFromResultSet(resultSet);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menuList;
	}
	
}
