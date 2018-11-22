package uo.ri.model;

import alb.util.date.Dates;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class Payroll {
    private Date date;
    private double baseSalary;
    private double extraSalary;
    private double productivity;
    private double trieniums;
    private double irpf;
    private double socialSecurity;

    //Atributos accidentales
    private Long id;
    private Contract contract;

    public Payroll() {
    }

    public Payroll(Contract contract, Date date, double productivityTimes) {

        this.date = new Date(Dates.lastDayOfMonth(Dates
                .subMonths(date, 1)).getTime());
        if (Dates.isBefore(this.date, contract.getStartDate())) {
            throw new IllegalArgumentException
                    ("La fecha de la nomina debe ser posterior a la de " +
                            "comienzo del contrato.");
        }

        Association.Nominalizar.link(contract, this);

        this.baseSalary = this.contract.getBaseSalaryPerYear() / 14;

        if (Dates.isSameMonth(this.date, Dates
                .fromDdMmYyyy(01, 06, 2010))
                || Dates.isSameMonth(this.date,
                Dates.fromDdMmYyyy(01, 12, 2010)))
            this.setExtraSalary(this.contract.getBaseSalaryPerYear());

        this.setProductivity(productivityTimes);

        this.setTrieniums(this.contract.getContractCategory()
                .getTrieniumSalary());

        this.setIrpf(contract.getIrpfPercent());

        this.setSocialSecurity(this.contract.getBaseSalaryPerYear());
    }

    public void setExtraSalary(double extraSalary) {
        this.extraSalary = extraSalary / 14;
    }

    public void setProductivity(double productivity) {
        this.productivity = productivity * this.contract
                .getContractCategory().getProductivityPlus();
    }

    public void setTrieniums(double trieniums) {
        this.trieniums = (monthsWorked() / 3) * trieniums;
    }

    public void setIrpf(double irpf) {
        this.irpf = irpf * getGrossTotal();
    }

    public void setSocialSecurity(double baseSalary) {
        this.socialSecurity = (baseSalary / 12) * 0.05;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "date=" + date +
                ", baseSalary=" + baseSalary +
                ", extraSalary=" + extraSalary +
                ", productivity=" + productivity +
                ", trieniums=" + trieniums +
                ", irpf=" + irpf +
                ", socialSecurity=" + socialSecurity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payroll payroll = (Payroll) o;
        return Objects.equals(date, payroll.date) &&
                Objects.equals(contract, payroll.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, contract);
    }

    protected void _setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getExtraSalary() {
        return extraSalary;
    }

    public double getProductivity() {
        return productivity;
    }

    public double getTrieniums() {
        return trieniums;
    }

    public double getIrpf() {
        return irpf;
    }

    public double getSocialSecurity() {
        return socialSecurity;
    }

    public double getTriennium() {
        return this.trieniums;
    }

    public Contract getContract() {
        return contract;
    }

    public double getGrossTotal() {
        return this.baseSalary + this.extraSalary + this.productivity
                + this.trieniums;
    }

    public double getDiscountTotal() {
        return this.irpf + this.socialSecurity;
    }

    public double getNetTotal() {
        return getGrossTotal() - getDiscountTotal();
    }

    public Long getId() {
        return id;
    }

    private int monthsWorked() {
        return Period.between(this.contract.getStartDate()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                this.date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                .getYears();
    }

}


