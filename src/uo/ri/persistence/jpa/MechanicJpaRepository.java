package uo.ri.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.business.repository.MecanicoRepository;
import uo.ri.model.Mecanico;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class MechanicJpaRepository extends BaseRepository<Mecanico>
	implements MecanicoRepository {

    @Override
    public Mecanico findByDni(String dni) {
	return Jpa.getManager()
		.createNamedQuery("Mecanico.findByDni",
			Mecanico.class)
		.setParameter(1, dni).getResultStream().findFirst()
		.orElse(null);
    }

    @Override
    public List<Mecanico> findActive() {
	return Jpa.getManager()
		.createNamedQuery("Mecanico.findActive",
			Mecanico.class)
		.getResultStream().collect(Collectors.toList());
    }

}
