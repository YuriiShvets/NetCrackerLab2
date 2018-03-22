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
        System.out.println("Create base instance");
    }

    @Test
    public void getObject() throws Exception {
        Employee employee = (Employee)base.getObject(new BigInteger("23"));
        System.out.println(employee.getEName());
    }

    @Test
    public void setObject() throws Exception {
        Employee employee = new Employee(new BigInteger("23"), "Yurii20", "Middle Java developer", null, LocalDate.now(), 10000, 5000, null);
        try {
            base.setObject(employee);
            base.setObject(employee);
            base.setObject(employee);
            employee = new Employee(new BigInteger("23"), "Yurii15", "Middle Java developer", null, LocalDate.now(), 10000, 5000, null);
            base.setObject(employee);
            base.getObject(employee.getId());
            base.getObject(new BigInteger("23"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}