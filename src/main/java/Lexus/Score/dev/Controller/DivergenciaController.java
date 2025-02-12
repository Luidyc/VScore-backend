package Lexus.Score.dev.Controller;

import Lexus.Score.dev.Dto.DivergenciaDto;
import Lexus.Score.dev.Entity.Divergencia.Divergencia;
import Lexus.Score.dev.Service.Divergencia.DivergenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Divergencia")
public class DivergenciaController {

    @Autowired
    DivergenciaService divergenciaService;

    @PostMapping
    ResponseEntity<DivergenciaDto> createDivergencia(@Valid @RequestBody DivergenciaDto request) {
        Optional<Divergencia> divergencia = divergenciaService.create(request);
        if (divergencia.isPresent()) {
            DivergenciaDto response = new DivergenciaDto(divergencia.get().getItemNota(),divergencia.get().getNotaFiscal(),
                    divergencia.get().getConferencia(), divergencia.get().getFoto(),divergencia.get().getDescricao());
            return ResponseEntity.ok().body(response);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    ResponseEntity<List<DivergenciaDto>> getAll() {
        return ResponseEntity.ok(divergenciaService.getAll());
    }
}
