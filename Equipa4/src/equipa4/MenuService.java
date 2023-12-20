package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class MenuService 
{
	protected EntityManager em;
	
	private static final String PERSISTENCE_UNIT_NAME = "LibraryHamburger";
	private static EntityManagerFactory factory;
	private static EntityManager emanager = null;
	
	public static EntityManager getEM() 
	{
		if (emanager == null) 
		{
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			emanager = factory.createEntityManager();
		}
		return emanager;
	}
	
	private boolean saveData(Menu menu) {

        try {
            // Begin m new local transaction so that we can persist new entities
            em.getTransaction().begin();
            em.persist(menu);
            // Commit the transaction, which will cause the entity to
            // be stored in the database
            em.getTransaction().commit();
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
	
	public MenuService() {
		this.em = getEM();
	}
	
	public MenuService(EntityManager em) 
	{
		this.em = em;
	}
	
	public Menu updateMenu(Menu menu) {
		   EntityManager em = getEM();
		   
		   try {
			   Menu m = em.find(Menu.class, menu.getIdM());
			   
			   if(m == null) {
				   saveData(menu);
				   return menu;
			   }
			   m.setIdM(menu.getIdM());
			   m.setNomeM(menu.getNomeM());
			   m.setDescricaoM(menu.getDescricaoM());
			   m.setGrupoM(menu.getGrupoM());
			   m.setInfNM(menu.getInfNM());
			   m.setAlergenicosM(menu.getAlergenicosM());
			   m.setPrecoM(menu.getPrecoM());
			   
			   return m;
		   }
		   finally {
			   em.close();
		   }
	   }
	
	/**public Menu updateMenu(int idM, String nomeM, String descricaoM, String grupoM, int infNM, String alergenicosM, float precoM, List<Produto> produtos)
	{
		Menu m = em.find(Menu.class, idM);
		if (m == null) 
		{
			m = new Menu();
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
		saveData(m);
		return m;
	}*/
	public Menu updateMenu(int idM, String nomeM, String descricaoM, String grupoM, int infNM, String alergenicosM, float precoM)
	{
		Menu m = em.find(Menu.class, idM);
		if (m == null) 
		{
			m = new Menu();
		}
		m.setIdM(idM);
		m.setNomeM(nomeM);
		m.setDescricaoM(descricaoM);
		m.setGrupoM(grupoM);
		m.setInfNM(infNM);
		m.setAlergenicosM(alergenicosM);
		m.setPrecoM(precoM);
		m.getProdutos().clear();
		saveData(m);
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