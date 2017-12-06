package DAO;

import DAO.Types.Employee;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 24.11.2017.
 * @author Shvets
 */
public class OracleDatabaseTest {
    @org.junit.Test
    public void setObject() throws Exception {
        Employee employee = new Employee(Base.getNewObjectId(), "Yurii3", "Middle Java developer", null, LocalDate.now(), 10000, 5000, null);
        Base base = new OracleDatabase();
        try {
            base.setObject(employee, employee.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Employee employee2 = null;
        Employee employee3 = null;
        try {
            employee2 = (Employee)base.getObject(employee.getEmpNo());
            employee3 = (Employee)base.getObjectFromBase(employee.getEmpNo());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(employee);
        System.out.println(employee2);
        System.out.println(employee3);

        assertTrue(employee == employee2);
        assertTrue(employee.equals(employee3));
    }

}