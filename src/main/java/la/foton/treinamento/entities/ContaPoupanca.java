package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ws.rs.core.Response;

public class ContaPoupanca extends Conta {

    private int diaAniversario;

    public ContaPoupanca() {
        tipo = TipoDaConta.POUPANCA;
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(int diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    @Override
    public void debita(double valor) {
        if (valor > saldo)
            throw new NegocioException(Mensagem.SALDO_INSUFICIENTE, Response.Status.BAD_REQUEST);
        saldo -= valor;
    }
}
