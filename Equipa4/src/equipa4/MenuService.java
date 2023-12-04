package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class MenuService 
{
	protected EntityManager em;
	public MenuService(EntityManager em) 
	{
		this.em = em;
	}
	public Menu updateMenu(int idM, String nomeM, String descricaoM, String grupoM, int infNM, String alergenicosM, float precoM)
	{
		Menu m = em.find(Menu.class, idM);
		if (m == null) 
		{
			m = new Menu();
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
	public Menu findMenu(int idM) 
	{
		return em.find(Menu.class, idM);
	}
	public boolean removeMenu(int idM) 
	{
		Menu m = findMenu(idM);
		if (m != null)
		{
			em.remove(m);
		}
		return false ;
	}
	@SuppressWarnings("unchecked")
	public List<Menu> findAllMenus() 
	{
		Query qd = em.createQuery("Select m from Menu m");
		return qd.getResultList();
	}
}
