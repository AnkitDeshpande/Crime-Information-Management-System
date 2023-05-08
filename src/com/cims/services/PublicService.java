package com.cims.services;

import java.util.Map;

import com.cims.entity.Crime;
import com.cims.entity.Public;
import com.cims.exceptions.CrimeException;
import com.cims.exceptions.DuplicateDataException;
import com.cims.exceptions.InvalidDetailsException;

public interface PublicService {
	public boolean login(String email,String password, Map<String, Public> publics) throws InvalidDetailsException;
	public void signUp(Public p, Map<String, Public> publics) throws DuplicateDataException;
	public String reportCrime(Crime cr, Map<Integer, Crime> crimes);
	public void viewAllCrimes(Map<Integer, Crime> crimes) throws CrimeException;
	public String updateMyCrime(int id, Crime c, Map<Integer, Crime> crimes) throws CrimeException;
	public Public viewMyDetails(String email, Map<String, Public> publics);
}
