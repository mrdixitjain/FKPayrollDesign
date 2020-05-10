
import java.sql.*;
import java.util.*;

class Employee {

    private String name, id, password, mop;
    private boolean isInUnion;
    private float commissionRate;

    public Employee(String name, String id, String password, String mop, boolean isInUnion, float commissionRate) {
        this.name = name;
        this.id = id;
        this.password = password; 
        this.mop = mop;
        this.isInUnion = isInUnion;
        this.commissionRate = commissionRate;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void updatePassword() {
        java.io.Console console = System.console();

        String password = new String(console.readPassword("  Old Password: "));
        if(!password.equals(this.password)) {
            System.out.println("\nWrong Password Entered\n");
            return;
        }
        System.out.println("Password Length >= 8");
        String newPass = new String(console.readPassword("  new Password: "));
        if(newPass.length() < 8) {
            System.out.println("\nLength is less than required\nPlease try again\n");
            return;
        }
        String newPass2 = new String(console.readPassword("  Confirm Password: "));

        if(!newPass2.equals(newPass)){
            System.out.println("\n Both password do not match\nPlease try Again\n");
            return;
        }

        Validation.changePassword(this.id, newPass);
        return;
    }


    public void joinUnion() {
        if(getInUnion()) {
            System.out.println("\nAlready in Union\n");
            return;
        }

        // showUnionCharges();

        System.out.println("\nDo you want to join?\n  1. yes\n  2. no\n");
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n!=1)
            return;
        this.isInUnion = true;
        Validation.addToUnion(this.id);
    }


    public void leaveUnion() {
        if(!this.isInUnion) {
            System.out.println("\nnot registered in Union\n");
            return;
        }

        // showDues();
        System.out.println("\nDo you want to Leave?\n  1. yes\n  2. no\n");
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n!=1)
            return;
        this.isInUnion = true;
        Validation.leaveUnion(this.id);

    }



    public String getMop() {
        return mop;
    }

    public void updateMop() {
        this.mop = mop;
    }

    public boolean getInUnion() {
        return isInUnion;
    }


    public int showChoices() {
        String choices = "\nEnter a choice:\n  1. Update password\n  2. Join Employee Union\n  3. Leave Employee Union\n  4. update method of payment\n  5. get your details\n";
        System.out.print(choices);
        return 0;
    }


    
}