package DAO.base.Types;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public class Employee {
    private BigInteger empNo;
    private String eName;
    private String job;
    private BigInteger MGR; //id of boss
    private LocalDate hireDate;
    private int sal;
    private int comm;   //commission
    private BigInteger deptNo;
}
