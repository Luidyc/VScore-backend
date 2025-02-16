package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Dto.TransporteDtoList;
import Lexus.Score.dev.Entity.Transporte.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface TransporteRepository extends JpaRepository<Transporte, Long> {
    @Query(value = "SELECT Transporte.id,Transporte.motorista, Transporte.placa, Transporte.fotos FROM Transporte ORDER BY ID DESC limit 10",
            nativeQuery = true)
    public List<Transporte> getTheLastList();
}
