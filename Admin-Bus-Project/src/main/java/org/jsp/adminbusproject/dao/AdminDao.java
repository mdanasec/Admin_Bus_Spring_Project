package org.jsp.adminbusproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.adminbusproject.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AdminDao {
	@Autowired
	public EntityManager manager;
	
	public Admin saveAdmin(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(admin);
		transaction.begin();
		transaction.commit();
		return admin; 
	}
	
	public Admin updateAdmin(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(admin);
		transaction.begin();
		transaction.commit();
		return admin; 
	}
	
	public Admin findAdmin(int id) {
		return manager.find(Admin.class, id); 
	}
	
	public Admin verifyByAdmin(long phone, String password) {
		String jpql="select a from Admin a where a.phone=?1 and a.password=?2";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		
		try {
			return(Admin) q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	
	}
	
	public Admin verifyByAdmin(String email, String password) {
		String jpql="select a from Admin a where a.email=?1 and a.password=?2";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, email);
		q.setParameter(2, password);
		
		try {
			return(Admin) q.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	
	}
	
	public boolean deleteAdmin(int id) {
		Admin a = findAdmin(id);
		if(a!=null) {
			EntityTransaction transaction = manager.getTransaction();
			manager.remove(a);
			transaction.begin();
			transaction.commit();
			return true;
		} 
		return false;
	
	}
	
	
}
