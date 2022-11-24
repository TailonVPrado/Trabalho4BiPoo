package br.unipar.flashcar.model;

import lombok.Data;

@Data
public class Modelo {
    private int id;
    private String descricao;
    private Marca marca;
}
