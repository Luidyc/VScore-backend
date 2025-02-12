package Lexus.Score.dev.Entity.Conferencia;

import Lexus.Score.dev.Entity.Divergencia.Divergencia;
import Lexus.Score.dev.Entity.Fiscal.Nota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List; 

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataConfencia;

    private String conferente;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "conferencia_id")
    private List<Nota> notasFiscais = new ArrayList<>();

    @OneToMany(mappedBy = "conferencia",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Divergencia> divergencias = new ArrayList<>();
}
