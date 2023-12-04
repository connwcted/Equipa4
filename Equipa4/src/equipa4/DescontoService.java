package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class DescontoService 
{
	protected EntityManager em;
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
