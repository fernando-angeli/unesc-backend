package com.fernando.biblioteca.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private String titulo;
    private Integer status;
    private String mensagem;

}
