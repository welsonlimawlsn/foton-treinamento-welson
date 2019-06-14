package la.foton.treinamento.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NegocioException extends WebApplicationException {

    private final Mensagem mensagem;

    public NegocioException(Mensagem mensagem, Response.Status status) {
        super(mensagem.getDescricao(), Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).entity("{\"erro\":\"" + mensagem.getDescricao() + "\"}").build());
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public static NegocioException build(Mensagem mensagem, Response.Status status) {
        return new NegocioException(mensagem, status);
    }
}
