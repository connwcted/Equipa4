package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class EncomendaService
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
	
	public EncomendaService() {
		this.em = getEM();
	}
	
	public EncomendaService(EntityManager em) 
	{
		this.em = em;
	}
	public Encomenda updateEncomenda(int numEnc, float precoEnc)
	{
		Encomenda e = em.find(Encomenda.class, numEnc);
		if (e == null) 
		{
			e = new Encomenda();
			em.persist(e);
		}
		e.setNumEnc(numEnc);
		e.setPrecoEnc(precoEnc);
		e.getProdutos().clear();
		return e;
	}
	public Encomenda findEncomenda(int numEnc) 
	{
		return em.find(Encomenda.class, numEnc);
	}
	public boolean removeEncomenda(int numEnc) 
	{
		Encomenda m = findEncomenda(numEnc);
		if (m != null)
		{
			em.remove(m);
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<Encomenda> findAllEncomendas() 
	{
		Query qd = em.createQuery("Select e from Encomenda e");
		return qd.getResultList();
	}
}
