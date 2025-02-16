package Lexus.Score.dev.Controller;

import Lexus.Score.dev.Dto.ConferenciaDto;
import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import Lexus.Score.dev.Service.Conferencia.ConferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Conferencia")
public class ConferenciaController {

    @Autowired
    ConferenciaService conferenciaService;

    @PostMapping()
    public ResponseEntity<Conferencia> create(@AuthenticationPrincipal UserDetails userDetails,
                                              @RequestBody ConferenciaDto request){
        String username = userDetails.getUsername();
        Optional<Conferencia> response = conferenciaService.create(username,request);
        if (response.isPresent()) return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<Conferencia> update(@AuthenticationPrincipal UserDetails userDetails,
                                              @RequestBody ConferenciaDto request){
        String username = userDetails.getUsername();
        Optional<Conferencia> response = conferenciaService.update(username,request);
        if (response.isPresent()) return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<List<Conferencia>> getAll() {
        return ResponseEntity.ok(conferenciaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conferencia> getById(@PathVariable() Long id) {
        Optional<Conferencia> response = conferenciaService.getById(id);
        if(response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
