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
		if(crimes!=null && crimes.size()>0) {
			if(crimes.containsKey(id)) {
				crimes.remove(id);
				System.out.println("Crime Removed Successfully.");
			}else {				
				throw new CrimeException("No ID found."); 
			}
		}else {
			throw new CrimeException("List is Empty."); 
		}
		
	}

	@Override
	public String updateCrime(int id, Crime c, Map<Integer, Crime> crimes) throws CrimeException {
		// TODO Auto-generated method stub
		if(crimes!=null && crimes.size()>0) {
			if(crimes.containsKey(id)) {
				crimes.put(id, c);
				return "Crime has successfully updated";
			}else {
				throw new CrimeException("Crime not found.");
			}
		}else {
			throw new CrimeException("Crime List is Empty.");
		}
	}
	
}
