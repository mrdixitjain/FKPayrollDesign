
import java.sql.*; 
import java.util.*;

import java.sql.DriverManager;

class Validation{  
    public static HashMap<String, String> validate(String id, String password, String type){ 
        HashMap<String, String> userDetails = new HashMap<>();
        userDetails.put("Login", "false"); 
        try{  
            // Class.forName("com.mysql.jdbc.Driver");
              
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  
            Statement stmt=con.createStatement();  
            String query = "select * from Employee";
            if(type.equals("admin"))
                query += " where type = 'admin'";
            ResultSet rs=stmt.executeQuery(query);  
            if(rs == null) {
                System.out.println("\nWrong Employee Id\nPlease Try Again");
                return userDetails;
            }
            rs.next();
            // System.out.println(rs);
            // System.out.println(rs.getString(4) + " " + password);
            if(!rs.getString(4).equals(password)) { 
                System.out.println("\nWrong Password\nPlease Try Again");
                return userDetails;
            }

            userDetails.put("Login", "true");
            userDetails.put("name", rs.getString(2));
            userDetails.put("type", rs.getString(5));
            userDetails.put("isInUnion", ""+rs.getBoolean(7));
            userDetails.put("commissionRate", ""+rs.getFloat(6));
            userDetails.put("mop", rs.getString(3));

            con.close();


            return userDetails;
        }
        catch(Exception e){ System.out.println(e); return userDetails; }  
    }  

    public static HashMap<String, String> logIN(String type) {
        java.io.Console console = System.console();
        System.out.println("Employee Login: ");
        String empId = console.readLine("Employee Id: ");
        String password = new String(console.readPassword("Password: "));
        HashMap<String, String> details = validate(empId, password, "Employee");
        // System.out.println(userDetails);
        if(details.get("Login").equals("false"))
            return details;
        details.put("id", empId);
        details.put("password", password);
        return details;
        // if(empDetails.get("type") == "hourly") {
        //     HourlyPaidEmployee.choices(empDetails);
        // }
        // else{
        //     choices();
        // }
        // System.out.println(username + " " + password);
    }
}  