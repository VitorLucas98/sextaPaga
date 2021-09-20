package basis.bsb.sga.repositorios;

import basis.bsb.sga.dominio.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoRepositorio extends JpaRepository<Motivo, Long> {
}
