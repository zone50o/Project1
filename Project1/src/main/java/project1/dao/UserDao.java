package project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project1.model.User;

public class UserDao {
	public static User getUser(String username) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String query = "SELECT ers_users_id, ers_password, concat(user_first_name,' ' ,user_last_name) "
					+ "AS name, user_email, user_role_id "
					+ "FROM ers_users eu "
					+ "WHERE ers_username = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("ers_users_id");
				String password = rs.getString("ers_password");
				String fullName = rs.getString("name");
				String email = rs.getString("user_email");
				int role = rs.getInt("user_role_id");
				return new User(id,username,password,fullName,email, role);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
