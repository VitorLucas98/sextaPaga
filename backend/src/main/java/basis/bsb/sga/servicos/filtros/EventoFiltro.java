package basis.bsb.sga.servicos.filtros;


import basis.bsb.sga.dominio.Evento;
import basis.bsb.sga.dominio.Evento_;
import basis.bsb.sga.dominio.Motivo_;
import basis.bsb.sga.dominio.Situacao_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventoFiltro implements EntityFiltro<Evento>{

    private Long id;
    private String nome;
    private LocalDate dataEventoInicial;
    private LocalDate dataEventoFinal;
    private Double valor;
    private String motivo;
    private String situacao;

    @Override
    public Specification<Evento> filter() {
        return (root, cq, cb) ->
                cb.and(getPredicates(root,cq,cb)
                        .toArray(new Predicate[0]));
    }

    private List<Predicate> getPredicates(Root<Evento> root, CriteriaQuery<?> cq, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();
        cq.orderBy(cb.asc(root.get(Evento_.DATA_EVENTO)));

        if (id != null){
            predicates.add(cb.equal(root.get(Evento_.id), id));
        }

        if (nome != null){
            predicates.add(cb.like(root.get(Evento_.nome), "%"+nome+"%"));
        }

        if (valor != null){
            predicates.add(cb.equal(root.get(Evento_.VALOR), valor));

        }
        if (motivo != null){
            predicates.add(cb.like(root.join("motivo").get(Motivo_.TITULO), "%"+motivo+"%"));
        }

        if (situacao != null){
            predicates.add(cb.like(root.join("situacao").get(Situacao_.DESCRICAO), "%"+situacao+"%"));
        }

        return predicates;

    }
}
