package DataBase;

import DataBase.OracleDB.DAO.Types.All;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public abstract class Base {

    private ArrayList<All> objects = new ArrayList<>();

    public All getObject(BigInteger id) throws SQLException, ClassNotFoundException {
        for(All object: objects) {
            if(object.getId().equals(id)) {
                return object;
            }
        }
        All object = getObjectFromBase(id);
        objects.add(object);
        return object;
    }

    public void setObject(All object, String type) throws SQLException, ClassNotFoundException {
        for(All obj: objects) {
            if(obj.getId().equals(object.getId())) {
                objects.remove(obj);
            }
        }
        objects.add(object);
        setObjectToBase(object, type);
    }
    protected abstract All getObjectFromBase(BigInteger id) throws ClassNotFoundException, SQLException;
    protected abstract void setObjectToBase(All object, String type) throws ClassNotFoundException, SQLException;
}
