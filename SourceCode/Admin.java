
import java.util.*;

class Admin {
    public static enum MethodOfPayment {Paycheck, PaycheckPickup, BankTransfer};

    private static HashMap<String, String> adminDetails;

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