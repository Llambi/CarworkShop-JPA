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
    private Set<Payroll> payrolls = new HashSet<>();

    public Contract(Mecanico mecanico, Date startDate, double baseSalary) {
        if (baseSalary <= 0) {
            throw new IllegalStateException("el salario base debe ser positivo.");
        }
        this.startDate = new Date(Dates.firstDayOfMonth(startDate).getTime());
        this.baseSalaryPerYear = baseSalary;
        this.compensation = 0D;
        Association.Contratar.link(mecanico, this);
    }

    public Contract(Mecanico mecanico, Date startDate, double baseSalary, ContractType contractType) {
        this(mecanico, startDate, baseSalary);
        Association.Tipificar.link(contractType, this);
    }

    public Contract(Mecanico mechanic, Date today, Date ldom, int baseSalary) {
        this(mechanic, today, baseSalary);
        this.endDate = new Date(Dates.lastDayOfMonth(endDate).getTime());
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
        this.endDate = Dates.lastDayOfMonth(endDate);

        this.compensation = this.baseSalaryPerYear / 365 * getContractType().getCompensationDays();
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
     * @return
     */
    public double getIrpfPercent() {
        return 0;
    }
}
