package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Entity.Fiscal.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long> {
}
