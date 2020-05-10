

import java.sql.*;
import java.util.*;

class MonthlyEmployee extends Employee {

    private float salary;

    // called after login
    public MonthlyEmployee(String name, String id, String password, String mop, boolean isInUnion, float commissionRate) {
        super(name, id, password, mop, isInUnion, commissionRate);
        // this.salary = Validation.getSalary(id);
    }

    // called while adding new employee
    public MonthlyEmployee(String name, String id, String password, String mop, boolean isInUnion, float commissionRate, float salary) {
        super(name, id, password, mop, isInUnion, commissionRate);
        this.salary = salary;
    }

    public float getSalary() {
        System.out.println("\nyour monthly salary is: " + this.salary + ".\n");
        return this.salary;
    }

    public int showChoices() {
        super.showChoices();
        Scanner in = new Scanner(System.in);

        System.out.println("  6. know your salary\n  7. log out");

        int n = in.nextInt();

        return n;
    }

    public void employeeMain() {

        int n = 7;
        Choice:
        while(true) {
            n = showChoices();

            switch(n){
                case 1 :
                    updatePassword();
                    break;

                // case 2 : 
                //     joinUnion();
                //     break;

                // case 3 : 
                //     leaveUnion();
                //     break;

                // case 4 : 
                //     joinUnion();
                //     break;

                // case 5 : 
                //     updateMOP();
                //     break;

                // case 6 : 
                //     getDetails();
                //     break;

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