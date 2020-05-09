
import java.util.*;
import java.sql.*; 



public class Main {

	private static void login() {
		System.out.println("\nUSER LOG-IN\n  Enter Post: ");
		System.out.println("  1. Admin\n  2. Employee");
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();

		HashMap<String, String> details;

		if( k ==1 ) {
			details = Validation.logIN("admin");
		}
		else {
			details = Validation.logIN("employee");
		}

		if(details.get("Login").equals("false"))
			return;
		else {
			System.out.println("\nLogged-in SuccessFully\n");
		}


	}

	public static void main(String[] args) {

		while(true){
			System.out.println("1. Log-IN\n2. Quit.\n");
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();
			if(n == 1)
				login();
			else
				break;
		}
	}
}