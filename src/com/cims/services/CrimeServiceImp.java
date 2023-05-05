package com.cims.services;

import java.util.Map;

import com.cims.entity.Crime;
import com.cims.exceptions.CrimeException;

public class CrimeServiceImp implements CrimeService {

	@Override
	public String addCrime(Crime c, Map<Integer, Crime> crimes) {
		// TODO Auto-generated method stub
		crimes.put(c.getCrime_id(), c);
		return "Crime added Successfully";
	}

	@Override
	public void viewAllCrimes(Map<Integer, Crime> crimes) throws CrimeException {
		// TODO Auto-generated method stub
		if(crimes!=null && crimes.size()>0) {
			for(Map.Entry<Integer, Crime> me: crimes.entrySet()) {
				System.out.println(me.getValue());
			}
		}else {
			throw new CrimeException("Crime List is Empty.");
		}
		
	}

	@Override
	public void deleteCrime(int id, Map<Integer, Crime> crimes) throws CrimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updateCrime(int id, Map<Integer, Crime> crimes) throws CrimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
