package basis.bsb.sga.repositorios.repositorios;

import basis.bsb.sga.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}

