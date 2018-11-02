package uo.ri.model;

import alb.util.date.Dates;
import uo.ri.model.types.ContractStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Contract {
    //TODO: hash, equals, toString
    private Date startDate;
    private Date endDate;
    private double baseSalaryPerYear;
    private double compensation;
    private ContractStatus status;

    //Atributos accidentales
    private Mecanico mecanico;
    private ContractType contractType;
    private ContractCategory contractCategory;
    private Set<Payroll> payrolls = new HashSet<>();

    public Contract(Mecanico mecanico, Date startDate, double baseSalary) {
        if (baseSalary <= 0) {
            throw new IllegalArgumentException("el salario base debe ser positivo.");
        }
        this.startDate = new Date(Dates.firstDayOfMonth(startDate).getTime());
        this.baseSalaryPerYear = baseSalary;
        this.compensation = 0D;
        this.status = ContractStatus.ACTIVE;
        Association.Contratar.link(mecanico, this);
    }

    public Contract(Mecanico mecanico, Date startDate, double baseSalary, ContractType contractType) {
        this(mecanico, startDate, baseSalary);
        Association.Typefy.link(this, contractType);
    }

    public Contract(Mecanico mechanic, Date startDate, Date endDate, double baseSalary) {
        this(mechanic, startDate, baseSalary);
        this.endDate = endDate == null ? null : new Date(Dates.lastDayOfMonth(endDate).getTime());
    }

    protected void _setContractCategory(ContractCategory contractCategory) {
        this.contractCategory = contractCategory;
    }

    public ContractCategory getContractCategory() {
        return contractCategory;
    }

    protected void _setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    protected Set<Payroll> _getPayrolls() {
        return payrolls;
    }

    public Set<Payroll> getPayrolls() {
        return new HashSet<>(payrolls);
    }

    public ContractType getContractType() {
        return contractType;
    }

    protected void _setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return new Date(endDate.getTime());
    }

    public void setEndDate(Date endDate) {
        this.endDate = new Date(endDate.getTime());
    }

    public double getBaseSalaryPerYear() {
        return baseSalaryPerYear;
    }

    public double getCompensation() {
        return compensation;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public Mecanico getMechanic() {
        return mecanico;
    }


    /**
     * Metodo que fija la fecha de finalizacion de un contrato, calcula la compensacion del contrato
     *
     * @param endDate
     * @throws IllegalArgumentException si:
     *                                  - La fecha de finalizacion es anterior a la de inicio.
     *                                  - El contrato ya estaba finalizado.
     */
    public void markAsFinished(Date endDate) {
        if (Dates.isBefore(endDate, this.startDate)) {
            throw new IllegalArgumentException("La fecha de finalizacion del contrato debe ser posterior a las del comienzo de este.");
        }
        if (this.status.equals(ContractStatus.FINISHED)) {
            throw new IllegalStateException("Un contrato ya finalizado no se puede volver a finalizar.");
        }
        this.status = ContractStatus.FINISHED;

        this.endDate = Dates.lastDayOfMonth(endDate);

        if (monthsWorked() >= 12) {
            this.compensation = this.baseSalaryPerYear / 365 * getContractType().getCompensationDays();
        }

    }

    private double monthsWorked() {
        if (this.status.equals(ContractStatus.FINISHED))
            return Dates.diffDays(this.endDate, this.startDate) / 30D;
        return 0D;
    }

    /**
     * Metodp que comprueba si un contrato esta activo o finalizado;
     *
     * @return True si esta Finalizado, False si esta activo.
     */
    public boolean isFinished() {
        return this.status.equals(ContractStatus.FINISHED);
    }

    /**
     * Metodo que devuelve la ultima nomina del contrato o null si no hay nominas para el contrato.
     *
     * @return Payroll con la ultima fecha o null si no hay.
     */
    public Payroll getLastPayroll() {
        Payroll selectedPayroll = null;
        for (Payroll payroll : payrolls)
            if (selectedPayroll == null || Dates.isAfter(payroll.getDate(), selectedPayroll.getDate()))
                selectedPayroll = payroll;
        return selectedPayroll;
    }

    /**
     * Metodo que devuelve el porcentaje de irpf correcto.
     *
     * @return Porcentaje de irpf segun el tramo de sueldo en el que te encuentres.
     */
    public double getIrpfPercent() {
        double percent;
        if (this.baseSalaryPerYear < 12000) percent = 0;
        else if (this.baseSalaryPerYear < 30000) percent = 0.1;
        else if (this.baseSalaryPerYear < 40000) percent = 0.15;
        else if (this.baseSalaryPerYear < 50000) percent = 0.20;
        else if (this.baseSalaryPerYear < 60000) percent = 0.30;
        else percent = 0.4;
        return percent;
    }
}
