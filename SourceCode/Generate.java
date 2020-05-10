

import java.util.*;

class Generate {

	public static String generateId() {
		Random r = new Random();
		String id = "";
		for(int i = 0; i < 4; i++) {
			id += (char) (r.nextInt(26)+65);
		}
		for(int i = 0; i < 6; i++){
			id += r.nextInt(10);
		}
		return id;
	}

	public static String generatePassword() {
		Random r = new Random();
		String password = "";
		for(int i = 0; i < 10; i++) {
			password += (char) (r.nextInt(26)+65);
		}
		return password;
	}


}