package DataBase.OracleDB.DAO.Types;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by User on 15.11.2017.
 * @author Shvets
 */
public class Department extends All {

    private BigInteger deptNo;
    private String dName;
    private String loc;
    private ArrayList<Employee> employees;

    Department(BigInteger id) {
        super(id, 0, Department.class.getSimpleName());
    }
}
