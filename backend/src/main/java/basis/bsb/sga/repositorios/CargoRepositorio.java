package basis.bsb.sga.repositorios;

import basis.bsb.sga.dominio.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepositorio extends JpaRepository<Cargo, Long> {
}