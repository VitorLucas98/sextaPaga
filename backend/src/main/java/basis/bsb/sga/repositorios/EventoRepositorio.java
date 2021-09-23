package basis.bsb.sga.repositorios;

import basis.bsb.sga.dominio.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long> , JpaSpecificationExecutor<Evento> {
    boolean existsByDataEvento(LocalDate data);

    @Query("SELECT obj FROM Evento obj ORDER BY obj.dataEvento")
    List<Evento> findAllOrderData();
}
