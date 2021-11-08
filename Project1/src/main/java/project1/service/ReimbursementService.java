package project1.service;

import java.util.ArrayList;

import project1.dao.ReimbursementDao;
import project1.model.Reimbursement;

public class ReimbursementService {
	public static boolean addReimbursement(Reimbursement newReimbursement) {
		return ReimbursementDao.addReimbursement(newReimbursement);
	}

	public static boolean updateReimbursementResolve(String resolver,String status  ,int id) {

		return ReimbursementDao.updateReimbursementResolve(resolver, status, id);
	}

	public static ArrayList<Reimbursement> getReimbursementsByUser(String user) {

		return ReimbursementDao.getReimbursementsByUser(user);
	}

	public static ArrayList<Reimbursement> getReimbursementsByStatus(String selection) {

		return ReimbursementDao.getReimbursementsByStatus(selection);
	}

	public static ArrayList<Reimbursement> getAllReimbursements() {
		return ReimbursementDao.getAllReimbursements();
	}
}
