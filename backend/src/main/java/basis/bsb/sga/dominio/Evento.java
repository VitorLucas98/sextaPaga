package basis.bsb.sga.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_evento")
    private LocalDate dataEvento;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_motivo")
    private Motivo motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "usuario_evento",
            joinColumns = {
                    @JoinColumn(name = "id_evento")
            }, inverseJoinColumns = {
                    @JoinColumn(name = "id_usuario")
    })
    private List<Usuario> usuarios;
}