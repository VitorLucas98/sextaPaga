package basis.bsb.sga.repositorios.repositorios;

import basis.bsb.sga.dominio.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long> {

}
