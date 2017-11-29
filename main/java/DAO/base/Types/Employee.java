package DAO.base.Types;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public class Employee {
    private BigInteger empNo;   //ID
    private String eName;
    private String job;
    private BigInteger MGR; //id of manager
    private LocalDate hireDate;
    private int sal;
    private int comm;   //commission
    private BigInteger deptNo;

    public Employee(BigInteger empNo, String eName, String job, BigInteger MGR, LocalDate hireDate, int sal, int comm, BigInteger deptNo) {
        this.empNo = empNo;
        this.eName = eName;
        this.job = job;
        this.MGR = MGR;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public Employee(String eName, String job, BigInteger MGR, LocalDate hireDate, int sal, int comm, BigInteger deptNo) {
        this.eName = eName;
        this.job = job;
        this.MGR = MGR;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public BigInteger getEmpNo() {
        return empNo;
    }

    public String getJob() {
        return job;
    }

    public BigInteger getMGR() {
        return MGR;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public int getSal() {
        return sal;
    }

    public int getComm() {
        return comm;
    }

    public BigInteger getDeptNo() {
        return deptNo;
    }

    public String getEName() {

        return eName;
    }
}
