package org.jsp.adminbusproject.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.jsp.adminbusproject.AdminBusConfig;
import org.jsp.adminbusproject.dao.AdminDao;
import org.jsp.adminbusproject.dao.BusDao;
import org.jsp.adminbusproject.dto.Admin;
import org.jsp.adminbusproject.dto.Bus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AdminBusController {

	static AdminDao aDao;
	static BusDao bDao;
	static Scanner s = new Scanner(System.in);

	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(AdminBusConfig.class);
		aDao = context.getBean(AdminDao.class);
		bDao = context.getBean(BusDao.class);
	}

	public static void main(String[] args) {
		while (true) {
			displayMenu();
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				saveAdmin();
				break;
			case 2:
				saveBus();
				break;
			case 3:
				varifyById();
				break;
			case 4:
				updateAdmin();
				break;
			case 5:
				updateBus();
				break;
			case 6:
				varifyByphoneAndPass();
				break;
			case 7:
				varifyByEmailAndPass();
				break;
			case 8:
				deleteById();
				break;
			case 9:
				findBusesByAdminId();
				break;
			case 10:
				findBusesByLocationAndDate();
				break;
			default:
				System.err.println("Invalid choice. Please try again.");
			}
		}
	}

	public static void displayMenu() {
		System.out.println("Menu:");
		System.out.println("1. Save Admin");
		System.out.println("2. Save Bus with Admin ID");
		System.out.println("3. Find Admin by ID");
		System.out.println("4. Update Admin");
		System.out.println("5. Update Bus");
		System.out.println("6. Verify Admin by Phone and Password");
		System.out.println("7. Verify Admin by Email and Password");
		System.out.println("8. Delete Admin by ID");
		System.out.println("9. Find Buses By Admin ID");
		System.out.println("10. Find Buses By Location and Date");
		System.out.print("Enter your choice: ");
	}

	public static void saveAdmin() {
		Admin a = new Admin();
		System.out.println("name");
		a.setName(s.next()); // Set the name property
		System.out.println("phone");
		a.setPhone(s.nextLong()); // Set the phone property

		System.out.println("email");
		a.setEmail(s.next()); // Set the email property

		System.out.println("gst");
		a.setGst(s.nextDouble()); // Set the GST property

		System.out.println("password");
		a.setPassword(s.next()); // Set the password property

		a = aDao.saveAdmin(a);
		System.out.println("Administrator saved with Id: " + a.getId());
	}

	public static void saveBus() {
		System.out.println("Enter the Admin id to add a Bus:");
		int admin_id = s.nextInt();

		Bus b = new Bus();
		System.out.println("Enter Bus Name:");
		b.setName(s.next());
		System.out.println("Enter From Location:");
		b.setFrom_Loc(s.next());
		System.out.println("Enter To Location:");
		b.setTo_loc(s.next());

		System.out.print("Enter the departure date (yyyy-MM-dd): ");
		s.nextLine();
		String dateInput = s.nextLine();

		// Parse the input to a LocalDate
		LocalDate dateOfDeparture = LocalDate.parse(dateInput);

		System.out.println("Enter Cost per Seat:");
		b.setCos_per_seat(s.nextDouble());

		b = bDao.saveBus(b, admin_id);
		if (b != null) {
			System.out.println("Bus saved with Id:" + b.getId());
		} else
			System.err.println("Invalid admin Id !!");
	}

	public static void varifyById() {
		System.out.println("Enter admin id to find employee");
		int admin_id = s.nextInt();
		Admin a = aDao.findAdmin(admin_id);
		if (a != null) {
			System.out.println("name : " + a.getName());
			System.out.println("phone : " + a.getPhone());
			System.out.println("GST : " + a.getGst());
			System.out.println("email : " + a.getEmail());
		} else
			System.err.println("invalid id !!");
	}

	public static void updateAdmin() {
		System.out.println("Enter admin id to find the admin to update:");
		int admin_id = s.nextInt();

		Admin a = aDao.findAdmin(admin_id);

		if (a != null) {
			System.out.println("Enter new information for the admin:");

			System.out.println("Name:");
			a.setName(s.next());

			System.out.println("Phone:");
			a.setPhone(s.nextLong());

			System.out.println("Email:");
			a.setEmail(s.next());

			System.out.println("GST:");
			a.setGst(s.nextDouble());

			System.out.println("Password:");
			a.setPassword(s.next());

			// Update the admin's information in the database
			a = aDao.updateAdmin(a);

			System.out.println("Admin updated with id: " + a.getId());
		} else
			System.err.println("Admin with the given ID not found!");
	}

	public static void updateBus() {
		System.out.println("Enter the admin id to update a bus record:");
		int admin_id = s.nextInt();
		
		Bus b = new Bus();
		System.out.println("Enter the updated bus name:");
		b.setName(s.next());
		System.out.println("Enter the updated 'from' location:");
		b.setFrom_Loc(s.next());
		System.out.println("Enter the updated 'to' location:");
		b.setTo_loc(s.next());
		System.out.println("Enter the updated cost per seat:");
		b.setCos_per_seat(s.nextDouble());
		b = bDao.updateBus(b, admin_id);

		if (b != null)
			System.out.println("Bus updated with id " + b.getId());
		else
			System.err.println("Invalid admin Id");
	}

	public static void findBusesByAdminId() {
		System.out.println("Enter admin id to find associated buses:");
		int admin_id = s.nextInt();

		List<Bus> buses = bDao.findBusesByAdminId(admin_id);
		if (buses.size() > 0) {
			for (Bus bus : buses) {
				System.out.println("Bus ID: " + bus.getId());
				System.out.println("Bus name" + bus.getName());
				System.out.println("Bus From " + bus.getFrom_Loc());
				System.out.println("Bus To " + bus.getTo_loc());
				System.out.println("Bus deprature " + bus.getDate_of_deprature());
				System.out.println("Bus cost " + bus.getCos_per_seat());
				System.out.println("--------------------------------");
			}
		} else {
			System.err.println("you have entered wrong id ");

		}
	}

	public static void varifyByphoneAndPass() {
		System.out.println("enter your phone");
		long phone = s.nextLong();
		System.out.println("enter your password");
		String password = s.next();

		Admin a = aDao.verifyByAdmin(phone, password);
		if (a != null) {
			System.out.println("name : " + a.getName());
			System.out.println("phone : " + a.getPhone());
			System.out.println("email : " + a.getEmail());
			System.out.println("name : " + a.getGst());
			System.out.println("---------------------");
		} else
			System.err.println("invalid phone and password");
	}

	public static void varifyByEmailAndPass() {
		System.out.println("enter your phone");
		String email = s.next();
		System.out.println("enter your password");
		String password = s.next();

		Admin a = aDao.verifyByAdmin(email, password);
		if (a != null) {
			System.out.println("name : " + a.getName());
			System.out.println("phone : " + a.getPhone());
			System.out.println("email : " + a.getEmail());
			System.out.println("name : " + a.getGst());
			System.out.println("----------------------");
		} else
			System.err.println("invalid email and password !!");
	}

	public static void deleteById() {
		System.out.println("enter id");
		int id = s.nextInt();
		aDao.deleteAdmin(id);
	}

	public static void findBusesByLocationAndDate() {
		System.out.println("Enter 'from' location:");
		String fromLoc = s.next();
		System.out.println("Enter 'to' location:");
		String toLoc = s.next();

		System.out.print("Enter the departure date (yyyy-MM-dd): ");
		s.nextLine();
		String dateInput = s.nextLine();

		// Parse the input to a LocalDate
		LocalDate date_of_departure = LocalDate.parse(dateInput);
		List<Bus> buses = bDao.findBusesByLocationAndDate(fromLoc, toLoc, date_of_departure);

		if (buses.size() > 0) {
			for (Bus bus : buses) {
				System.out.println("Bus ID: " + bus.getId());
				System.out.println("Bus name " + bus.getName());
				System.out.println("From : " + bus.getFrom_Loc());
				System.out.println("TO : " + bus.getTo_loc());
				System.out.println("bus of deprature  " + bus.getDate_of_deprature());

				System.out.println("-------------------------");
			}
		} else
			System.err.println("dont have any buses inthis root ");
	}

}
