package com.cims.entity;

import java.io.Serializable;

public class Public extends User implements Serializable{

	public Public() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Public(String username, String password, String address, String email) {
		super(username, password, address, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User \n username :" + getUsername() + "\n password :" + getPassword() + "\n address :" + getAddress() + "\n email :" + getEmail();
	}
	
}
