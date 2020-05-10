
import java.sql.*; 
import java.util.*;
import java.time.*;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.*; 
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoField; 
import java.sql.DriverManager;


class Payroll {
	
	public static void payHourlyEmployee() {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement preparedStmt = null;

        String getQuery = "select Employee.*, Sum(workHours) as totalWorkHours, Sum(extraHours) as totalExtraHours, rate from Employee Inner Join TimeCards Using(empId) Inner Join HourRate Using(empId) where type = 'hourly' and paid = false GROUP BY empId;";

        String updateQuery = "update TimeCards set paid = true where paid = false;";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 
            ResultSet rs = stmt.executeQuery(getQuery);

            if(!rs.next()){
                System.out.println("\nNo dues\n");
                return;
            }

            do {

                System.out.println("\nEmployee Id: " + rs.getString("empId") + "\nEmployee Name: " + rs.getString("name") + "\n  Total Payment: " + (rs.getFloat("totalWorkHours")*rs.getFloat("rate") + rs.getFloat("totalExtraHours")*rs.getFloat("rate")*1.5) + "\n  Method of Payment: " + rs.getString("mop"));
            } while(rs.next());
            System.out.println();

            preparedStmt = con.prepareStatement(updateQuery);
            preparedStmt.execute();

            con.commit();

            System.out.println("\nPaid All Hourly Employees\n");
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
        } 

        finally {
            try{
                if(stmt!=null)
                   stmt.close();
            }
            catch(SQLException se2){
            }

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



    public static void payMonthlyEmployee() {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement preparedStmt = null;

        String getQuery = "select Employee.*, salary from Employee Inner Join MonthlySalary Using(empId) where type = 'monthly' GROUP BY empId;";

        String updateQuery = "delete from Employee where working = 0;";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 
            ResultSet rs = stmt.executeQuery(getQuery);

            if(!rs.next()){
                System.out.println("\nNo dues\n");
                return;
            }

            do {

                System.out.println("\nEmployee Id: " + rs.getString("empId") + "\nEmployee Name: " + rs.getString("name") + "\n  Total Payment: " + rs.getFloat("salary") + "\n  Method of Payment: " + rs.getString("mop"));
            } while(rs.next());
            System.out.println();

            preparedStmt = con.prepareStatement(updateQuery);
            preparedStmt.execute();

            con.commit();

            System.out.println("\nPaid All Monthly Employees\n");
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
        } 

        finally {
            try{
                if(stmt!=null)
                   stmt.close();
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



    public static void payCommissions() {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement preparedStmt = null;

        String getQuery = "select Employee.*, sum(totalAmount) as totalCommission from Employee INNER JOIN SaleReciepts USING(empId) where commissionRate > 0 and paid = false GROUP BY empId;";

        String updateQuery = "update SaleReciepts set paid = true where paid = false;";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 
            ResultSet rs = stmt.executeQuery(getQuery);

            if(!rs.next()){
                System.out.println("\nNo dues\n");
                return;
            }

            do {

                System.out.println("\nEmployee Id: " + rs.getString("empId") + "\nEmployee Name: " + rs.getString("name") + "\n  Total Commission: " + rs.getFloat("totalCommission") + "\n  Method of Payment: " + rs.getString("mop"));
            } while(rs.next());
            System.out.println();

            preparedStmt = con.prepareStatement(updateQuery);
            preparedStmt.execute();

            con.commit();

            System.out.println("\nAll commissions paid\n");
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
        } 

        finally {
            try{
                if(stmt!=null)
                   stmt.close();
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