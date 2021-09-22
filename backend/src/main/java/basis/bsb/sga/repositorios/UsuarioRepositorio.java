package basis.bsb.sga.repositorios;

import basis.bsb.sga.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}

