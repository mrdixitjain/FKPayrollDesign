
import java.util.*;
import java.sql.*; 




public class Main {

	private static void login() {
		System.out.println("\nUSER LOG-IN\n  Enter Post: ");
		System.out.println("    1. Admin\n    2. Employee");
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();

		HashMap<String, String> details;

		if( k == 1 ) {
			details = Validation.logIN("admin");
		}
		else if(k == 2) {
			details = Validation.logIN("employee");
		}
		else
			return;

		if(details.get("Login").equals("false"))
			return;
		else {
			System.out.println("\nLogged-in SuccessFully\n");

			String str = details.get("type");

			switch(str) {
				case "admin" :
					Admin.adminMain(details);
					break;

				case "hourly" :
					float hourRate = Validation.getHourRate(details.get("id"));
					if(hourRate < 0){
						System.out.println("\nSome error occurred\nplease try again later");
						return;
					}
					HourlyEmployee emp = new HourlyEmployee(details.get("name"), details.get("id"), details.get("password"), details.get("mop"), Boolean.parseBoolean(details.get("isInUnion")), Float.parseFloat(details.get("commissionRate")), hourRate);
					emp.hourlyEmployeeMain();
					break;

				case "monthly" :
					float salary = Validation.getSalary(details.get("id"));
					if(salary < 0){
						System.out.println("\nSome error occurred\nplease try again later");
						return;
					}
					MonthlyEmployee emp = new MonthlyEmployee(details.get("name"), details.get("id"), details.get("password"), details.get("mop"), Boolean.parseBoolean(details.get("isInUnion")), Float.parseFloat(details.get("commissionRate")), salary);
					emp.employeeMain();
					break;
			}
		}


	}

	public static void main(String[] args) {

		while(true){
			System.out.println("1. Log-IN\n2. Quit.\n");
			Scanner in = new Scanner(System.in);
			int n = 0;
	        while(!in.hasNextInt()){
	            System.out.println("\nPlease! Enter a valid choice\n");
	            System.out.println("  1. Log-IN\n  2. Quit.\n");
	            
	        }
	        n = in.nextInt();
			if(n == 1)
				login();
			else
				break;
		}
	}
}