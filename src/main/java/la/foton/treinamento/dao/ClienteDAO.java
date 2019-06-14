package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {

    void insere(Cliente cliente);

    Optional<Cliente> consultaPorCPF(String cpf);

    List<Cliente> listaTodos();
}
