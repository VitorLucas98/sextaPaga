package basis.bsb.sga.builder;

import basis.bsb.sga.dominio.Cargo;
import basis.bsb.sga.dominio.Usuario;
import basis.bsb.sga.repositorios.UsuarioRepositorio;
import basis.bsb.sga.servicos.dtos.UsuarioDTO;
import basis.bsb.sga.servicos.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class UsuarioBuilder extends ConstrutorDeEntidade<Usuario> {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    protected Usuario construirEntidade() throws ParseException {
        Usuario usuario = new Usuario();
        usuario.setCpf("99672324049");
        usuario.setEmail("alanT@gmail.com");
        usuario.setNome("Alan Turing");
        usuario.setStatus(true);
        usuario.setTelefone("09982730294");
        usuario.setDataNascimento(LocalDate.now().minusYears(23));
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        usuario.setCargo(cargo);
        return usuario;
    }

    @Override
        protected Usuario persistir(Usuario entidade) {
        return usuarioRepositorio.save(entidade);
    }

    @Override
    protected Collection<Usuario> obterTodos() {
        return usuarioRepositorio.findAll();
    }

    @Override
    protected Usuario obterPorId(Long id) {
        return usuarioRepositorio.findById(id).get();
    }

    @Override
    public Usuario construir() throws ParseException {
        return super.construir();
    }

    public UsuarioDTO construirDTO() throws ParseException {
        return usuarioMapper.toDto(construir());
    }

    public UsuarioDTO construirObjDTO() throws ParseException{
        return usuarioMapper.toDto(construirEntidade());
    }

    public void delete() {
        usuarioRepositorio.deleteAll();
    }

}
