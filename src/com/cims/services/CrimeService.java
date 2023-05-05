package com.cims.services;

import java.util.Map;

import com.cims.entity.Crime;
import com.cims.exceptions.CrimeException;

public interface CrimeService {
	public String addCrime(Crime c, Map<Integer, Crime> crimes);
	public void viewAllCrimes(Map<Integer, Crime> crimes) throws CrimeException;
	public void deleteCrime(int id, Map<Integer, Crime> crimes) throws CrimeException;
	public String updateCrime(int id, Map<Integer, Crime> crimes) throws CrimeException;
	
}
