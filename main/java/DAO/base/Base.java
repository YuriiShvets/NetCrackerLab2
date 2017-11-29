package DAO.base;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public abstract class Base {
    protected static BigInteger getNewId() {
        Random random = new Random();
        BigInteger id = new BigInteger("" + LocalDateTime.now().getYear() + LocalDateTime.now().getMinute() + LocalDateTime.now().getDayOfMonth() + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + random.nextInt(10000));
        return id;
    }

    public static BigInteger getNewObjectId() {
        return new BigInteger("2" + getNewId());
    }
    public abstract Object getObject(BigInteger id);
    public abstract void setObject(Object object, String type) throws ClassNotFoundException, SQLException;
}
