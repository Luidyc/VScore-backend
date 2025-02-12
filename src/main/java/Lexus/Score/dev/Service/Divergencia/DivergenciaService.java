package Lexus.Score.dev.Service.Divergencia;

import Lexus.Score.dev.Dto.DivergenciaDto;
import Lexus.Score.dev.Entity.Divergencia.Divergencia;
import Lexus.Score.dev.Repository.DivergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DivergenciaService {

    @Autowired
    DivergenciaRepository divergenciaRepository;

    public Optional<Divergencia> create(DivergenciaDto divergenciaRequest) {
        Divergencia divergencia = new Divergencia();
        if (divergenciaRequest != null) {
            divergencia.setNotaFiscal(divergenciaRequest.notaFiscal());
            divergencia.setItemNota(divergenciaRequest.item());
            divergencia.setConferencia(divergenciaRequest.conferencia());
            divergencia.setFoto(divergenciaRequest.foto());
            divergencia.setDescricao(divergenciaRequest.descricao());
            divergenciaRepository.save(divergencia);
        }
        return Optional.of(divergencia);
    }

    public List<DivergenciaDto> getAll() {
        List<Divergencia> divergencias = divergenciaRepository.findAll();
        List<DivergenciaDto> response = new ArrayList();
        for (Divergencia divergencia : divergencias) {
            DivergenciaDto newDivergencia = new DivergenciaDto(divergencia.getItemNota(),divergencia.getNotaFiscal(),
                    divergencia.getConferencia(),divergencia.getFoto(),divergencia.getDescricao());
            response.add(newDivergencia);
        }
        return response;
    }
}
