package uo.ri.persistence.jpa;

import uo.ri.business.repository.*;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public MecanicoRepository forMechanic() {
		return new MechanicJpaRepository();
	}

	@Override
	public AveriaRepository forAveria() {
		return new AveriaJpaRepository();
	}

	@Override
	public MedioPagoRepository forMedioPago() {
		return new MedioPagoJpaRepository();
	}

	@Override
	public FacturaRepository forFactura() {
		return new FacturaJpaRepository();
	}

	@Override
	public ClienteRepository forCliente() {
		return new ClienteJpaRepository();
	}

	@Override
	public RepuestoRepository forRepuesto() {
		return new RepuestoJpaRepository();
	}

	@Override
	public IntervencionRepository forIntervencion() {
		return new InterventionJpaRepository();
	}

	@Override
	public ContractCategoryRepository forContractCategory() {
		return new ContractCategoryJpaRepository();
	}

	@Override
	public ContractTypeRepository forContractType() {
		return new ContractTypeJpaRepository();
	}

	@Override
	public ContractRepository forContract() {
		return new ContractJpaRepository();
	}

	@Override
	public PayrollRepository forPayroll() {
		return new PayrollJpaRepository();
	}

	@Override
	public VehiculoReceptionRepository forVehiculoReception() {
		return new VehiculoReceptionJpaRepository();
	}

}
