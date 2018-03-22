package DataBase.OracleDB.DAO.Types;

import java.math.BigInteger;

/**
 * Created by User on 20.03.2018.
 * @author Shvets
 */
public class Customer extends All {

    private String name;

    public Customer(BigInteger id, String name) {
        super(id, 0, Employee.class.getSimpleName());
        this.name = name;
    }
}
