
import java.sql.*; 
import java.util.*;

import java.sql.DriverManager;

class Validation{  

    public static int checkId(String id) {
        Connection con = null;
        String query = "select * from Employee where empId = '" + id +"'";
        Statement stmt = null;
        try{  
            // Class.forName("com.mysql.jdbc.Driver");
              
            con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  
            
            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(query);  
            if(!rs.next()) {
                return 1;
            }

            return 2;
        }
        catch(SQLException e){ 
            e.printStackTrace();
            System.out.println("\nTechnical Error\nPlease try again Later.\n");
            return 3;
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

    public static boolean addEmployee(String name, String id, String password, String mop, float commission, String type, float rate) {
        Connection con = null;
        PreparedStatement preparedStmt = null;
        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            preparedStmt = con.prepareStatement("insert into Employee values(?, ?, ?, ?, ?, ?, ?);");
            preparedStmt.setString (1, id);
            preparedStmt.setString (2, name);
            preparedStmt.setString (3, mop);
            preparedStmt.setString (4, password);
            preparedStmt.setString (5, type);
            preparedStmt.setFloat (6, commission);
            preparedStmt.setBoolean (7, false);
            preparedStmt.execute();
 
            if(type == "hourly")
                preparedStmt = con.prepareStatement("insert into HourRate values(?, ?);");
            else
                preparedStmt = con.prepareStatement("insert into MonthlySalary values(?, ?);");
            preparedStmt.setString (1, id);
            preparedStmt.setFloat (2, rate);
            preparedStmt.execute();

            con.commit();
            return true;
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
            return false;
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

    
    public static HashMap<String, String> validate(String id, String password, String type){ 
        HashMap<String, String> userDetails = new HashMap<>();
        
        userDetails.put("Login", "false"); 

        Connection con = null;
        String query = "select * from Employee";
        Statement stmt = null;

        try{  
            // Class.forName("com.mysql.jdbc.Driver");
              
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  
            
            stmt = con.createStatement();  
            
            if(type.equals("admin"))
                query += " where type = 'admin'";
            ResultSet rs=stmt.executeQuery(query);  
            
            if(!rs.next()) {
                System.out.println("\nWrong Employee Id\nPlease Try Again\n");
                return userDetails;
            }
            // System.out.println(rs);
            // System.out.println(rs.getString(4) + " " + password);
            if(!rs.getString(4).equals(password)) { 
                System.out.println("\nWrong Password\nPlease Try Again\n");
                return userDetails;
            }

            userDetails.put("Login", "true");
            userDetails.put("name", rs.getString(2));
            userDetails.put("type", rs.getString(5));
            userDetails.put("isInUnion", ""+rs.getBoolean(7));
            userDetails.put("commissionRate", ""+rs.getFloat(6));
            userDetails.put("mop", rs.getString(3));


            return userDetails;
        }
        catch(Exception e){ e.printStackTrace(); return userDetails; } 

        finally {
            try{
                if(stmt!=null)
                   stmt.close();
            }
            catch(SQLException se){
            }
            
            try{
                if(con != null){
                    con.setAutoCommit(true);
                    con.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        } 
    }  

    public static HashMap<String, String> logIN(String type) {
        
        java.io.Console console = System.console();
        System.out.println("\nLogin: ");
        String empId = console.readLine("  Employee Id: ");
        String password = new String(console.readPassword("  Password: "));
        HashMap<String, String> details = validate(empId, password, type);
        // System.out.println(userDetails);
        if(details.get("Login").equals("false"))
            return details;
        details.put("id", empId);
        details.put("password", password);
        return details;
    }
}  