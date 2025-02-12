package Lexus.Score.dev.Entity.Divergencia;

import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import Lexus.Score.dev.Entity.Fiscal.ItemNota;
import Lexus.Score.dev.Entity.Fiscal.Nota;
import Lexus.Score.dev.Entity.Product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Divergencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "itemNota_id",nullable = false)
    private ItemNota itemNota;

    @ManyToOne
    @JoinColumn(name = "notaFiscal_id",nullable = false)
    private Nota notaFiscal;

    @ManyToOne
    @JoinColumn(name = "conferencia_id",nullable = false)
    private Conferencia conferencia;

    private String foto;

    private String descricao;
}
