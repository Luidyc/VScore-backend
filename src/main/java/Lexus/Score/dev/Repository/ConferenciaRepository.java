package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenciaRepository extends JpaRepository<Conferencia, Long> {
}
