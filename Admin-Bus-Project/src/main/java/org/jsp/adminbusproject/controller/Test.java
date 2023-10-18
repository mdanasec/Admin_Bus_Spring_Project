package org.jsp.adminbusproject.controller;

import org.jsp.adminbusproject.AdminBusConfig;
import org.jsp.adminbusproject.dao.AdminDao;
import org.jsp.adminbusproject.dao.BusDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AdminBusConfig.class);
		AdminDao aDao = context.getBean(AdminDao.class);
		System.out.println(aDao.manager);
		BusDao bDao = context.getBean(BusDao.class);
		System.out.println(bDao.manager);
	}

}
