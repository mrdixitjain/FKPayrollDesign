
import java.sql.*; 
import java.util.*;
import java.time.*;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.*; 
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoField; 
import java.sql.DriverManager;


class Login {
	
    public static HashMap<String, String> validate(String id, String password, String type){ 
        HashMap<String, String> userDetails = new HashMap<>();
        
        userDetails.put("Login", "false"); 

        Connection con = null;
        String query = "select * from Employee where empId = '" + id + "' and password = '" + password + "'";
        Statement stmt = null;

        try{  
            // Class.forName("com.mysql.jdbc.Driver");
              
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  
            
            stmt = con.createStatement();  
            
            if(type.equals("admin"))
                query += " and type = 'admin'";

            ResultSet rs=stmt.executeQuery(query);  
            
            if(!rs.next()) {
                System.out.println("\nWrong Employee Id\nPlease Try Again\n");
                return userDetails;
            }

            if(!rs.getBoolean(8)){
                System.out.println("\nYou are not authorized to login\nYou have been fired\n");
                return userDetails;
            }
            // System.out.println(rs);
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
        String empId;
        if(type.equals("admin")) {
            empId = console.readLine("  Admin Id: ");
        }
        else {
            empId = console.readLine("  Employee Id: ");
        }
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