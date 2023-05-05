
package com.cims;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import com.cims.entity.Crime;
import com.cims.entity.Public;
import com.cims.exceptions.InvalidDataExeception;
import com.cims.exceptions.InvalidInputException;
import com.cims.services.CrimeService;
import com.cims.services.CrimeServiceImp;
import com.cims.utility.Admin;
import com.cims.utility.FileExists;
import com.cims.utility.Genrate_ID;

public class Main {

	private static void adminFunctionilty(Scanner sc, Map<Integer, Crime> crimes, Map<String, Public> Publics)
			throws InvalidDataExeception {
		// TODO Auto-generated method stub

		adminLogin(sc);

		CrimeService cService = new CrimeServiceImp();

		int choice = 0;
		try {
			do {
				System.out.println("Enter the operation you want to perform :");
				System.out.println("1-->Add a new Crime/Register a Crime.");
				System.out.println("2-->Update a Crime.");
				System.out.println("3-->Add a new Criminal.");
				System.out.println("4-->Update a Criminal record.");
				System.out.println("5-->Remove a Criminal from record.");
				System.out.println("6-->View a record of a criminal.");
				System.out.println("7-->View all criminal record.");
				System.out.println("8-->Logout.");
				choice = sc.nextInt();

				switch (choice) {

				case 1:
					String added = AdminAddCrime(sc, crimes, cService);
					System.out.println(added);
					break;
				case 2:
					System.out.println(2);
					break;
				case 3:
					System.out.println(3);
					break;
				case 4:
					System.out.println(4);
					break;
				case 5:
					System.out.println(5);
					break;
				case 6:
					System.out.println(6);
					break;
				case 7:
					System.out.println(7);
					break;
				case 8:
					System.out.println(0);
					break;
				default:
					throw new InvalidInputException("Invalid Input. Please Enter the correct number between 0 to 7.");

				}

			} while (choice <= 8);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

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
		System.out.println("Enter the name of Victim.");
		String victim = sc.next();
		Crime c = new Crime(Genrate_ID.genrateID(), category, desc, ps_area, LocalDate.now(), victim);
		str = cService.addCrime(c, crimes);
		return str;
	}

	private static void publicFunctionality(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Working");
	}

	private static void newUser() {
		// TODO Auto-generated method stub
		System.out.println("Working");
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

	public static void main(String[] args) {

		Map<Integer, Crime> crimes = FileExists.crimeFile();
		Map<Integer, Public> p1 = FileExists.publicFile();

		Scanner sc = new Scanner(System.in);

		System.out.println(
				"\n*-*-*-*-*-*-*-*-*-*-*- Welcome to Crime Information Management System.*-*-*-*-*-*-*-*-*-*-*-\n");

		try {
			int choice = 0;

			do {

				System.out.println("Enter your preference : ");
				System.out.println("1 --> Admin login.  \n2 --> Public login. \n3--> New User? signup. \n0 --> exit.");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					adminFunctionilty(sc, crimes, null);
					break;
				case 2:
					publicFunctionality(sc);
					break;
				case 3:
					newUser();
					break;
				case 0:
					System.out.println("Exited Successfully. Have a good day.");
					break;
				default:
					throw new InvalidInputException("Invalid Input, Please enter a number between 0 to 3.");
				}

			} while (choice != 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Crime.ser"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

}
