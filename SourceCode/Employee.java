
import java.sql.*;
import java.util.*;
import java.time.*; 
import java.time.format.*; 
import java.time.temporal.ChronoUnit;

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

    public void getDetails() {
        System.out.println("\nName : " + name);
        System.out.println("id : " + id);
        System.out.println("method of payment : " + mop);
        System.out.println("in union : " + isInUnion);
        System.out.println("commission rate : " + commissionRate);
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
        if(this.isInUnion) {
            System.out.println("\nAlready in Union\n");
            return;
        }

        // showUnionCharges();

        System.out.println("\nDo you want to join?\n  1. yes\n  2. no\n");
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n!=1)
            return;
        if(Validation.addToUnion(this.id))
            this.isInUnion = true;
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
        if(Validation.leaveUnion(this.id))
            this.isInUnion = false;

    }



    public void updateMOP() {

        if(Validation.updateMOP(this.id)){
            String newMOP = Validation.getMOP(this.id);
            if(newMOP.equals(""));
            else
                this.mop = newMOP;
        }
        
    }

    
    public void addSaleReciept() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu"); 
        String sdate = System.console().readLine("\nEnter Date(dd-mm-yyyy): ");
        LocalDate date = LocalDate.now();
        try{
            date = LocalDate.parse(sdate, formatter);
        }
        catch (DateTimeParseException e) {
            System.out.println("\nwrong date format\nPlease try again\n");
            return;
        }
        LocalDate currentDate = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(date, currentDate);
        if(daysBetween >= 15){
            System.out.println("\nCan't take entries older than 15 days\n");
            return;
        }
        String recieptNumber = System.console().readLine("\nEnter Reciept Number: ");
        System.out.print("\nEnter number of sales: ");
        Scanner in = new Scanner(System.in);
        int numberOfSales = in.nextInt();
        float totalAmount;
        System.out.print("\nEnter total sale amount: ");
        totalAmount = in.nextFloat();

        Validation.addSaleReciept(recieptNumber, this.id, date, numberOfSales, totalAmount);
    }


    public int showChoices() {
        String choices = "\nEnter a choice:\n  1. Update password\n  2. Join Employee Union\n  3. Leave Employee Union\n  4. update method of payment\n  5. get your details\n  6. Add Sale Reciept\n  7. Log Out\n";
        System.out.print(choices);
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();

        return n;
    }


    
}