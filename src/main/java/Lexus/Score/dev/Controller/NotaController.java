package Lexus.Score.dev.Controller;

import Lexus.Score.dev.Entity.Fiscal.Nota;
import Lexus.Score.dev.Service.Fiscal.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/NotaFiscal")
public class NotaController {


    @Autowired
    private NotaService notaService;


    @PostMapping()
    public ResponseEntity<Nota> createNota(@RequestBody @Valid Nota nota){
        Optional<Nota> response = notaService.create(nota);
        if(response.isPresent()) return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<List<Nota>> getAllNotas(){
        return ResponseEntity.ok(notaService.getAll());
    }

    @GetMapping("/")
    public ResponseEntity<Nota> getById(@RequestBody Nota nota){
        Optional<Nota> response = notaService.getById(nota.getId());
        if(response.isEmpty()) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        else { return ResponseEntity.ok(response.get());}
    }
}
