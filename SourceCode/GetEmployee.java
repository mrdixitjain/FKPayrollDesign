
import java.sql.*; 
import java.util.*;
import java.time.*;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.*; 
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoField; 
import java.sql.DriverManager;

class GetEmployee {
	
	public static String getMOP(String id) {

        int l = Validation.checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return "";
        }

        Connection con = null;
        Statement stmt = null;
        String getQuery = "Select mop from Employee where empId = '" + id + "'";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return "";
            }

            return rs.getString(1);
        }

        catch (SQLException e ) {
            e.printStackTrace();
            return "";
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




    public static float getSalary(String id) {

        int l = Validation.checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return -1;
        }

        Connection con = null;
        Statement stmt = null;
        String getQuery = "Select salary from MonthlySalary where empId = '" + id + "'";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return -1;
            }

            return rs.getFloat(1);
        }

        catch (SQLException e ) {
            e.printStackTrace();
            return -1;
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




    public static float getHourRate(String id) {

        int l = Validation.checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return -1;
        }

        Connection con = null;
        Statement stmt = null;
        String getQuery = "Select rate from HourRate where empId = '" + id + "'";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return -1;
            }

            return rs.getFloat(1);
        }

        catch (SQLException e ) {
            e.printStackTrace();
            return -1;
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