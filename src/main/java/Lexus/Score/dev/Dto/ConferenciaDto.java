package Lexus.Score.dev.Dto;

import Lexus.Score.dev.Entity.Fiscal.Nota;

import java.util.List;

public record ConferenciaDto(Long id, List<Nota>notasFiscais) {
}
