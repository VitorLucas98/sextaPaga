package basis.bsb.sga.repositorios;

import basis.bsb.sga.dominio.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long> {
    boolean existsByDataEvento(LocalDate data);
}
