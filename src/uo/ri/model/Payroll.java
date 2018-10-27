package uo.ri.model;

import alb.util.console.Console;

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

        this.contract = contract;
        this.date = date;
        this.irpf = irpf;
    }

    protected void _setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getDate() {
        return date;
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

    public Contract getContract() {
        return contract;
    }
}
