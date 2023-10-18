package org.jsp.adminbusproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String from_Loc;
	@Column(nullable = false)
	private String to_loc;
	@CreationTimestamp
	private LocalDate date_of_deprature;
	private double cos_per_seat;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom_Loc() {
		return from_Loc;
	}

	public void setFrom_Loc(String from_Loc) {
		this.from_Loc = from_Loc;
	}

	public String getTo_loc() {
		return to_loc;
	}

	public void setTo_loc(String to_loc) {
		this.to_loc = to_loc;
	}

	public LocalDate getDate_of_deprature() {
		return date_of_deprature;
	}

	public void setDate_of_deprature(LocalDate date_of_deprature) {
		this.date_of_deprature = date_of_deprature;
	}

	public double getCos_per_seat() {
		return cos_per_seat;
	}

	public void setCos_per_seat(double cos_per_seat) {
		this.cos_per_seat = cos_per_seat;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
