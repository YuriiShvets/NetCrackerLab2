package DAO;

import DAO.Types.All;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public abstract class Base {

    private ArrayList<All> objects = new ArrayList<>();

    protected static BigInteger getNewId() {
        Random random = new Random();
        BigInteger id = new BigInteger("" + LocalDateTime.now().getYear() + LocalDateTime.now().getMinute() + LocalDateTime.now().getDayOfMonth() + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + random.nextInt(10000));
        return id;
    }

    public static BigInteger getNewObjectId() {
        return new BigInteger("2" + getNewId());
    }

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
