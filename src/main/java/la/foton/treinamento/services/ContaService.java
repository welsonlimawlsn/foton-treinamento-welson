package la.foton.treinamento.services;

import la.foton.treinamento.dao.ContaDAO;
import la.foton.treinamento.entities.*;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class ContaService {

    @Inject
    private ClienteService clienteService;
    @Inject
    private ContaDAO contaDAO;

    public Conta abreConta(Cliente cliente, TipoDaConta tipo) {
        cliente.ativa();
        clienteService.validaSituacaoCliente(cliente);
        Conta conta = criarConta(tipo);
        conta.setTitular(cliente);

        conta.setNumero(contaDAO.geraNumero());
        conta.setAgencia(1234);

        contaDAO.insere(conta);

        return conta;
    }

    public void encerraConta(Conta conta) {
        conta.encerra();
        contaDAO.atualiza(conta);
    }

    public void sacaEmConta(Conta conta, double valor) {
        conta.debita(valor);
        contaDAO.atualiza(conta);
    }

    public void depositaEmConta(int numeroConta, double valor) {
        Conta conta = contaDAO.consultaPorNumero(numeroConta);
        conta.credita(valor);
        contaDAO.atualiza(conta);
    }

    public void transfereEntreContas(Conta origem, Conta destino, double valor) {
        origem.transfere(destino, valor);
        contaDAO.atualiza(origem);
        contaDAO.atualiza(destino);
    }

    public Conta consultaPorNumero(int numero) {
        Conta conta = contaDAO.consultaPorNumero(numero);
        if (conta == null)
            throw new NegocioException(Mensagem.CONTA_NAO_ENCONTRADA, Response.Status.NOT_FOUND);
        return conta;
    }

    private Conta criarConta(TipoDaConta tipo) {
        Conta conta = null;

        if (tipo == TipoDaConta.CORRENTE) {
            conta = new ContaCorrente();
            ((ContaCorrente) conta).setLimiteChequeEspecial(500);
        } else if (tipo == TipoDaConta.POUPANCA) {
            conta = new ContaPoupanca();
            ((ContaPoupanca) conta).setDiaAniversario(1);
        }

        return conta;
    }
}
