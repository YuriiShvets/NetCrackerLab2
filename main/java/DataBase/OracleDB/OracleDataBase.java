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
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Types.TypesName " +
                                                " FROM Types INNER JOIN Objects ON Types.TypesID = Objects.TypesID " +
                                                "WHERE Objects.ObjectsID = " + id);
        All object = null;
        if(rs.next()) {
            String typesName = rs.getString("TypesName");
            switch(typesName) {
                case "Employee": {
                    object = employeeDAO.getObject(id, connection);
                    break;
                }
            }
        }

        connection.close();
        return object;
    }

    @Override
    protected void setObjectToBase(All object, String type) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        switch(type) {
            case "Employee": {
                employeeDAO.setObject((Employee) object,connection);
                break;
            }
        }
        connection.close();
    }
}
