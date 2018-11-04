package uo.ri.model;

import alb.util.console.Console;
import alb.util.date.Dates;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.ContractStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        this.date = new Date(Dates.lastDayOfMonth(Dates.subMonths(date, 1)).getTime());
        if (Dates.isBefore(this.date, contract.getStartDate())) {
            throw new IllegalArgumentException("La fecha de la nomina debe ser posterior a la de comienzo del contrato.");
        }

        Association.Nominalizar.link(contract, this);

        this.baseSalary = this.contract.getBaseSalaryPerYear() / 14;

        if (Dates.isSameMonth(this.date, Dates.fromDdMmYyyy(01, 06, 2010))
                || Dates.isSameMonth(this.date, Dates.fromDdMmYyyy(01, 12, 2010)))
            this.extraSalary = this.contract.getBaseSalaryPerYear() / 14;

        this.productivity = productivityTimes * this.contract.getContractCategory().getProductivityPlus();

        /*this.productivity = this.contract.getMechanic().getIntervenciones().stream()
                .filter(inter -> Dates.isSameMonth(inter.getAveria().getFecha(), this.date)
                        && inter.getAveria().getStatus().equals(AveriaStatus.ABIERTA))
                .mapToDouble(Intervencion::getImporte)
                .sum() * this.contract.getContractCategory().getProductivityPlus();*/
        this.trieniums = (monthsWorked() / 3) * this.contract.getContractCategory().getTrienniumSalary();

        this.irpf = contract.getIrpfPercent() * getGrossTotal();

        this.socialSecurity = (this.contract.getBaseSalaryPerYear() / 12) * 0.05;
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
        return this.baseSalary + this.extraSalary + this.productivity + this.trieniums;
    }

    public double getDiscountTotal() {
        return this.irpf + this.socialSecurity;
    }

    public double getNetTotal() {
        return getGrossTotal() - getDiscountTotal();
    }

    private int monthsWorked() {
        return Period.between(
                this.contract.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .getYears();
    }

}


