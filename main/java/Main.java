import DAO.base.Base;
import DAO.base.OracleDatabase;
import DAO.base.Types.Employee;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        //System.out.println(employee.getClass().getSimpleName());
        //Random random = new Random();
        //BigInteger id = new BigInteger("" + LocalDateTime.now().getYear() + LocalDateTime.now().getMinute() + LocalDateTime.now().getDayOfMonth() + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + random.nextInt(100));
        Base base = new OracleDatabase();
        //System.out.println(id);
        try {
            base.setObject(employee, employee.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            base.getObject(id);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
