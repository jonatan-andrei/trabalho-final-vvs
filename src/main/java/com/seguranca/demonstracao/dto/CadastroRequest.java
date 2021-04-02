package com.seguranca.demonstracao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadastroRequest {

    private String email;

    private String senha;
}
