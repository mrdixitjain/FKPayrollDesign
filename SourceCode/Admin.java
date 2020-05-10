
import java.util.*;

class Admin {
    public static final String[] MOP = {"Paycheck", "PaycheckPickup", "BankTransfer"};
    public static final String[] type = {"hourly", "monthly"};

    private static HashMap<String, String> adminDetails;

    
    private static void addEmployee() {
        
        System.out.println("\nEnter Type Of Employee:\n  1. Hourly Paid\n  2. Monthly Paid");
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



    private static void removeEmployee() {

        String id = System.console().readLine("\nEnter Employee Id: ");

        System.out.println("\nEnter Type Of Employee:\n  1. Hourly Paid\n  2. Monthly Paid");
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        Validation.removeEmployee(id, type[n - 1]);
    }

    private static void updateEmployee() {
        
        System.out.println("\nEnter Type Of Employee:\n  1. Hourly Paid\n  2. Monthly Paid");
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.print("\nUpdate Employee's:\n  1. Method Of Payment\n  2. Password\n  3. Commission Rate\n  4. Union MemberShip Fees\n  5. Union Regular Fees\n");
        if(n == 1)
            System.out.println("  6. Hourly Rate");
        if(n == 2)
            System.out.println("  6. Salary");
        System.out.println("  0. To return.");



        int k = in.nextInt();

        if(k == 4) {
            Validation.updateMembershipFees();
            return;
        }
        else if (k == 5) {
            Validation.updateRegularFees();
            return;
        }


        String id = System.console().readLine("\nEnter Employee Id: ");

        switch(k) {
            case 1:
                Validation.updateMOP(id);
                break;
            
            case 2:
                Validation.updatePassword(id, type[n-1]);
                break;
            
            case 3:
                Validation.updateCommission(id, type[n-1]);
                break;
            
            case 6:
                Validation.updateSalary(id, type[n-1]);
                break;
            default:
                break;
        }
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
                case 2 :
                    removeEmployee();
                    break;
                case 3 :
                    updateEmployee();
                    break;
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