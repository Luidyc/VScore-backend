package Lexus.Score.dev.Service.Fiscal;

import Lexus.Score.dev.Entity.Fiscal.ItemNota;
import Lexus.Score.dev.Entity.Fiscal.Nota;
import Lexus.Score.dev.Entity.Product.ProductEntity;
import Lexus.Score.dev.Repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    NotaRepository notaRepository;


    //Essa função é para criar manualmente.
    public Optional<Nota> create(Nota notaFiscal) {
        // Para criar uma nota eu recebo o Numero da mesma e lista de itens contidos;
        Nota nota = new Nota();
        // Defino o numero como oque foi enviado
        nota.setNumeroNota(notaFiscal.getNumeroNota());
        nota.setTransporte(notaFiscal.getTransporte());
        List<ItemNota> itens = new ArrayList<>();
        // Laço para percorrer toda lista de itens, pego 1 item e vou adicionando a nota
        // pois a lista foi iniciada vazia na entidade;
        for (ItemNota item : notaFiscal.getItens() ) {
            ProductEntity produto = item.getProduto();
            produto.getNotaRecebida().add(item);
            item.setProduto(produto);
            item.setNotaFiscal(nota);
            itens.add(item);
            //1. Defino a nota que veio
            //2. Adiciono a relação do produto com a nota
            //3. Adiciono o item a Lista de itens da nota
            System.out.println(notaFiscal.toString());
        }
        nota.setItens(itens);
        notaRepository.save(nota);
        return Optional.of(nota);
    }

    public List<Nota> getAll() {
       return notaRepository.findAll();
    }

    public Optional<Nota> getById(Long id) {
        if(id != null) {
            Optional<Nota> response = notaRepository.findById(id);
            return Optional.of(response.get());
        }
        return Optional.empty();
    }


}
