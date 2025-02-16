package Lexus.Score.dev.Entity.Fiscal;


import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import Lexus.Score.dev.Entity.Transporte.Transporte;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NotaFiscal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transporte_id")
    private Transporte transporte;

    @Column(unique = true,nullable = false)
    private String NumeroNota;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNota> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "conferencia_id")
    @JsonIgnore
    private Conferencia conferencia;

    public void addItem(ItemNota item) {
        this.itens.add(item);
    }

}
