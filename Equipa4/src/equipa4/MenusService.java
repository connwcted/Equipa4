package equipa4;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MenusService {
	protected EntityManager em;

	public MenusService(EntityManager em) {
		this.em = em;
	}

	public Menus updateMenus(int idM, String nomeM, String descricaoM, String grupoM, int infNM, String alergenicosM,
			float precoM, List<Produto> produtos) {
		Menus m = em.find(Menus.class, idM);
		if (m == null) {
			m = new Menus();
			em.persist(m);
		}
		m.setIdM(idM);
		m.setNomeM(nomeM);
		m.setDescricaoM(descricaoM);
		m.setGrupoM(grupoM);
		m.setInfNM(infNM);
		m.setAlergenicosM(alergenicosM);
		m.setPrecoM(precoM);
		m.getProdutos().clear();
		m.getProdutos().addAll(produtos);
		return m;
	}
	
	public Menus updateMenus(int idM, String nomeM, String descricaoM, String grupoM, int infNM, String alergenicosM,
			float precoM) {
		Menus m = em.find(Menus.class, idM);
		if (m == null) {
			m = new Menus();
			em.persist(m);
		}
		m.setIdM(idM);
		m.setNomeM(nomeM);
		m.setDescricaoM(descricaoM);
		m.setGrupoM(grupoM);
		m.setInfNM(infNM);
		m.setAlergenicosM(alergenicosM);
		m.setPrecoM(precoM);
		m.getProdutos().clear();
		return m;
	}

	public Menus findMenus(int idM) {
		return em.find(Menus.class, idM);
	}

	public void removeMenus(int idM) {
		Menus m = findMenus(idM);
		if (m != null)
			em.remove(m);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Menus> findAllMenus() {
		Query qd = em.createQuery("Select m from Menus m");
		return qd.getResultList();
	}
}
