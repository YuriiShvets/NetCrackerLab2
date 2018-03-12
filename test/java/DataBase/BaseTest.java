package DataBase;

import DataBase.OracleDB.DAO.Types.Employee;
import DataBase.OracleDB.OracleDataBase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by User on 13.03.2018.
 * @author Shvets
 */
public class BaseTest {
    Base base;
    @Before
    public void setUp() throws Exception {
        base = new OracleDataBase();
    }

    @Test
    public void getObject() throws Exception {
        Employee employee = (Employee)base.getObject(new BigInteger("12"));
        System.out.println(employee.getEName());
    }

    @Test
    public void setObject() throws Exception {
        Employee employee = new Employee(new BigInteger("12"), "Yurii4", "Middle Java developer", null, LocalDate.now(), 10000, 5000, null);
        try {
            base.setObject(employee, employee.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}