package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ws.rs.core.Response;

public abstract class Conta {

    private int agencia;
    private int numero;
    protected double saldo;
    private Cliente titular;
    private EstadoDaConta estado;
    protected TipoDaConta tipo;

    public Conta() {
        estado = EstadoDaConta.ATIVA;
        saldo = 0;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public EstadoDaConta getEstado() {
        return estado;
    }

    public TipoDaConta getTipo() {
        return tipo;
    }

    public void credita(double valor) {
        saldo += valor;
    }

    public abstract void debita(double valor);

    public void transfere(Conta contaCredita, double valor) {
        this.debita(valor);
        contaCredita.credita(valor);
    }

    public void encerra() {
        if (estado == EstadoDaConta.ENCERRADA) {
            throw new NegocioException(Mensagem.CONTA_JA_ENCERRADA, Response.Status.BAD_REQUEST);
        }
        if (saldo > 0) {
            throw new NegocioException(Mensagem.CONTA_POSSUI_SALDO, Response.Status.BAD_REQUEST);
        }
        estado = EstadoDaConta.ENCERRADA;
    }
}
