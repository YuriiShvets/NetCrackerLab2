package DataBase.OracleDB.DAO.Types;

import java.math.BigInteger;

/**
 * Created by User on 06.12.2017.
 * @author Shvets
 */
public abstract class All{
    private BigInteger id;
    private int version;
    private String type;

    public All(BigInteger id, int version, String type) {
        this.id = id;
        this.version = version;
        this.type = type;
    }

    public All  (All original) {
        this.id = original.getId();
        this.version = original.getVersion();
    }

    public BigInteger getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void incrementVersion() {
        version++;
    }

    public String getType() {
        return type;
    }
}
