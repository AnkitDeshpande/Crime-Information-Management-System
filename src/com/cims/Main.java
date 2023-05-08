
package com.cims;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import com.cims.entity.Crime;
import com.cims.entity.Public;
import com.cims.exceptions.CrimeException;
import com.cims.exceptions.DuplicateDataException;
import com.cims.exceptions.InvalidDataExeception;
import com.cims.exceptions.InvalidDetailsException;
import com.cims.exceptions.InvalidInputException;
import com.cims.services.CrimeService;
import com.cims.services.CrimeServiceImp;
import com.cims.services.PublicServeImp;
import com.cims.services.PublicService;
import com.cims.utility.Admin;
import com.cims.utility.FileExists;
import com.cims.utility.Genrate_ID;

public class Main {

	private static void adminFunctionilty(Scanner sc, Map<Integer, Crime> crimes, Map<String, Public> publics)
			throws InvalidDataExeception {
		// TODO Auto-generated method stub

		adminLogin(sc);

		CrimeService cService = new CrimeServiceImp();

		int choice = 0;
		try {
			do {
				System.out.println("=======================================================");
				System.out.println("Enter the operation you want to perform :");
				System.out.println("1-->Add a new Crime/Register a Crime.");
				System.out.println("2-->Update a Crime.");
				System.out.println("3-->View all criminal record.");
				System.out.println("4-->Remove a Criminal from record.");
				System.out.println("5-->Logout.");
				choice = sc.nextInt();

				switch (choice) {

				case 1:
					String added = AdminAddCrime(sc, crimes, cService);
					System.out.println(added);
					break;
				case 2:
					String upt = adminUpdateCrime(sc, crimes, cService);
					System.out.println(upt);
					break;
				case 3:
					display(crimes, cService);
					break;
				case 4:
					rmCrime(sc, crimes, cService);
					break;
				case 5:
					System.out.println("Admin logged out successfully.");
					System.out.println("=======================================================");
					break;
				default:
					throw new InvalidInputException("Invalid Input. Please Enter the correct number between 0 to 5.");

				}

			} while (choice <= 4);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	private static void rmCrime(Scanner sc, Map<Integer, Crime> crimes, CrimeService cService) throws CrimeException {
		// TODO Auto-generated method stub
		System.out.println("Enter Crime ID to be deleted.");
		int id = sc.nextInt();
		cService.deleteCrime(id, crimes);
	}

	private static void display(Map<Integer, Crime> crimes, CrimeService cService) throws CrimeException {
		// TODO Auto-generated method stub
		cService.viewAllCrimes(crimes);
	}

	private static String adminUpdateCrime(Scanner sc, Map<Integer, Crime> crimes, CrimeService cService)
			throws CrimeException {
		// TODO Auto-generated method stub
		String str = null;
		System.out.println("Enter Crime ID.");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");
		System.out.println("Enter the criminal name.");
		String name = sc.next();
		System.out.println("Enter Crime category.");
		String category = sc.next();
		System.out.println("Enter Police station Area.");
		String ps_area = sc.next();
		System.out.println("Describe the crime.");
		String desc = sc.next();

		Crime c = new Crime(id, category, desc, ps_area, LocalDate.now(), name);

		str = cService.updateCrime(id, c, crimes);
		return str;
	}

	private static String AdminAddCrime(Scanner sc, Map<Integer, Crime> crimes, CrimeService cService) {
		// TODO Auto-generated method stub
		String str = null;
		System.out.println("Enter Crime Details.");
		System.out.println("Enter Crime category.");
		String category = sc.next();
		System.out.println("Describe the crime.");
		String desc = sc.next();
		System.out.println("Enter Police station Area.");
		String ps_area = sc.next();
		System.out.println("Enter the name of Criminal.");
		String name = sc.next();
		Crime c = new Crime(Genrate_ID.genrateID(), category, desc, ps_area, LocalDate.now(), name);
		str = cService.addCrime(c, crimes);
		return str;
	}

	public static void adminLogin(Scanner sc) throws InvalidDataExeception {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {
			System.out.println("Login successful, Welcom Ankit.");
		} else {
			throw new InvalidDataExeception("Please Enter correct Details.");
		}
	}

//===================================================================================================================================================================
	private static void publicFunctionality(Scanner sc, Map<String, Public> publics, Map<Integer, Crime> crimes)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
		CrimeService cs1 = new CrimeServiceImp();
		PublicService ps1 = new PublicServeImp();

		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();

		publicLogin(email, pass, publics, ps1);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to report a crime.");
				System.out.println("Press 2 view all crimes.");
				System.out.println("Press 3 to Update My reported Crime.");
				System.out.println("Press 4 view my details");
				System.out.println("Press 5 to logout");

				choice = sc.nextInt();

				switch (choice) {
				case 1:
					publicReportCrime(sc, crimes, ps1);
					break;
				case 2:
					publicViewAllCrime(crimes, ps1);
					break;
				case 3:
					publicUpdateCrime(sc, crimes, ps1);
					break;
				case 4:
					publicViewMyDetails(email, publics, ps1);
					break;
				case 5:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void publicViewMyDetails(String email, Map<String, Public> publics, PublicService ps1) {
		// TODO Auto-generated method stub
		Public p = ps1.viewMyDetails(email, publics);
		System.out.println(p.toString());

	}

	private static void publicUpdateCrime(Scanner sc, Map<Integer, Crime> crimes, PublicService ps1)
			throws CrimeException {
		// TODO Auto-generated method stub
		System.out.println("Enter the id of the crime you reported : ");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");
		System.out.println("Enter the criminal name.");
		String name = sc.next();
		System.out.println("Enter Crime category.");
		String category = sc.next();
		System.out.println("Enter Police station Area.");
		String ps_area = sc.next();
		System.out.println("Describe the crime.");
		String desc = sc.next();

		Crime c = new Crime(id, category, desc, ps_area, LocalDate.now(), name);
		System.out.println(ps1.updateMyCrime(id, c, crimes));
	}

	private static void publicViewAllCrime(Map<Integer, Crime> crimes, PublicService ps1) throws CrimeException {
		// TODO Auto-generated method stub

		ps1.viewAllCrimes(crimes);

	}

	private static void publicReportCrime(Scanner sc, Map<Integer, Crime> crimes, PublicService ps1) {
		// TODO Auto-generated method stub
		System.out.println("Enter Crime Details.");
		System.out.println("Enter Crime category.");
		String category = sc.next();
		System.out.println("Describe the crime.");
		String desc = sc.next();
		System.out.println("Enter Police station Area.");
		String ps_area = sc.next();
		System.out.println("Enter the name of Criminal.");
		String name = sc.next();
		Crime c = new Crime(Genrate_ID.genrateID(), category, desc, ps_area, LocalDate.now(), name);
		System.out.println(ps1.reportCrime(c, crimes));
	}

	private static void publicLogin(String email, String pass, Map<String, Public> publics, PublicService ps1)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
		ps1.login(email, pass, publics);
		System.out.println("Customer has successfully login.\n");

	}

	private static void newUser(Scanner sc, Map<String, Public> publics) throws DuplicateDataException {
		// TODO Auto-generated method stub
		System.out.println("please enter the following details to Signup.");
		System.out.println("please enter the user name.");
		String name = sc.next();
		System.out.println("Enter the password.");
		String pass = sc.next();
		System.out.println("enter the address.");
		String address = sc.next();
		System.out.println("Enter the email id.");
		String email = sc.next();

		Public p = new Public(name, pass, address, email);

		PublicService ps1 = new PublicServeImp();
		ps1.signUp(p, publics);
		System.out.println("customer has Succefully sign up.\n");
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Map<Integer, Crime> crimes = FileExists.crimeFile();
		Map<String, Public> publics = FileExists.publicFile();

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"\n*-*-*-*-*-*-*-*-*-*-*- Welcome to Crime Information Management System.*-*-*-*-*-*-*-*-*-*-*-\n");

		try {
			int choice = 0;
			ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Crime.ser"));
			do {
				System.out.println("Enter your preference : ");
				System.out
						.println("1 --> Admin login.  \n2 --> Public login. \n3--> New User? signup. \n0 --> exit.\n");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					adminFunctionilty(sc, crimes, publics);
					break;
				case 2:
					publicFunctionality(sc, publics, crimes);
					break;
				case 3:
					newUser(sc, publics);
					break;
				case 4:
					System.out.println("Exited Successfully. Have a good day.\n");
					break;
				default:
					throw new InvalidInputException("Invalid Input, Please enter a number between 1 to 4.\n");
				}
				// write data to file after each operation
				coos.writeObject(crimes);
			} while (choice < 4);
			coos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
