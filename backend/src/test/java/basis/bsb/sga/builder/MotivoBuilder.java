package basis.bsb.sga.builder;

import basis.bsb.sga.dominio.Motivo;
import basis.bsb.sga.repositorios.MotivoRepositorio;
import basis.bsb.sga.servicos.dtos.MotivoDTO;
import basis.bsb.sga.servicos.dtos.UsuarioDTO;
import basis.bsb.sga.servicos.mappers.MotivoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class MotivoBuilder extends ConstrutorDeEntidade<Motivo> {

    @Autowired
    private MotivoRepositorio repositorio;

    @Autowired
    private MotivoMapper mapper;

    @Override
    protected Motivo construirEntidade() throws ParseException {
        Motivo motivo = new Motivo();
        motivo.setTitulo("Anivesario");
        motivo.setDescricao("Anivesario de 25 anos da Basis");
        return motivo;
    }

    @Override
    protected Motivo persistir(Motivo entidade) {
        return repositorio.save(entidade);
    }

    @Override
    protected Collection<Motivo> obterTodos() {
        return repositorio.findAll();
    }

    @Override
    protected Motivo obterPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Motivo construir() throws ParseException {
        return super.construir();
    }

    public MotivoDTO construirDTO() throws ParseException {
        return mapper.toDto(construir());
    }

    public MotivoDTO construirObjDTO() throws ParseException{
        return mapper.toDto(construirEntidade());
    }

    public void delete() {
        repositorio.deleteAll();
    }

}
