package org.jsp.adminbusproject.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.adminbusproject.dto.Admin;
import org.jsp.adminbusproject.dto.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BusDao {
	@Autowired
	public EntityManager manager;
	
	public Bus saveBus(Bus bus, int admin_id) {
		Admin a = manager.find(Admin.class, admin_id);
		if (a != null) {
			bus.setAdmin(a);// Assigning Admin for Bus
			a.getBuses().add(bus);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(bus);
			transaction.begin();
			transaction.commit();
			return bus;
		}
		return null;
	}
	
	public Bus updateBus(Bus bus, int admin_id) {
		Admin a = manager.find(Admin.class, admin_id);
		if (a != null) {
			bus.setAdmin(a);// Assigning Admin for Bus
			a.getBuses().add(bus);
			EntityTransaction transaction = manager.getTransaction();
			manager.merge(bus);
			transaction.begin();
			transaction.commit();
			return bus;
		}
		return null;
	}
	
	public Bus findBus(int id) {
		return manager.find(Bus.class, id); 
	}


	public List<Bus> findBusesByAdminId(int adminId) {
	    String jpql = "SELECT b FROM Bus b WHERE b.admin.id = ?1";
	    Query query = manager.createQuery(jpql);
	    query.setParameter(1, adminId);
	    
	    return query.getResultList();
	}
	
	public List<Bus> findBusesByLocationAndDate(String fromLoc, String toLoc, LocalDate date_of_departure) {
	    String jpql = "SELECT b FROM Bus b WHERE b.from_Loc = ?1 AND b.to_loc = ?2 AND b.date_of_deprature = ?3";
	    Query query = manager.createQuery(jpql);
	    query.setParameter(1, fromLoc);
	    query.setParameter(2, toLoc);
	    query.setParameter(3, date_of_departure);
	    
	    return query.getResultList();
	}


	
}
