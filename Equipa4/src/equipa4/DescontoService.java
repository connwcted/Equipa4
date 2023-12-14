package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class DescontoService 
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
	
	public DescontoService() {
		this.em = getEM();
	}
	
	public DescontoService(EntityManager em) 
	{
		this.em = em;
	}
	public Desconto updateDesconto(float desconto) 
	{
		Desconto d = em.find(Desconto.class, desconto);
		if (d == null) 
		{
			d = new Desconto();
			em.persist(d);
		}
		d.setDesconto(desconto);
		em.persist(d);
		return d;
	}
	public Desconto findDesconto(float desconto) 
	{
		return em.find(Desconto.class, desconto);
	}
	public boolean removeDesconto(float desconto) 
	{
		Desconto r = findDesconto(desconto);
		if (r != null)
			em.remove(r);
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<Desconto> findAllDescontos() 
	{
		Query qd = em.createQuery("Select d from Desconto d");
		return qd.getResultList();
	}
}
