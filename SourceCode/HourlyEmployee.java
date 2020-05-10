
import java.sql.*;
import java.util.*;

class HourlyEmployee extends Employee {

    private float hourRate;

    // called while adding new employee
    public MonthlyEmployee(String name, String id, String password, String mop, boolean isInUnion, float commissionRate, float hourRate) {
        super(name, id, password, mop, isInUnion, commissionRate);
        this.hourRate = hourRate;
    }

    public float getHourRate() {
        return hourRate;
    }


} 