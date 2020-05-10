
import java.sql.*; 
import java.util.*;
import java.util.Calendar;
import java.time.LocalDate;


import java.sql.DriverManager;

class Validation{  


    public static void updatePassword(String id, String type) {

        int l = checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        String updateString = "Update Employee set password = ? where empId = ? and type = ?";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            preparedStmt = con.prepareStatement(updateString);

            String newPassword = Generate.generatePassword();
            preparedStmt.setString (1, newPassword);
            preparedStmt.setString (2, id);
            preparedStmt.setString (3, type);
            preparedStmt.execute();

            con.commit();

            System.out.println("Password Changed SuccessFully.\nNew Password: " + newPassword + "\n");
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





    public static void updateMOP(String id, String type) {
        String[] MOP = {"Paycheck", "PaycheckPickup", "BankTransfer"};

        int l = checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        String getQuery = "Select mop from Employee where empId = '" + id + "' and type = '" + type +"'";
        String updateString = "Update Employee set mop = ? where empId = ? and type = ?";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return;
            }

            System.out.println("\nCurrent Payment of Employee is: " + rs.getString(1));
            System.out.println("Do You Want To change?\n  1. yes\n  2. no\n");

            Scanner in = new Scanner(System.in);

            int choice = in.nextInt();

            if(choice != 1)
                return;

            System.out.print("\nEnter Employee's Method Of Payment:\n  1. Paycheck\n  2. Paycheck Pickup\n  3. Bank Transfer\n");

            int mop = in.nextInt();

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setString (1, MOP[mop-1]);
            preparedStmt.setString (2, id);
            preparedStmt.setString (3, type);
            preparedStmt.execute();

            con.commit();

            System.out.println("Method of Payment Changed SuccessFully.\nNew MOP: " + MOP[mop-1] + "\n");
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
                if(preparedStmt!=null)
                   preparedStmt.close();
            }
            catch(SQLException se2){
            }

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



    public static void updateSalary(String id, String type) {

        int l = checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        String getQuery, updateString, show1, show2, show3;
        if(type.equals("hourly")) {
            getQuery = "Select rate from HourRate where empId = '" + id + "'";
            updateString = "Update HourRate set rate = ? where empId = ?";
            show1 = "\nCurrent Hour Rate of Employee is: ";
            show2 = "Hour Rate updated SuccessFully\nNew Hour Rate: ";
            show3 = "\nEnter new Hour Rate: ";
        }
        else{
            getQuery = "Select salary from MonthlySalary where empId = '" + id + "'";
            updateString = "Update MonthlySalary set salary = ? where empId = ?";
            show1 = "\nCurrent salary of Employee is: ";
            show2 = "\nsalary updated SuccessFully\nNew salary: ";
            show3 = "\nEnter new Salary: ";
        }

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return;
            }

            System.out.println(show1 + rs.getString(1));
            System.out.println("Do You Want To change?\n  1. yes\n  2. no\n");

            Scanner in = new Scanner(System.in);

            int choice = in.nextInt();

            if(choice != 1)
                return;

            System.out.print(show3);

            float salary = in.nextFloat();

            if(salary < 0) {
                System.out.println("\nCan't be Negative\n");
                return;
            }

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setFloat (1, salary);
            preparedStmt.setString (2, id);
            preparedStmt.execute();

            con.commit();

            System.out.println(show2 + salary + "\n");
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
                if(preparedStmt!=null)
                   preparedStmt.close();
            }
            catch(SQLException se2){
            }

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



    public static void removeEmployee(String id, String type) {

        int l = checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        String show1, show2, show3;
        String getQuery = "Select name from Employee where empId = '" + id + "'";
        String updateString = "Update Employee set working = ? where empId = ?";


        if(type.equals("hourly")) {
            show1 = "\nCurrent Hour Rate of Employee is: ";
            show2 = "Hour Rate updated SuccessFully\nNew Hour Rate: ";
            show3 = "\nEnter new Hour Rate: ";
        }
        else{
            getQuery = "select name, salary from Employee Inner Join MonthlySalary on Employee.empId = MonthlySalary.empId where Employee.empId = '" + id + "'";
            show1 = "\nCurrent salary of Employee is: ";
            show2 = "\nsalary updated SuccessFully\nNew salary: ";
            show3 = "\nEnter new Salary: ";
        }

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return;
            }

            System.out.println("Employee Name: " + rs.getString(1));
            System.out.println("Do You Want To remove?\n  1. yes\n  2. no\n");
            Scanner in = new Scanner(System.in);

            int choice = in.nextInt();

            if(choice != 1)
                return;


            float dues = 0;
            if(type == "monthly") {
                float salary = rs.getFloat(2);
                LocalDate date = LocalDate.now();
                int lengthOfMonth = date.lengthOfMonth();
                int dayOfMonth = date.getDayOfMonth();

                dues = salary * dayOfMonth / lengthOfMonth;
                System.out.println("\nDue Monthly salary of Employee: " + dues + "\n");
            }



            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setBoolean (1, false);
            preparedStmt.setString (2, id);
            preparedStmt.execute();

            if(type == "monthly") {
                preparedStmt = con.prepareStatement("insert into dues values(?, ?)");
                preparedStmt.setString (1, id);
                preparedStmt.setFloat (2, dues);
                preparedStmt.execute();
            }

            con.commit();

            System.out.println("\nEmployee removed SuccessFully.\nDues will be paid in next Cycle\n");
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
                if(preparedStmt!=null)
                   preparedStmt.close();
            }
            catch(SQLException se2){
            }

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



    public static void updateCommission(String id, String type) {

        int l = checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        String getQuery = "Select commissionRate from Employee where empId = '" + id + "' and type = '" + type +"'";
        String updateString = "Update Employee set commissionRate = ? where empId = ? and type = ?";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement();  
            
            ResultSet rs = stmt.executeQuery(getQuery);  
            if(!rs.next()) {
                System.out.println("\nUnable to locate Employee with Id: " + id);
                System.out.println("Please Try Again\n");
                return;
            }

            System.out.println("\nCurrent Commission Rate of Employee is: " + rs.getString(1));
            System.out.println("Do You Want To change?\n  1. yes\n  2. no\n");

            Scanner in = new Scanner(System.in);

            int choice = in.nextInt();

            if(choice != 1)
                return;

            System.out.print("\nEnter new commission rate: ");

            float newRate = in.nextFloat();

            if(newRate < 0 || newRate > 100){
                System.out.println("\nCommission Rate can't be " + newRate);
                return;
            }

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setFloat (1, newRate);
            preparedStmt.setString (2, id);
            preparedStmt.setString (3, type);
            preparedStmt.execute();

            con.commit();

            System.out.println("Commission rate Changed SuccessFully.\nNew Rate: " + newRate + "\n");
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
                if(preparedStmt!=null)
                   preparedStmt.close();
            }
            catch(SQLException se2){
            }

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


//__________________________________________________________________________________________________

// employee functions

    public static void changePassword(String id, String newPassword) {
        int l = checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        Statement stmt = null;
        String updateString = "Update Employee set password = ? where empId = ?";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setString (1, newPassword);
            preparedStmt.setString (2, id);
            preparedStmt.execute();

            con.commit();

            System.out.println("Password Changed SuccessFully.\n");
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



//__________________________________________________________________________________________________


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
            // System.out.println(rs);
            System.out.println(rs.getString(4) + " " + password);
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
        System.out.println(password);
        HashMap<String, String> details = validate(empId, password, type);
        // System.out.println(userDetails);
        if(details.get("Login").equals("false"))
            return details;
        details.put("id", empId);
        details.put("password", password);
        return details;
    }
}  