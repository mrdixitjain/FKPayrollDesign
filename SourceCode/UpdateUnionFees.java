
import java.sql.*; 
import java.util.*;
import java.time.*;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.*; 
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoField; 
import java.sql.DriverManager;

class UpdateUnionFees {
	
	public static void updateMembershipFees() {
        Connection con = null;
        PreparedStatement preparedStmt = null;
        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            System.out.println("\nEnter Choice: \n  1. Update for all Employees\n  2. Update for a specific user");

            Scanner in = new Scanner(System.in);

            int n = in.nextInt();

            if(n == 1) {
                preparedStmt = con.prepareStatement("update UnionCharges set membershipFee = ? where 1");
            }

            else if(n == 2) {
                String id = System.console().readLine("\nEnter Employee id: ");
                preparedStmt = con.prepareStatement("update UnionCharges set membershipFee = ? where empId = ?");
                preparedStmt.setString (2, id);
            }

            else{
                System.out.println("\nwrong option selected\nPlease try again\n");
                return;
            }

            System.out.print("Enter new Membership Fees: ");
            float newFees = in.nextFloat();


            preparedStmt.setFloat (1, newFees);
            preparedStmt.execute();

            con.commit();
            System.out.println("\nMembership Fees Updated SuccessFully\n");
            return;
        }

        catch (SQLException e ) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            }
            return;
        } 

        finally {
            try{
                if(preparedStmt!=null)
                   preparedStmt.close();
            }
            catch(SQLException se2){
            }
            
            try{
                if(con!=null){
                    con.setAutoCommit(true);
                    con.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }



    public static void updateRegularFees() {
        Connection con = null;
        PreparedStatement preparedStmt = null;
        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            System.out.println("\nEnter Choice: \n  1. Update for all Employees\n  2. Update for a specific user");

            Scanner in = new Scanner(System.in);

            int n = in.nextInt();

            if(n == 1) {
                preparedStmt = con.prepareStatement("update UnionCharges set regularFee = ? where 1");
            }

            else if(n == 2) {
                String id = System.console().readLine("\nEnter Employee id: ");
                preparedStmt = con.prepareStatement("update UnionCharges set regularFee = ? where empId = ?");
                preparedStmt.setString (2, id);
            }

            else{
                System.out.println("\nwrong option selected\nPlease try again\n");
                return;
            }

            System.out.print("Enter new Regular Fees: ");
            float newFees = in.nextFloat();


            preparedStmt.setFloat (1, newFees);
            preparedStmt.execute();

            con.commit();
            System.out.println("\nRegular Fees Updated SuccessFully\n");
            return;
        }

        catch (SQLException e ) {
            e.printStackTrace();
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            }
            return;
        } 

        finally {
            try{
                if(preparedStmt!=null)
                   preparedStmt.close();
            }
            catch(SQLException se2){
            }
            
            try{
                if(con!=null){
                    con.setAutoCommit(true);
                    con.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

}