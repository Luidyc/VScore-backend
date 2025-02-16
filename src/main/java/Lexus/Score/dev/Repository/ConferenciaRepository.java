package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import Lexus.Score.dev.Entity.Transporte.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ConferenciaRepository extends JpaRepository<Conferencia, Long> {
    @Query(value = "SELECT c FROM Conferencia c JOIN c.notasFiscais n WHERE n.id = :notaId")
    public Optional<Conferencia> findByNotaId(@Param("notaId")Long id);
}
