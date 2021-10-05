package basis.bsb.sga.servicos.filtros;


import org.springframework.data.jpa.domain.Specification;

public interface EntityFiltro <T>{
    Specification<T> filter();
}

