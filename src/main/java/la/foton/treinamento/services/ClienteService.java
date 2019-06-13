package la.foton.treinamento.services;

import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.entities.SituacaoDoCliente;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ws.rs.core.Response;

public class ClienteService {

    private static ClienteService clienteService;

    public static ClienteService getInstance() {
        if (clienteService == null) {
            clienteService = new ClienteService();
        }
        return clienteService;
    }

    public void validaSituacaoCliente(Cliente cliente) {
        if (cliente.getSituacao() == SituacaoDoCliente.PENDENTE) {
            throw new NegocioException(Mensagem.CLIENTE_SITUACAO_PENDENTE, Response.Status.BAD_REQUEST);
        }
    }
}
