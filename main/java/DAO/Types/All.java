package DAO.Types;

import java.math.BigInteger;

/**
 * Created by User on 06.12.2017.
 */
public abstract class All {
    private BigInteger id;

    public All(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }
}
