package project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import project1.model.Reimbursement;

public class ReimbursementDao {

//	public static void main(String[] args) {
//		System.out.println(getAllReimbursements());
//	}

	public static ArrayList<Reimbursement> getAllReimbursements() {
		ArrayList<Reimbursement> allReimbursements = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM tickets";

			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			int nextInt = 0;
			
			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				if (id > nextInt) {
					nextInt = id;
				}
				double amount = rs.getDouble("reimb_amount");
				String submitted = getFormattedDate(rs.getTimestamp("reimb_submitted"));
				String resolved = getFormattedDate(rs.getTimestamp("reimb_resolved"));
				String description = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				String author = rs.getString("author");
				String resolver = rs.getString("resolver");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				allReimbursements.add(new Reimbursement(id, amount, submitted, resolved, description, receipt, author,
						resolver, status, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allReimbursements;
	}

	public static ArrayList<Reimbursement> getReimbursementsByUser(String user) {
		ArrayList<Reimbursement> allReimbursements = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM tickets WHERE author = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitted = getFormattedDate(rs.getTimestamp("reimb_submitted"));
				String resolved = getFormattedDate(rs.getTimestamp("reimb_resolved"));
				String description = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				String author = rs.getString("author");
				String resolver = rs.getString("resolver");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				allReimbursements.add(new Reimbursement(id, amount, submitted, 
						resolved, description, receipt, author,
						resolver, status, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allReimbursements;
	}
	
	public static ArrayList<Reimbursement> getReimbursementsByStatus(String selection) {
		ArrayList<Reimbursement> allReimbursements = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM tickets WHERE reimb_status = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, selection);

			ResultSet rs = ps.executeQuery();
			

			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				
				String submitted = getFormattedDate(rs.getTimestamp("reimb_submitted"));
				String resolved = getFormattedDate(rs.getTimestamp("reimb_resolved"));
				String description = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				String author = rs.getString("author");
				String resolver = rs.getString("resolver");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				allReimbursements.add(new Reimbursement(id, amount, submitted, 
						resolved, description, receipt, author,
						resolver, status, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allReimbursements;
	}
	
	public static String getFormattedDate(Timestamp ts) {
		if(ts==null) {
			return "";
		}
		
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		return customFormatter.format(ts.toLocalDateTime());
	}

	public static int getUserIDByName(String user) {

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT ers_users_id FROM ers_users eu " 
						+ "WHERE CONCAT(eu.user_first_name, ' '"
						+ ", eu.user_last_name) = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt("ers_users_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static int getStatusID(String status) {

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT reimb_status_id FROM " 
						+ "ers_reimbursement_status ers WHERE " 
						+ "reimb_status = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, status);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt("reimb_status_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static int getTypeID(String type) {

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT reimb_type_id FROM" 
						+ " ers_reimbursement_type ers " 
						+ "WHERE reimb_type = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt("reimb_type_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static boolean addReimbursement(Reimbursement newReimbursement) {

		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "INSERT INTO ers_reimbursement (reimb_amount, " 
					+ "reimb_submitted, reimb_description"
					+ ", reimb_author, reimb_status_id, " 
					+ "reimb_type_id) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, newReimbursement.getAmount());
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(3, newReimbursement.getDescription());
			ps.setInt(4, getUserIDByName(newReimbursement.getAuthorID()));
			ps.setInt(5, getStatusID("Pending"));
			ps.setInt(6, getTypeID(newReimbursement.getType()));

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	

	public static boolean updateReimbursementResolve(String resolver,String status  ,int id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "UPDATE ers_reimbursement SET reimb_resolved = ?"
					+ ", reimb_resolver = ?, reimb_status_id  = ?  WHERE reimb_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			if(status.equals("Pending")) {
				ps.setTimestamp(1, null);
				ps.setNull(2, java.sql.Types.INTEGER); 
			}else {
				ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
				ps.setInt(2, getUserIDByName(resolver));
			}
			
			ps.setInt(3, getStatusID(status));
			ps.setInt(4, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
