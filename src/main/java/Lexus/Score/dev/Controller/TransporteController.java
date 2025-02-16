package Lexus.Score.dev.Controller;
import Lexus.Score.dev.Dto.TransporteDto;
import Lexus.Score.dev.Dto.TransporteDtoList;
import Lexus.Score.dev.Entity.Transporte.Transporte;
import Lexus.Score.dev.Service.Transporte.TransporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Transporte")
public class TransporteController {

    @Autowired
    TransporteService transporteService;

    @PostMapping()
    public ResponseEntity<TransporteDto> create(@RequestBody @Valid TransporteDto transporteRequest) {
        Optional<Transporte> transporte = transporteService.create(transporteRequest);
        if (transporte.isPresent()) {
            TransporteDto transporteResponse = new TransporteDto(
                    transporte.get().getMotorista(),
                    transporte.get().getPlaca(),
                    transporte.get().getFotos()
            );
            return ResponseEntity.ok().body(transporteResponse);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<TransporteDto>> getAll() {
       return ResponseEntity.ok(transporteService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransporteDto> update(@Valid @PathVariable("id") Long id,
                                                @RequestBody TransporteDto transporteRequest) {
        Optional<TransporteDto> transporteUpdated = transporteService.update(id,transporteRequest);
        if(transporteUpdated.isPresent()) { return ResponseEntity.ok().body(transporteUpdated.get()); }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/last")
    public ResponseEntity<List<TransporteDtoList>> getTheLast() {
        return ResponseEntity.ok(transporteService.getTheLast());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransporteDto> deleteById(@Valid @PathVariable("id") Long id) {
        boolean response = transporteService.deleteById(id);
        if(response != true) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {return ResponseEntity.ok().build();}
    }


}
