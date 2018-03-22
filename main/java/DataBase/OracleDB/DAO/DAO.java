package DataBase.OracleDB.DAO;

import DataBase.OracleDB.DAO.Types.All;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by User on 12.03.2018.
 * @author Shvets
 */
public abstract class DAO {

    protected static BigInteger getNewId() {
        //private LinkedHashMap map = new
        Random random = new Random();
        BigInteger id = new BigInteger("" + LocalDateTime.now().getYear() + LocalDateTime.now().getMinute() + LocalDateTime.now().getDayOfMonth() + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + random.nextInt(10000));
        return id;
    }

    public static BigInteger getNewObjectId() {
        return new BigInteger("2" + getNewId());
    }

    protected ResultSet getResultSetOfObject(Connection connection, BigInteger id) throws ClassNotFoundException, SQLException {

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT Attributes.AttributesName, Parameters.NumbersData, Parameters.StringData, Parameters.DateData, Relations.ObjectsID RelationObjectID " +
                " FROM Objects INNER JOIN Types ON Objects.TypesID = Types.TypesID " +
                "INNER JOIN Attributes ON Types.TypesID = Attributes.TypesID " +
                "INNER JOIN Parameters ON Parameters.AttributesID = Attributes.AttributesID AND Parameters.ObjectsID = Objects.ObjectsID " +
                "LEFT JOIN Relations ON Parameters.Relation = Relations.Relation " +
                "WHERE Objects.ObjectsID = " + id);
        return rs;
    }

    public abstract All getObject(BigInteger id, Connection connection) throws ClassNotFoundException, SQLException;
    public abstract boolean setObject(All object, Connection connection) throws ClassNotFoundException, SQLException;
    protected abstract BigInteger getTypesId(Statement statement) throws ClassNotFoundException, SQLException;
}
