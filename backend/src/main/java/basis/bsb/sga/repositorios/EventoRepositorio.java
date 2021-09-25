package basis.bsb.sga.repositorios;

import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long> , JpaSpecificationExecutor<Evento> {
    boolean existsByDataEvento(LocalDate data);

    @Query("SELECT evento FROM Evento evento WHERE evento.dataEvento >=:data ORDER BY evento.dataEvento ASC")
    List<Evento> findByDateAlter(LocalDate data);

    Evento findByDataEvento(LocalDate data);

    List<Evento> getAllByUsuarios (Usuario usuario);
}
