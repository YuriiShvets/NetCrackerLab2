package DataBase.OracleDB.DAO.Types;

import java.math.BigInteger;

/**
 * Created by User on 06.12.2017.
 * @author Shvets
 */
public abstract class All {
    private BigInteger id;
    private int version;

    public All(BigInteger id, int version) {
        this.id = id;
        this.version = version;
    }

    public BigInteger getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
