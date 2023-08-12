package taskify.taskify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {

    private long idUsuario;
    private String cep;
    private String numero;
}
