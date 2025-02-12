package Lexus.Score.dev.Service.Product;

import Lexus.Score.dev.Repository.ProductRepository;
import Lexus.Score.dev.Dto.ProductDto;
import Lexus.Score.dev.Entity.Product.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;



    public Optional<ProductDto> create(ProductDto productRequest) {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setNomeProduto(productRequest.nomeProduto());
        newProduct.setCodigo(productRequest.codigo());
        productRepository.save(newProduct);
        Optional<ProductDto> response = Optional.of(productRequest);
        return response;
    }

    public Optional<ProductDto>update(ProductDto productRequest) {
        Optional<ProductEntity> product = productRepository.findById(productRequest.codigo());
        if(product.isPresent()) {
            product.get().setNomeProduto(productRequest.nomeProduto());
            productRepository.save(product.get());
            ProductDto response = new ProductDto(product.get().getCodigo(),product.get().getNomeProduto());
            return Optional.of(response);
        }
        return Optional.empty();
    }
/*
    Função que substitui um Mapper, refatorada apartir da analise do funcionamento do mesmo.
    Obviamente, com BodyPlate maior.
*/
    // Função para retornar uma lista contendo todas entidades no repositório.
    public List<ProductDto> getAll() {
        // Trazendo a lista do BD.
        List<ProductEntity> products = productRepository.findAll();
        //Inicializando o Array de retorno, pois não vou devolver minha entidade mas um DTO.
        List<ProductDto> responses = new ArrayList<>();
        //Crie um Objeto da entidade para cada Produto da lista.
        for(ProductEntity product : products) {
            // Para cada produto, eu crio a resposta que é o produto sendo passado para um DTO.
            ProductDto response = new ProductDto(product.getCodigo(),product.getNomeProduto());
            // Adicionando ele a lista de retorno.
            responses.add(response);
        }
        return responses;
    }
}
