
import java.sql.*; 
import java.util.*;
import java.time.*;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.*; 
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoField; 
import java.sql.DriverManager;

class UpdateEmployee {

    public static void addTimeCard(String cardNumber, String empId, LocalDate entryDate, float workHours) {
            int l = Validation.checkId(empId);
            if(l == 1 || l == 3){
                System.out.println("\nUnable to locate Employee with Id: " + empId);
                System.out.println("Please Try Again\n");
                return;
            }

            Connection con = null;
            PreparedStatement preparedStmt = null;
            Statement stmt = null;
            String updateString = "Insert into TimeCards values(?, ?, ?, ?, ?)";

            try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setString (1, cardNumber);
            preparedStmt.setString (2, empId);
            preparedStmt.setDate (3, java.sql.Date.valueOf(entryDate));
            preparedStmt.setFloat (4, workHours);
            preparedStmt.setBoolean (5, false);
            preparedStmt.execute();

            con.commit();

            System.out.println("Time Card Added SuccessFully.\n");
            return;
        }

        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nEntry already exists for given Time Card Number.\n");
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


    public static void addSaleReciept(String recieptNumber, String empId, LocalDate entryDate, int numberOfSales, float totalAmount) {
            int l = Validation.checkId(empId);
            if(l == 1 || l == 3){
                System.out.println("\nUnable to locate Employee with Id: " + empId);
                System.out.println("Please Try Again\n");
                return;
            }

            Connection con = null;
            PreparedStatement preparedStmt = null;
            Statement stmt = null;
            String updateString = "Insert into SaleReciepts values(?, ?, ?, ?, ?, ?)";

            try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            stmt=con.createStatement(); 

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setString (1, recieptNumber);
            preparedStmt.setString (2, empId);
            preparedStmt.setInt (3, numberOfSales);
            preparedStmt.setFloat (4, totalAmount);
            preparedStmt.setDate (5, java.sql.Date.valueOf(entryDate));
            preparedStmt.setBoolean (6, false);
            preparedStmt.execute();

            con.commit();

            System.out.println("Sale Reciept Added SuccessFully.\n");
            return;
        }

        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nEntry already exists for given Reciept Number\n");
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


    public static void changePassword(String id, String newPassword) {
        int l = Validation.checkId(id);
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




    public static boolean addToUnion(String id) {
        int l = Validation.checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return false;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        String updateString = "Update Employee set isInUnion = ? where empId = ?;";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setBoolean (1, true);
            preparedStmt.setString (2, id);
            preparedStmt.execute();

            preparedStmt = con.prepareStatement("insert into UnionCharges (empId) values(?);");
            preparedStmt.setString(1, id);
            preparedStmt.execute();
            con.commit();

            System.out.println("Added to Union SuccessFully.\n");
            return true;
        }

        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nprevious dues are remaining. First clear them\n");
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



    public static boolean leaveUnion(String id) {
        int l = Validation.checkId(id);
        if(l == 1 || l == 3){
            System.out.println("\nUnable to locate Employee with Id: " + id);
            System.out.println("Please Try Again\n");
            return false;
        }

        Connection con = null;
        PreparedStatement preparedStmt = null;
        String updateString = "Update Employee set isInUnion = ? where empId = ?;";

        try {
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/Employee","root","root");  

            con.setAutoCommit(false);

            preparedStmt = con.prepareStatement(updateString);

            preparedStmt.setBoolean (1, false);
            preparedStmt.setString (2, id);
            preparedStmt.execute();



            preparedStmt = con.prepareStatement("update UnionCharges set dues = dues + 100 where empId = ?;");
            preparedStmt.setString(1, id);
            preparedStmt.execute();
            con.commit();

            System.out.println("Removed from Union SuccessFully.\n");
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
}