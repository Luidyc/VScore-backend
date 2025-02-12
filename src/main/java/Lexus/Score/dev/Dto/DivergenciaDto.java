package Lexus.Score.dev.Dto;


import Lexus.Score.dev.Entity.Conferencia.Conferencia;
import Lexus.Score.dev.Entity.Fiscal.ItemNota;
import Lexus.Score.dev.Entity.Fiscal.Nota;

public record DivergenciaDto(ItemNota item, Nota notaFiscal, Conferencia conferencia,
                             String foto, String descricao) {
}
