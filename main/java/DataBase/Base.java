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

    private Map <BigInteger, All> objects = new HashMap<>();

    public All getObject(BigInteger id) throws SQLException, ClassNotFoundException {
        if(objects.containsKey(id)) {
            System.out.println("Returning from bufer" + id);
            return getClone(objects.get(id));
        }
        All object = getObjectFromBase(id);
        objects.put(object.getId(), object);
        System.out.println("returning from base" + id);
        return getClone(object);
    }

    public boolean setObject(All object) throws SQLException, ClassNotFoundException {
        if(!objects.containsKey(object.getId())) {
            objects.put(object.getId(), object);
            setObjectToBase(object);
            System.out.println("Put new Object to bufer" + object.getId());
            return true;
        }
        else {
            if (object.getVersion() == objects.get(object.getId()).getVersion()) {
                objects.remove(object.getId());
                object.incrementVersion();
                objects.put(object.getId(), object);
                setObjectToBase(object);
                System.out.println("Update object in bufer" + object.getId());
                return true;
            }
        }
        System.out.println("Ooops, wrong version");
        return false;
    }
    protected abstract All getObjectFromBase(BigInteger id) throws ClassNotFoundException, SQLException;
    protected abstract void setObjectToBase(All object) throws ClassNotFoundException, SQLException;

    protected abstract All getClone(All original) throws SQLException, ClassNotFoundException;
}
