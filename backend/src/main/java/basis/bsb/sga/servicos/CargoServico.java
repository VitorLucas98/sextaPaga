package basis.bsb.sga.servicos;

import basis.bsb.sga.dominio.Cargo;
import basis.bsb.sga.repositorios.CargoRepositorio;
import basis.bsb.sga.servicos.dtos.CargoDTO;
import basis.bsb.sga.servicos.dtos.MotivoDTO;
import basis.bsb.sga.servicos.dtos.SelectDTO;
import basis.bsb.sga.servicos.excecoes.ObjetoNaoEncontrado;
import basis.bsb.sga.servicos.mappers.CargoMapper;
import basis.bsb.sga.servicos.mappers.CargoSelectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CargoServico {

    private final CargoRepositorio repositorio;

    private final CargoSelectMapper mapper;

    public List<SelectDTO> listarTodos(){
        return mapper.toDto(repositorio.findAll());
    }


}

