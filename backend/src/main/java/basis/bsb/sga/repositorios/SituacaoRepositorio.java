package basis.bsb.sga.repositorios;


import basis.bsb.sga.dominio.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepositorio extends JpaRepository<Situacao, Long> {
}
