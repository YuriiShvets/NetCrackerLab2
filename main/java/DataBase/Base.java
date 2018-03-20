package DataBase;

import DataBase.OracleDB.DAO.Types.All;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public abstract class Base {

    private Map <BigInteger, All> objects = new HashMap<BigInteger, All>();

    public All getObject(BigInteger id) throws SQLException, ClassNotFoundException {
        if(objects.containsKey(id)) {
            return getClone(objects.get(id));
        }
        All object = getObjectFromBase(id);
        objects.put(object.getId(), object);
        return getClone(object);
    }

    public boolean setObject(All object) throws SQLException, ClassNotFoundException {
        if(objects.containsKey(object.getId()) && object.getVersion() == objects.get(object.getId()).getVersion()) {
            objects.remove(object.getId());
            object.incrementVersion();
            objects.put(object.getId(), object);
            setObjectToBase(object);
            return true;
        }
        return false;
    }
    protected abstract All getObjectFromBase(BigInteger id) throws ClassNotFoundException, SQLException;
    protected abstract void setObjectToBase(All object) throws ClassNotFoundException, SQLException;

    public abstract All getClone(All original) throws SQLException, ClassNotFoundException;
}
