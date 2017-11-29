package DAO.base;

import DAO.base.Types.Employee;

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
        Employee employee = new Employee(new BigInteger("21"), "Yurii3", "Middle Java developer", null, LocalDate.now(), 10000, 5000, null);
        Base base = new OracleDatabase();
        try {
            base.setObject(employee, employee.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Employee employee2 = new Employee(new BigInteger("22"), "Yurii", "Junior Java developer", null, LocalDate.now(), 10000, 5000, null);
        try {
            base.setObject(employee2, employee2.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(employee.getEmpNo());
        System.out.println(employee2.getEmpNo());
    }

}