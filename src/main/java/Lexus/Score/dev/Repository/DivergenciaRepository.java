package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Entity.Divergencia.Divergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivergenciaRepository extends JpaRepository<Divergencia,Long> {
}
