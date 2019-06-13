package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ws.rs.core.Response;

public class ContaCorrente extends Conta {

    private double limiteChequeEspecial;

    public ContaCorrente() {
        tipo = TipoDaConta.CORRENTE;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void debita(double valor) {
        if (valor > saldo + limiteChequeEspecial)
            throw new NegocioException(Mensagem.SALDO_INSUFICIENTE, Response.Status.BAD_REQUEST);
        saldo -= valor;
    }


}
