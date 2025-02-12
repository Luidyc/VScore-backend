package Lexus.Score.dev.Entity.Transporte;

import Lexus.Score.dev.Entity.Fiscal.Nota;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String motorista;

    @NotBlank
    @Column(nullable = false)
    private String placa;

    private String fotos;


    @JsonIgnore
    @OneToMany(mappedBy = "transporte",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notasFiscais = new ArrayList<>();
}
