package la.foton.treinamento.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NegocioException extends WebApplicationException {

    private final Mensagem mensagem;

    public NegocioException(Mensagem mensagem, Response.Status status) {
        super(mensagem.getDescricao(), Response.status(status).entity(mensagem.getDescricao()).build());
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
