package com.jump.plus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jump.plus.connection.ConnectionManager;
import com.jump.plus.model.User;

public class UserDAOImp implements UserDAO {

	Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<User> getUserList() {
		
		final String statement = "SELECT * FROM user";
		List<User> users = new ArrayList<>();
		
		try (PreparedStatement pr = conn.prepareStatement(statement);
				ResultSet rs = pr.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp modifyDate = rs.getTimestamp("modify_date");
				Timestamp createDate = rs.getTimestamp("create_date");

				User user = new User(id, username, password, firstName, lastName, email, modifyDate, createDate);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserByName(String inputUsername) {
		final String statement = "SELECT * FROM user WHERE username=?";
		
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setString(1, inputUsername);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp modifyDate = rs.getTimestamp("modify_date");
				Timestamp createDate = rs.getTimestamp("create_date");

				return new User(id, username, password, firstName, lastName, email, modifyDate, createDate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User addNewUser(User user) {
		
		final String statement = "INSERT INTO user "
				+ "(user_id, username, password, first_name, last_name, email, create_date, modify_date)"
				+ "VALUES" 
				+ "(null, ?, ?, ?, ?, ?, NOW(), NOW());";
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setString(1, user.getUsername());
			pr.setString(2, user.getPassword());
			pr.setString(3, user.getFirstName());
			pr.setString(4, user.getLastName());
			pr.setString(5, user.getEmail());
			if (pr.executeUpdate() == 1) {
				return getUserByName(user.getUsername());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
