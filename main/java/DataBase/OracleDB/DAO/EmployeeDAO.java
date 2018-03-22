package DataBase.OracleDB.DAO;

import DataBase.OracleDB.DAO.Types.All;
import DataBase.OracleDB.DAO.Types.Employee;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;

/**
 * Created by User on 12.03.2018.
 * @author Shvets
 */
public class EmployeeDAO extends DAO{

    public All getObject(BigInteger id, Connection connection) throws ClassNotFoundException, SQLException {

        ResultSet rs = getResultSetOfObject(connection, id);
        BigInteger empNo = null;
        String eName = null;
        String job = null;
        BigInteger MGR = null;
        LocalDate hireDate = null;
        int sal = 0;
        int comm = 0;
        BigInteger deptNo = null;
        while(rs.next()) {
            switch(rs.getString("AttributesName")) {
                case "empNo": {
                    empNo = new BigInteger(rs.getString("NumbersData"));
                    break;
                }
                case "eName": {
                    eName = rs.getString("StringData");
                    break;
                }
                case "job": {
                    job = rs.getString("StringData");
                    break;
                }
                case "MGR": {
                    try {
                        MGR = new BigInteger(rs.getString("RELATIONOBJECTID"));
                    } catch(NullPointerException e) {
                        System.out.println("MGR is NULL");
                    }
                    break;
                }
                case "hireDate": {
                    String date = rs.getString("DateData").split(" ")[0];
                    hireDate = LocalDate.of(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2]));
                    break;
                }
                case "sal": {
                    sal = rs.getInt("NumbersData");
                    break;
                }
                case "comm": {
                    comm = rs.getInt("NumbersData");
                    break;
                }
                case "deptNo": {
                    try {
                        deptNo = new BigInteger(rs.getString("RELATIONOBJECTID"));
                    } catch (NullPointerException e) {
                        System.out.println("deptNo is NULL");
                    }
                    break;
                }
            }
        }
        Employee employee = new Employee(empNo, eName, job, MGR, hireDate, sal, comm, deptNo);
        return employee;
    }

    public boolean setObject(All object, Connection connection) throws ClassNotFoundException, SQLException {

        if(object.equals(null)) {
            return false;
        }

        Employee employee = (Employee)object;

        Statement statement = connection.createStatement();

        BigInteger typesId = getTypesId(statement);

        ResultSet rs = statement.executeQuery("SELECT ObjectsId " +
                "FROM Objects " +
                "WHERE ObjectsId = " + employee.getEmpNo());
        if (rs.next()) {    //if object already exist
            updateEmployee(connection, employee);
        } else {
            //add new Employee
            statement.execute("insert into Objects(ObjectsID, TypesID, ObjectsName)" +
                    "values(" + employee.getEmpNo() + ", " + typesId + ", 'Employee')");
            rs = statement.executeQuery("SELECT AttributesId, AttributesName " +
                    "FROM Attributes " +
                    "WHERE TypesId = " + typesId);
            Statement statement2 = connection.createStatement();
            while (rs.next()) {
                String attributesName = rs.getString("AttributesName");
                BigInteger attributesId = new BigInteger(rs.getString("AttributesId"));
                switch (attributesName) {
                    case "empNo": {
                        statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, NumbersData)" +
                                "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", " + employee.getEmpNo() + ")");
                        break;
                    }
                    case "eName": {
                        statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, StringData)" +
                                "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", '" + employee.getEName() + "')");
                        break;
                    }
                    case "job": {
                        statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, StringData)" +
                                "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", '" + employee.getJob() + "')");
                        break;
                    }
                    case "MGR": {
                        if (employee.getMGR() != null) {
                            BigInteger relation = getNewId();
                            statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, Relation)" +
                                    "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", " + relation + ")");
                            statement2.execute("INSERT INTO Relations(RelationsID, Relation, ObjectsID)" +
                                    "VALUES(5" + getNewId() + ", " + relation + ", " + employee.getMGR() + ")");
                        } else {
                            statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID)" +
                                    "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ")");
                        }
                        break;
                    }
                    case "hireDate": {
                        statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, DateData)" +
                                "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", TO_DATE('" + employee.getHireDate().getDayOfMonth() + "/" + employee.getHireDate().getMonthValue() + "/" + employee.getHireDate().getYear() + "', 'dd/mm/yyyy'))");
                        break;
                    }
                    case "sal": {
                        statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, NumbersData)" +
                                "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", " + employee.getSal() + ")");
                        break;
                    }
                    case "comm": {
                        statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, NumbersData)" +
                                "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", " + employee.getComm() + ")");
                        break;
                    }
                    case "deptNo": {
                        if (employee.getDeptNo() != null) {
                            BigInteger relation = getNewId();
                            statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID, Relation)" +
                                    "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ", " + relation + ")");
                            statement2.execute("INSERT INTO Relations(RelationsID, Relation, ObjectsID)" +
                                    "VALUES(5" + getNewId() + ", " + relation + ", " + employee.getDeptNo() + ")");
                        } else {
                            statement2.execute("insert into Parameters(ParametersID, ObjectsID, AttributesID)" +
                                    "values(4" + getNewId() + ", " + employee.getEmpNo() + ", " + attributesId + ")");
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }

    private void updateEmployee(Connection connection, Employee employee) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.getResultSet();
        //eName
        statement.executeUpdate("UPDATE Parameters " +
                "SET StringData = '" + employee.getEName() +
                "' WHERE ObjectsId = " + employee.getEmpNo() +
                " AND AttributesId = (SELECT Attributes.AttributesId " +
                "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                "WHERE Attributes.AttributesName = 'eName' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        //job
        statement.executeUpdate("UPDATE Parameters " +
                "SET StringData = '" + employee.getJob() +
                "' WHERE ObjectsId = " + employee.getEmpNo() +
                " AND AttributesId = (SELECT Attributes.AttributesId " +
                "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                "WHERE Attributes.AttributesName = 'job' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        //MGR
        if(employee.getMGR() == null) { //if not exist
            statement.executeUpdate("DELETE Relations " +
                    " WHERE Relation = (SELECT Relation " +
                    "FROM Parameters " +
                    "WHERE ObjectsId = " + employee.getEmpNo() +
                    " AND Relation IS NOT NULL)");
            statement.executeUpdate("UPDATE Parameters " +
                    "SET Relation = NULL" +
                    " WHERE ObjectsId = " + employee.getEmpNo() +
                    " AND AttributesId = (SELECT Attributes.AttributesId " +
                    "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                    "WHERE Attributes.AttributesName = 'MGR' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        }
        else {
            rs = statement.executeQuery("SELECT Relation, RelationID " +
                    "FROM Parameters INNER JOIN Relations ON Parameters.Relation = Relations.Relation " +
                    "WHERE ObjectsId = " + employee.getEmpNo() +
                    " AND Parameters.AttributesId = (SELECT AttributesID " +
                    "FROM Attributes " +
                    "WHERE AttributesName = 'MGR')");
            if(rs.next()) { //if Relation already exist
                Statement statement2 = connection.createStatement();
                statement2.executeUpdate("UPDATE Relations " +
                        "SET ObjectsID = " + employee.getMGR() +
                        " WHERE RelationsID = " + rs.getInt("RelationID"));
            }
            else {
                BigInteger relation = getNewId();
                statement.executeUpdate("UPDATE Parameters " +
                        "SET Relation = " + relation +
                        " WHERE ObjectsId = " + employee.getEmpNo() +
                        " AND AttributesId = (SELECT Attributes.AttributesId " +
                        "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                        "WHERE Attributes.AttributesName = 'MGR' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
                statement.execute("INSERT INTO Relations(RelationsID, Relation, ObjectsID)" +
                        "VALUES(5" + getNewId() + ", " + relation + ", " + employee.getMGR() + ")");
            }
        }
        //hireDate
        statement.executeUpdate("UPDATE Parameters " +
                "SET DateData = TO_DATE('" + employee.getHireDate().getDayOfMonth() + "/" + employee.getHireDate().getMonthValue() + "/" + employee.getHireDate().getYear() + "', 'dd/mm/yyyy')" +
                " WHERE ObjectsId = " + employee.getEmpNo() +
                " AND AttributesId = (SELECT Attributes.AttributesId " +
                "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                "WHERE Attributes.AttributesName = 'hireDate' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        //sal
        statement.executeUpdate("UPDATE Parameters " +
                "SET NumbersData = " + employee.getSal() +
                " WHERE ObjectsId = " + employee.getEmpNo() +
                " AND AttributesId = (SELECT Attributes.AttributesId " +
                "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                "WHERE Attributes.AttributesName = 'sal' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        //comm
        statement.executeUpdate("UPDATE Parameters " +
                "SET NumbersData = " + employee.getComm() +
                " WHERE ObjectsId = " + employee.getEmpNo() +
                " AND AttributesId = (SELECT Attributes.AttributesId " +
                "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                "WHERE Attributes.AttributesName = 'comm' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        //deptNo
        if(employee.getDeptNo() == null) { //if not exist
            statement.executeUpdate("DELETE Relations " +
                    " WHERE Relation = (SELECT Relation " +
                    "FROM Parameters " +
                    "WHERE ObjectsId = " + employee.getEmpNo() +
                    " AND Relation IS NOT NULL)");
            statement.executeUpdate("UPDATE Parameters " +
                    "SET Relation = NULL" +
                    " WHERE ObjectsId = " + employee.getEmpNo() +
                    " AND AttributesId = (SELECT Attributes.AttributesId " +
                    "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                    "WHERE Attributes.AttributesName = 'deptNo' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
        }
        else {
            rs = statement.executeQuery("SELECT Relation, RelationID " +
                    "FROM Parameters INNER JOIN Relations ON Parameters.Relation = Relations.Relation " +
                    "WHERE ObjectsId = " + employee.getEmpNo() +
                    " AND Parameters.AttributesId = (SELECT AttributesID " +
                    "FROM Attributes " +
                    "WHERE AttributesName = 'deptNo')");
            if(rs.next()) { //if Relation already exist
                Statement statement2 = connection.createStatement();
                statement2.executeUpdate("UPDATE Relations " +
                        "SET ObjectsID = " + employee.getDeptNo() +
                        " WHERE RelationsID = " + rs.getInt("RelationID"));
            }
            else {
                BigInteger relation = getNewId();
                statement.executeUpdate("UPDATE Parameters " +
                        "SET Relation = " + relation +
                        " WHERE ObjectsId = " + employee.getEmpNo() +
                        " AND AttributesId = (SELECT Attributes.AttributesId " +
                        "FROM Attributes INNER JOIN Parameters ON Attributes.AttributesId = Parameters.AttributesID " +
                        "WHERE Attributes.AttributesName = 'deptNo' AND Parameters.ObjectsId = '" + employee.getEmpNo() + "')");
                statement.execute("INSERT INTO Relations(RelationsID, Relation, ObjectsID)" +
                        "VALUES(5" + getNewId() + ", " + relation + ", " + employee.getDeptNo() + ")");
            }
        }
    }

    @Override
    public BigInteger getTypesId(Statement statement) throws ClassNotFoundException, SQLException {
        ResultSet rs = statement.executeQuery("select TypesID from Types WHERE TypesName = '" + Employee.class.getSimpleName() + "'");
        BigInteger typesId;
        if (!rs.next()) {    //if that type does not exist in the database
            typesId = new BigInteger("1" + getNewId());
            statement.execute("insert into Types(TypesID, TypesName, Description)" +
                    "values(" + typesId + ", '" + Employee.class.getSimpleName() + "', 'EmployeeType')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'empNo')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'eName')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'job')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'MGR')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'hireDate')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'sal')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'comm')");
            statement.execute("insert into Attributes(AttributesID, TypesID, AttributesName)" +
                    "values(3" + getNewId() + ", '" + typesId + "', 'deptNo')");
        } else {
            typesId = new BigInteger(rs.getString("TypesId"));
        }
        return typesId;
    }
}
