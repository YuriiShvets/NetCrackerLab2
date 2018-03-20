package DataBase.OracleDB;

import DataBase.Base;
import DataBase.OracleDB.DAO.EmployeeDAO;
import DataBase.OracleDB.DAO.Types.All;
import DataBase.OracleDB.DAO.Types.Employee;
import DataBase.OracleDB.DAO.*;

import java.math.BigInteger;
import java.sql.*;

/**
 * Created by User on 14.11.2017.
 *
 * @author Shvets
 */
public class OracleDataBase extends Base {

    private String base = "NETCRACKERLAB2";
    private String password = "ijcnbqrehc";

    private DAO employeeDAO;

    public OracleDataBase() {
        employeeDAO = new EmployeeDAO();
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", base, password);
        return connection;
    }

    @Override
    protected All getObjectFromBase(BigInteger id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        All object = null;
        String typesName = getTypesName(id);

        switch (typesName) {
            case "Employee": {
                object = employeeDAO.getObject(id, connection);
                break;
            }
        }
        connection.close();
        return object;
}

    private String getTypesName(BigInteger id) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Types.TypesName " +
                " FROM Types INNER JOIN Objects ON Types.TypesID = Objects.TypesID " +
                "WHERE Objects.ObjectsID = " + id);
        String typesName = null;
        if (rs.next()) {
            typesName = rs.getString("TypesName");
        }
        connection.close();
        return typesName;
    }

    @Override
    protected void setObjectToBase(All object) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        switch (object.getType()) {
            case "Employee": {
                employeeDAO.setObject((Employee) object, connection);
                break;
            }
        }
        connection.close();
    }

    @Override
    public All getClone(All original) throws SQLException, ClassNotFoundException {
        String typesName = getTypesName(original.getId());

        All newObject = null;

        switch (typesName) {
            case "Employee": {
                newObject = new Employee((Employee)original);
                break;
            }
        }
        return newObject;
    }
}
