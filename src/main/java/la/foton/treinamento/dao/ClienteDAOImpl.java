package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insere(Cliente cliente) {
        entityManager.persist(cliente);
    }

    @Override
    public Optional<Cliente> consultaPorCPF(String cpf) {
        return Optional.ofNullable(entityManager.find(Cliente.class, cpf));
    }

    @Override
    public List<Cliente> listaTodos() {
        return entityManager.createQuery("SELECT C FROM Cliente C", Cliente.class).getResultList();
    }
}
