package Lexus.Score.dev.Entity.Product;


import Lexus.Score.dev.Entity.Fiscal.ItemNota;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "produto")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @Column(nullable = false,unique = true)
    private Number codigo;
    @Column(nullable = false)
    private String nomeProduto;

    @OneToMany(mappedBy = "produto",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ItemNota> notaRecebida = new ArrayList<>();
}
