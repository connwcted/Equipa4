package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class EncomendaService
{
	protected EntityManager em;
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
	public void removeEncomenda(int numEnc) 
	{
		Encomenda m = findEncomenda(numEnc);
		if (m != null)
		{
			em.remove(m);
		}
		return;
	}
	@SuppressWarnings("unchecked")
	public List<Encomenda> findAllEncomendas() 
	{
		Query qd = em.createQuery("Select e from Encomenda e");
		return qd.getResultList();
	}
}
