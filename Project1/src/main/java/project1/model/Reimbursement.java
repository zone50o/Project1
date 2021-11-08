package project1.model;

import java.sql.Timestamp;


public class Reimbursement {
	
	private static int nextID = 0;
	private int id;
	private double amount;
	private String submitted;
	private String resolved;
	private String description;
	private String receipt;
	private String author;
	private String resolver;
	private String status;
	private String type;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(double amount, String submitted, String resolved, String description, String receipt,
			String author, String resolver, String status, String type) {
		super();
		this.id = ++nextID;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int id, double amount, String submitted, String resolved, String description,
			String receipt, String author, String resolver, String status, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public double getAmount() {return amount;}
	public void setAmount(double amount) {this.amount = amount;}

	public String getSubmitted() {return submitted;}

	public void setSubmitted(String submitted) {this.submitted = submitted;}

	public String getResolved() {return resolved;}

	public void setResolved(String resolved) {this.resolved = resolved;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public String getReceipt() {return receipt;}
	public void setReceipt(String receipt) {this.receipt = receipt;}

	public String getAuthorID() {return author;}
	public void setAuthorID(String author) {this.author = author;}

	public String getResolverID() {return resolver;}
	public void setResolverID(String resolver) {this.resolver = resolver;}

	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}

	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	public static void setNextID(int nextID) {Reimbursement.nextID = nextID;}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}
	
	
}
