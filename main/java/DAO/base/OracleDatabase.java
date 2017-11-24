package DAO.base;

import DAO.base.Types.Employee;

import java.math.BigInteger;
import java.sql.*;

import java.time.LocalDateTime;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public class OracleDatabase extends Base {

    private String base = "NETCRACKERLAB2";
    private String password = "ijcnbqrehc";

    @Override
    public Object getObject(BigInteger id) {
        try {
            getTypesName(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setObject(Object object, String type) throws ClassNotFoundException, SQLException{
        if(type.equals("Employee")) {
            setEmployee((Employee)object);
        }
    }

    public String getTypesName(BigInteger id) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","NETCRACKERLAB2","ijcnbqrehc");

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from Types");
        while (rs.next()) {
            System.out.println(rs.getInt("TYPESID"));
        }

        connection.close();
        return "";
    }

    private void setEmployee(Employee employee) throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",base,password);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select TypesID from Types WHERE TypesName = '" + employee.getClass().getSimpleName() + "'");
        BigInteger typesId;
        if(!rs.next()) {    //if that type does not exist in the database
            typesId = new BigInteger("1" + getNewId());
            statement.execute("insert into Types(TypesID, TypesName, Description)" +
                                "values(" + typesId + ", '" + employee.getClass().getSimpleName() + "', 'EmployeeType')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'empNo')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'eName')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'job')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'MGR')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'hireDate')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'sal')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'comm')");
            statement.execute("insert into Atributes(AtributesID, TypesID, AtributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'deptNo')");
        }
        else {
            typesId = new BigInteger(rs.getString("TypesId"));
        }
//        while (rs.next()) {
//            System.out.println(rs.getString("TypesName"));
//        }

        connection.close();
    }
}
