
import java.sql.*;
import java.util.*;
import java.time.*; 
import java.time.format.*; 
import java.time.temporal.ChronoUnit;

class HourlyEmployee extends Employee {

    private float hourRate;

    public HourlyEmployee(String name, String id, String password, String mop, boolean isInUnion, float commissionRate, float hourRate) {
        super(name, id, password, mop, isInUnion, commissionRate);
        this.hourRate = hourRate;
    }

    public int showChoices() {
        String choices = "\nEnter a choice:\n  1. Update password\n  2. Join Employee Union\n  3. Leave Employee Union\n  4. update method of payment\n  5. get your details\n  6. Add Sale Reciept\n  7. Add Time Card\n  8. Log Out\n";
        System.out.print(choices);
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();

        return n;
    }

    public void addTimeCard() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu"); 
        String sdate = System.console().readLine("\nEnter Date(dd-mm-yyyy): ");
        LocalDate date = LocalDate.now();
        try{ date = LocalDate.parse(sdate, formatter); }
        catch (DateTimeParseException e) {
            System.out.println("\nwrong date format\nPlease try again\n");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(date, currentDate);
        if(daysBetween >= 7){
            System.out.println("\nCan't take entries older than 7 days\n");
            return;
        }
        String cardNumber = System.console().readLine("\nEnter Time Card Number: ");
        System.out.print("\nEnter number of worked hours: ");
        Scanner in = new Scanner(System.in);
        float numberOfHours = in.nextFloat();
        if(numberOfHours > 24 || numberOfHours < 0){
            System.out.println("\nnumber of worked hours can't be " + numberOfHours +"\nPlease try again\n");
            return;
        }
        float extraHours = 0;
        if(numberOfHours > 8){
            extraHours = numberOfHours - 8;
            numberOfHours = 8;
        }
        UpdateEmployee.addTimeCard(cardNumber, this.getId(), date, numberOfHours, extraHours);

    }

    public void getDetails() {
        super.getDetails();
        System.out.println("Hour Rate : " + hourRate);

    }  

    public void hourlyEmployeeMain() {

        int n = 6;
        Choice:
        while(true) {
            n = showChoices();

            switch(n){
                case 1 :
                    updatePassword();
                    break;

                case 2 : 
                    joinUnion();
                    break;

                case 3 : 
                    leaveUnion();
                    break;

                case 4 : 
                    updateMOP();
                    break;

                case 5 : 
                    getDetails();
                    break;

                case 6 : 
                    addSaleReciept();
                    break;

                case 7 :
                    addTimeCard();
                    break;

                case 8 :
                    break Choice;

                default :
                    System.out.println("\nPlease! enter a valid choice\n");
                    continue Choice;
            }
        }

        System.out.println("\nLogged Out SuccessFully\n");
    }


} 