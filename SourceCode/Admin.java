
import java.util.*;

class Admin {
    public static final String[] MOP = {"Paycheck", "PaycheckPickup", "BankTransfer"};

    private static HashMap<String, String> adminDetails;

    private static void addEmployee() {
        
        System.out.println("\nAdd Type Of Employee:\n  1. Hourly Paid\n  2. Monthly Paid");
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();


        String name = System.console().readLine("\nEnter Employee Name: ");

        System.out.print("\nEnter Employee's Method Of Payment:\n  1. Paycheck\n  2. Paycheck Pickup\n  3. Bank Transfer\n");

        int mop = in.nextInt();

        System.out.print("\nEnter Employee Commission Rate: ");
        float commission = in.nextFloat();

        String id = Generate.generateId();
        int k = Validation.checkId(id);
        while(k == 2) {
            id = Generate.generateId();
            k = Validation.checkId(id);
        }

        if( k == 3 )
            return;

        String password = Generate.generatePassword();

        float rate = 0;
        String type;

        if ( n == 1 ) {
            System.out.print("\nEnter Hourly Rate: ");
            rate = in.nextFloat();
            type = "hourly";
        }

        else {
            System.out.print("\nEnter Salary: ");
            rate = in.nextFloat();
            type = "monthly";
        }

        if(!Validation.addEmployee(name, id, password, MOP[mop - 1], commission, type, rate))
            return;
        System.out.println("\nEmployee Added SuccessFully\nEmployee id: " + id + ", password: " + password + "\n");
        return;

    }



    private static int showChoices() {
        String choices = "  1. Add new Employee\n  2. Remove Existing Employee\n  3. Update Existing Employee\n  4. Logout\n";
        
        System.out.println("Enter a choice:\n" + choices);
        Scanner in = new Scanner(System.in);
        int n = 0;

        while(!in.hasNextInt()){
            System.out.println("\nPlease! Enter a valid choice\n" + choices);
        }
        n = in.nextInt();

        return n;

    }

    public static void adminMain(HashMap<String, String> details) {
        adminDetails = details;

        int n = 4;

        Choice:
        while(true) {
            n = showChoices();

            switch(n){
                case 1 :
                    addEmployee();
                    break;
                // case 2 :
                //     removeEmployee();
                //     break;
                // case 3 :
                //     updateEmployee();
                //     break;
                case 4 :
                    break Choice;

                default :
                    System.out.println("\nPlease! enter a valid choice\n");
                    continue;
            }
        }
        System.out.println("\nSuccessFully Logged out\n");



    }

}