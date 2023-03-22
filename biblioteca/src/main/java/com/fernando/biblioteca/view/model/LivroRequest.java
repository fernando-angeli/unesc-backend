package com.fernando.biblioteca.view.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequest {

    private String titulo;

    private Integer isbn;

    private String resenha;

    private Integer numeroPaginas;
}
