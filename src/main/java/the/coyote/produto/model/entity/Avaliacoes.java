package the.coyote.produto.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
public class Avaliacoes {

    private String usuario;
    private String comentario;
    private int nota;

    public Avaliacoes(String usuario, String comentario, int nota) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.nota = nota;
    }

}
