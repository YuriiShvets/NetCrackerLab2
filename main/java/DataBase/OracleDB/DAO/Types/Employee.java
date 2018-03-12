package DataBase.OracleDB.DAO.Types;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Created by User on 14.11.2017.
 * @author Shvets
 */
public class Employee extends All {
    private BigInteger empNo;   //ID
    private String eName;
    private String job;
    private BigInteger MGR; //id of manager
    private LocalDate hireDate;
    private int sal;
    private int comm;   //commission
    private BigInteger deptNo;

    public Employee(BigInteger empNo, String eName, String job, BigInteger MGR, LocalDate hireDate, int sal, int comm, BigInteger deptNo) {
        super(empNo, 5);
        this.empNo = empNo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getSal() != employee.getSal()) return false;
        if (getComm() != employee.getComm()) return false;
        if (getEmpNo() != null ? !getEmpNo().equals(employee.getEmpNo()) : employee.getEmpNo() != null) return false;
        if (eName != null ? !eName.equals(employee.eName) : employee.eName != null) return false;
        if (getJob() != null ? !getJob().equals(employee.getJob()) : employee.getJob() != null) return false;
        if (getMGR() != null ? !getMGR().equals(employee.getMGR()) : employee.getMGR() != null) return false;
        if (getHireDate() != null ? !getHireDate().equals(employee.getHireDate()) : employee.getHireDate() != null)
            return false;
        return getDeptNo() != null ? getDeptNo().equals(employee.getDeptNo()) : employee.getDeptNo() == null;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", eName='" + eName + '\'' +
                ", job='" + job + '\'' +
                ", MGR=" + MGR +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptNo=" + deptNo +
                '}';
    }

    @Override
    public int hashCode() {
        int result = getEmpNo() != null ? getEmpNo().hashCode() : 0;
        result = 31 * result + (eName != null ? eName.hashCode() : 0);
        result = 31 * result + (getJob() != null ? getJob().hashCode() : 0);
        result = 31 * result + (getMGR() != null ? getMGR().hashCode() : 0);
        result = 31 * result + (getHireDate() != null ? getHireDate().hashCode() : 0);
        result = 31 * result + getSal();
        result = 31 * result + getComm();
        result = 31 * result + (getDeptNo() != null ? getDeptNo().hashCode() : 0);
        return result;
    }

    public BigInteger getDeptNo() {
        return deptNo;
    }

    public String getEName() {

        return eName;
    }
}
