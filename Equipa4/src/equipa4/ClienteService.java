package equipa4;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteService {
	protected EntityManager em;

	public ClienteService(EntityManager em) {
		this.em = em;
	}

	public Cliente updateCliente(int id, String nome, String email, String senha) {
		Cliente c = em.find(Cliente.class, id);
		if (c == null) {
			c = new Cliente();
			em.persist(c);
		}
		c.setId(id);
		c.setNome(nome);
		c.setEmail(email);
		c.setSenha(senha);
		em.persist(c);
		return c;
	}

	public Cliente findCliente(int id) {
		return em.find(Cliente.class, id);
	}

	public void removeCliente(int id) {
		Cliente r = findCliente(id);
		if (r != null)
			em.remove(r);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientes() {
		Query qd = em.createQuery("Select c from Cliente c");
		return qd.getResultList();
	}
}
