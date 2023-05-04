package com.cims.utility;

import java.io.*;
import java.util.*;
import java.util.Map;
import com.cims.entity.Crime;
import com.cims.entity.Public;

public class FileExists {

	public static Map<Integer, Crime> crimeFile() {
		// TODO Auto-generated method stub
		Map<Integer, Crime> cFile = null;
		File f = new File("Crime.ser");
		boolean flag = false;
		try {
			if (f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				cFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(cFile);
				return cFile;
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				cFile = (Map<Integer, Crime>) ois.readObject();
				return cFile;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return cFile;
	}

	public static Map<Integer, Public> publicFile() {
		// TODO Auto-generated method stub
		Map<Integer, Public> cFile = null;
		File f = new File("Public.ser");
		boolean flag = false;
		try {
			if (f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				cFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(cFile);
				return cFile;
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				cFile = (Map<Integer, Public>) ois.readObject();
				return cFile;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return cFile;
	}

}
