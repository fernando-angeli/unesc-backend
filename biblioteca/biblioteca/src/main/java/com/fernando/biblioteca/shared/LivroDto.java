package com.fernando.biblioteca.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {

    private Integer id;

    private String titulo;

    private Integer isbn;

    private String resenha;

    private Integer numeroPaginas;

}
