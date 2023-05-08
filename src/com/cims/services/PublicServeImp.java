package com.cims.services;

import java.util.Map;

import com.cims.entity.Crime;
import com.cims.entity.Public;
import com.cims.exceptions.CrimeException;
import com.cims.exceptions.DuplicateDataException;
import com.cims.exceptions.InvalidDetailsException;

public class PublicServeImp implements PublicService {

	@Override
	public String reportCrime(Crime cr, Map<Integer, Crime> crimes) {
		// TODO Auto-generated method stub
		int id = cr.getCrime_id();
		crimes.put(id, cr);
		return "Crime reported successfully." + "Your reported crime ID is : " + id
				+ " \n If you get more Information on the crime inform us.\n";
	}

	@Override
	public void viewAllCrimes(Map<Integer, Crime> crimes) throws CrimeException {
		// TODO Auto-generated method stub
		if (crimes != null && crimes.size() > 0) {
			for (Map.Entry<Integer, Crime> me : crimes.entrySet()) {
				System.out.println(me.getValue());
			}
		} else {
			throw new CrimeException("Crime List is Empty.\n");
		}
	}

	@Override
	public String updateMyCrime(int id, Crime c, Map<Integer, Crime> crimes) throws CrimeException {
		// TODO Auto-generated method stub
		if (crimes != null && crimes.size() > 0) {
			if (crimes.containsKey(id)) {
				crimes.put(id, c);
				return "Crime has successfully updated.\n";
			} else {
				throw new CrimeException("Crime not found.\n");
			}
		} else {
			throw new CrimeException("Crime List is Empty.\n");
		}
	}

	@Override
	public boolean login(String email, String password, Map<String, Public> publics) throws InvalidDetailsException {
		// TODO Auto-generated method stub
		if (publics.containsKey(email)) {
			if (publics.get(email).getPassword().equals(password)) {
				return true;
			} else {
				throw new InvalidDetailsException("Invalid Credentials.\n");
			}
		} else {
			throw new InvalidDetailsException("you have not sign up yet, please signup.\n");
		}
	}

	@Override
	public void signUp(Public p, Map<String, Public> publics) throws DuplicateDataException {
		// TODO Auto-generated method stub
		
		 if (publics.containsKey(p.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login.\n");
		} else {
			publics.put(p.getEmail(), p);
		}
	}

	@Override
	public Public viewMyDetails(String email, Map<String, Public> publics) {
		// TODO Auto-generated method stub
		if (publics.containsKey(email)) {

			return publics.get(email);

		}
		return null;
	}

}
