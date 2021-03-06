package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import alb.util.date.Dates;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.ContractStatus;

public class Contract {
    private Date startDate;
    private Date endDate;
    private double baseSalaryPerYear;
    private double compensation;
    private ContractStatus status;

    // Atributos accidentales
    private Long id;
    private Mecanico mecanico;
    private ContractType contractType;
    private ContractCategory contractCategory;
    private Set<Payroll> payrolls = new HashSet<>();

    public Contract() {
    }

    public Contract(Mecanico mecanico, Date startDate,
	    double baseSalary) {
	if (baseSalary <= 0) {
	    throw new IllegalArgumentException(
		    "el salario base debe ser positivo.");
	}
	this.setStartDate(startDate);
	this.setBaseSalaryPerYear(baseSalary);
	this.setCompensation(0D);
	this.setStatus(ContractStatus.ACTIVE);
	Association.Contratar.link(mecanico, this);
    }

    public Contract(Mecanico mecanico, Date startDate,
	    double baseSalary, ContractType contractType) {
	this(mecanico, startDate, baseSalary);
	Association.Typefy.link(this, contractType);
    }

    public Contract(Mecanico mechanic, Date startDate, Date endDate,
	    double baseSalary) {
	this(mechanic, startDate, baseSalary);
	this.setEndDate(endDate);
    }

    protected void _setContractCategory(
	    ContractCategory contractCategory) {
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

    public void setStartDate(Date startDate) {
	this.startDate = new Date(
		Dates.firstDayOfMonth(startDate).getTime());
    }

    public Date getEndDate() {
	return endDate == null ? null : new Date(endDate.getTime());
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate == null ? null
		: new Date(Dates.lastDayOfMonth(endDate).getTime());
    }

    public double getBaseSalaryPerYear() {
	return baseSalaryPerYear;
    }

    public void setBaseSalaryPerYear(double baseSalaryPerYear) {
	this.baseSalaryPerYear = baseSalaryPerYear;
    }

    public double getCompensation() {
	return compensation;
    }

    public void setCompensation(double compensation) {
	this.compensation = compensation;
    }

    public ContractStatus getStatus() {
	return status;
    }

    public void setStatus(ContractStatus status) {
	this.status = status;
    }

    public Mecanico getMechanic() {
	return mecanico;
    }

    public Long getId() {
	return id;
    }

    @Override
    public String toString() {
	return "Contract{" + "startDate=" + startDate + ", endDate="
		+ endDate + ", baseSalaryPerYear=" + baseSalaryPerYear
		+ ", compensation=" + compensation + ", status="
		+ status + ", mecanico=" + mecanico
		+ ", contractType=" + contractType
		+ ", contractCategory=" + contractCategory + '}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	Contract contract = (Contract) o;
	return Objects.equals(startDate, contract.startDate)
		&& Objects.equals(mecanico, contract.mecanico);
    }

    @Override
    public int hashCode() {
	return Objects.hash(startDate, mecanico);
    }

    /**
     * Metodo que fija la fecha de finalizacion de un contrato, calcula la
     * compensacion del contrato
     *
     * @param endDate
     * @throws IllegalArgumentException si: - La fecha de finalizacion es anterior a
     *                                  la de inicio. - El contrato ya estaba
     *                                  finalizado.
     */
    public void markAsFinished(Date endDate) {
	if (Dates.isBefore(endDate, this.startDate)) {
	    throw new IllegalArgumentException(
		    "La fecha de finalizacion del contrato debe ser "
			    + "posterior a las del comienzo de este.");
	}
	if (this.status.equals(ContractStatus.FINISHED)) {
	    throw new IllegalStateException(
		    "Un contrato ya finalizado no puede volver a finalizarse");
	}
	this.status = ContractStatus.FINISHED;

	this.endDate = Dates.lastDayOfMonth(endDate);

	if (monthsWorked() >= 12) {
	    this.compensation = this.baseSalaryPerYear / 365
		    * getContractType().getCompensationDays();
	}

    }

    /**
     * Metodo que calcula los mesese trabajados de un contrato.
     * 
     * @return Los meses trabajados si un contrato esta finalizado o 0 si aun no
     *         esta finalizado.
     */
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
     * Metodo que devuelve la ultima nomina del contrato o null si no hay nominas
     * para el contrato.
     *
     * @return Payroll con la ultima fecha o null si no hay.
     */
    public Payroll getLastPayroll() {
	Payroll selectedPayroll = null;
	for (Payroll payroll : payrolls)
	    if (selectedPayroll == null || Dates.isAfter(
		    payroll.getDate(), selectedPayroll.getDate()))
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
	if (this.baseSalaryPerYear < 12000)
	    percent = 0;
	else if (this.baseSalaryPerYear < 30000)
	    percent = 0.1;
	else if (this.baseSalaryPerYear < 40000)
	    percent = 0.15;
	else if (this.baseSalaryPerYear < 50000)
	    percent = 0.20;
	else if (this.baseSalaryPerYear < 60000)
	    percent = 0.30;
	else
	    percent = 0.4;
	return percent;
    }

    /**
     * Metodo que calcula el numero de veces que un contrato ha cumplido las
     * condiciones necesarias para generar el extra de productividad, la condicion
     * es que durante el contrato el mecanico tenga una intervencion con una averia
     * cuyo estado sea ABIERTA.
     * 
     * @return Double con el numero de veces que cumple la condicion para el extra
     *         de productividad.
     */
    public double calculateProductivityTime() {
	return this.getMechanic().getIntervenciones().stream()
		.filter(inter -> Dates.isSameMonth(
			inter.getAveria().getFecha(), Dates.today())
			&& inter.getAveria().getStatus()
				.equals(AveriaStatus.ABIERTA))
		.mapToDouble(Intervencion::getImporte).sum();
    }
}
