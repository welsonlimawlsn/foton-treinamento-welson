package la.foton.treinamento.services;

import la.foton.treinamento.dao.ClienteDAO;
import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.entities.SituacaoDoCliente;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class ClienteService {

    @Inject
    private ClienteDAO dao;

    public void validaSituacaoCliente(Cliente cliente) {
        if (cliente.getSituacao() == SituacaoDoCliente.PENDENTE) {
            throw new NegocioException(Mensagem.CLIENTE_SITUACAO_PENDENTE, Response.Status.BAD_REQUEST);
        }
    }

    public Cliente consultaPorCPF(String cpf) {
        return dao.consultaPorCPF(cpf).orElseThrow(() -> new NegocioException(Mensagem.CLIENTE_NAO_ENCONTRADO, Response.Status.NOT_FOUND));
    }

    public List<Cliente> listaTodos() {
        return dao.listaTodos();
    }

    public void cadastraCliente(Cliente cliente) {
        boolean clienteEhInvalido = cliente.getNome() == null || cliente.getNome().isEmpty() || cliente.getCpf() == null || cliente.getCpf().isEmpty();
        if (clienteEhInvalido) {
            throw new NegocioException(Mensagem.CLIENTE_NAO_PODE_SER_CADASTRADO, Response.Status.BAD_REQUEST);
        }
        dao.insere(cliente);
    }
}
