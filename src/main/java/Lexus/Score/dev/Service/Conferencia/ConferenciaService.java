package Lexus.Score.dev.Service.Conferencia;

import Lexus.Score.dev.Dto.ConferenciaDto;
import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import Lexus.Score.dev.Entity.Fiscal.Nota;
import Lexus.Score.dev.Repository.ConferenciaRepository;
import Lexus.Score.dev.Repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenciaService {

    @Autowired
    ConferenciaRepository conferenciaRepository;

    @Autowired
    NotaRepository notaRepository;

    public Optional<Conferencia> create(String username,ConferenciaDto conferenciaDto) {
       if(conferenciaDto!=null) {
           Conferencia newConferencia = new Conferencia();
           newConferencia.setDataConfencia(LocalDateTime.now());
           newConferencia.setConferente(username);
           // Eu vou procurar pelo ID para cada nota recebida
           for(Nota nota : conferenciaDto.notasFiscais()) {
               Long id = nota.getId();
               // Meu retorno do repository é opicional, pode ou não encontrar
               Optional<Nota> searchNota = notaRepository.findById(id);
               // Se encontrou uma nota
               if (searchNota.isPresent()) {
                   // Minha conferência vai adicionar a nota encontrada.
                   newConferencia.getNotasFiscais().add(searchNota.get()); }
           }
           conferenciaRepository.save(newConferencia);
           return Optional.of(newConferencia);
       }
       return Optional.empty();
    }

    public List<Conferencia> getAll() {
        return conferenciaRepository.findAll();
    }

    public Optional<Conferencia> getById(Long id) {
        if(id!= null) {
            Optional<Conferencia> response = conferenciaRepository.findById(id);
            return Optional.of(response.get());
        }
        return Optional.empty();
    }

}
