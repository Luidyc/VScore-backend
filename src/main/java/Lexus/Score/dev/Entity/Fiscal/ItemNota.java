package Lexus.Score.dev.Entity.Fiscal;


import Lexus.Score.dev.Entity.Product.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ItemNota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notaFiscal_id",nullable = false)
    @JsonIgnore
    private Nota notaFiscal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="produto_id", nullable = false)
    private ProductEntity produto;

    private int QntdDaNota;

    private int QntdConferida;
}
