package uo.ri.model;

import alb.util.console.Console;
import alb.util.date.Dates;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.ContractStatus;

import java.util.Date;

public class Payroll {
    //TODO: hash, equals, toString
    private Date date;
    private double baseSalary;
    private double extraSalary;
    private double productivity;
    private double trieniums;
    private double irpf;
    private double socialSecurity;

    //Atributos accidentales
    private Contract contract;


    public Payroll(Contract contract, Date date, double irpf) {

        this.date = new Date(Dates.lastDayOfMonth(Dates.subMonths(date, 1)).getTime());
        if (Dates.isBefore(this.date, contract.getStartDate())) {
            throw new IllegalArgumentException("La fecha de la nomina debe ser posterior a la de comienzo del contrato.");
        }
        this.irpf = irpf;
        Association.Nominalizar.link(contract, this);

        this.baseSalary = this.contract.getBaseSalaryPerYear() / 14;

        this.productivity = this.contract.getMechanic().getIntervenciones().stream()
                .filter(inter -> Dates.isSameMonth(inter.getAveria().getFecha(), this.date)
                        && inter.getAveria().getStatus().equals(AveriaStatus.ABIERTA))
                .mapToDouble(Intervencion::getImporte)
                .sum() * this.contract.getContractCategory().getProductivityPlus();

        this.socialSecurity = (this.contract.getBaseSalaryPerYear() / 12) * 0.05;

        this.trieniums = (this.contract._monthsWorked() / 12) / 3 * this.contract.getContractCategory().getTrienniumSalary();
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

}
