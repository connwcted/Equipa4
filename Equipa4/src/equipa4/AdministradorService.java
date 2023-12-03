package equipa4;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AdministradorService {
	protected EntityManager em;

	public AdministradorService(EntityManager em) {
		this.em = em;
	}

	public Administrador updateAdministrador(int id, String nome, String email, String senha) {
		Administrador a = em.find(Administrador.class, id);
		if (a == null) {
			a = new Administrador();
			em.persist(a);
		}
		a.setId(id);
		a.setNome(nome);
		a.setEmail(email);
		a.setSenha(senha);
		em.persist(a);
		return a;
	}

	public Administrador findAdministrador(int id) {
		return em.find(Administrador.class, id);
	}

	public void removeAdministrador(int id) {
		Administrador r = findAdministrador(id);
		if (r != null)
			em.remove(r);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> findAllAdministradores() {
		Query qd = em.createQuery("Select a from Administrador a");
		return qd.getResultList();
	}
}

