package Lexus.Score.dev.Service.Transporte;
import Lexus.Score.dev.Dto.TransporteDto;
import Lexus.Score.dev.Dto.TransporteDtoList;
import Lexus.Score.dev.Entity.Transporte.Transporte;
import Lexus.Score.dev.Repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    TransporteRepository transporteRepository;

    public Optional<Transporte> create(TransporteDto transporteRequest){
        Transporte transporte = new Transporte();
        transporte.setMotorista(transporteRequest.motorista());
        transporte.setPlaca(transporteRequest.placa());
        transporte.setFotos(transporteRequest.fotos());
        transporteRepository.save(transporte);
        return Optional.of(transporte);
    }

    public List<TransporteDtoList> getTheLast() {
        List<Transporte> transportes = transporteRepository.getTheLastList();
        List<TransporteDtoList> response = new ArrayList();
        for (Transporte transporte : transportes) {
            TransporteDtoList newTransporte = new TransporteDtoList(transporte.getId(),transporte.getPlaca(),transporte.getMotorista());
            response.add(newTransporte);
        }
        return response;
    }

    public List<TransporteDto> getAll() {
        List<Transporte> transportes = transporteRepository.findAll();
        List<TransporteDto> response = new ArrayList();
        for (Transporte transporte : transportes) {
            TransporteDto newTransporte = new TransporteDto(transporte.getMotorista(),transporte.getPlaca(),transporte.getFotos());
            response.add(newTransporte);
        }
        return response;
    }

    public Optional<TransporteDto> update(Long id, TransporteDto transporteRequest) {
        TransporteDto transporteResponse = null;
        if (id != null) {
            Optional<Transporte> transporteUpdated = transporteRepository.findById(id);
            // Primeiro busco pelo transporte caso o id enviado seja válido.
            // Se o transporte a ser atualizado for encontrado, atualizo o motorista e as fotos.
            if(transporteUpdated.isPresent()) {
                transporteUpdated.get().setMotorista(transporteRequest.motorista());
                transporteUpdated.get().setPlaca(transporteRequest.placa());
                transporteUpdated.get().setFotos(transporteRequest.fotos());
                transporteRepository.save(transporteUpdated.get());
                transporteResponse = new TransporteDto(
                        transporteUpdated.get().getMotorista(),
                        transporteUpdated.get().getPlaca(),
                        transporteUpdated.get().getFotos());
            }
        }
        return Optional.of(transporteResponse);
    }

    public boolean deleteById(Long id) {
        Optional<Transporte> request = transporteRepository.findById(id);
        if(request.isPresent()) {
            transporteRepository.deleteById(id);
            return true;
        }
        else { return false; }
    }
}