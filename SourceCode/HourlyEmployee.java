
import java.sql.*;
import java.util.*;

class HourlyEmployee extends Employee {

    private float hourRate;

    // called while adding new employee
    public HourlyEmployee(String name, String id, String password, String mop, boolean isInUnion, float commissionRate, float hourRate) {
        super(name, id, password, mop, isInUnion, commissionRate);
        this.hourRate = hourRate;
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
                    break Choice;

                default :
                    System.out.println("\nPlease! enter a valid choice\n");
                    continue;
            }
        }

        System.out.println("\nLogged Out SuccessFully\n");
    }


} 