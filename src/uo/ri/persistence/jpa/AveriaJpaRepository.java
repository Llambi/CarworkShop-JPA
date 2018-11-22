package uo.ri.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.business.repository.AveriaRepository;
import uo.ri.model.Averia;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class AveriaJpaRepository extends BaseRepository<Averia>
	implements AveriaRepository {

    @Override
    public List<Averia> findByIds(List<Long> idsAveria) {
	return Jpa.getManager()
		.createNamedQuery("Averia.findByIds", Averia.class)
		.getResultStream().collect(Collectors.toList());
    }

    @Override
    public List<Averia> findNoFacturadasByDni(String dni) {
	return Jpa.getManager()
		.createNamedQuery("Averia.findNoFacturadasByDni",
			Averia.class)
		.getResultStream().collect(Collectors.toList());
    }

}
