package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class ProdutoService 
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
	
	public ProdutoService() {
		this.em = getEM();
	}
	
	public ProdutoService(EntityManager em) 
	{
		this.em = em;
	}
	
	public Produto updateProduto(int id, String nome, String descricao, String grupo, String ingredientes, int infNutricional, String alergenios, float preco) 
	{
		Produto p = em.find(Produto.class, id);
		if (p == null) 
		{
			p = new Produto();
			em.persist(p);
		}
		p.setId(id);
		p.setNome(nome);
		p.setDescricao(descricao);
		p.setGrupo(grupo);
		p.setIngredientes(ingredientes);
		p.setInfNutricional(infNutricional);
		p.setAlergenios(alergenios);
		p.setPreco(preco);
		em.persist(p);
		return p;
	}
	public boolean removeProduto(int id) 
	{
		Produto p = findProduto(id);
		if (p != null)
		{
			em.remove(p);
		}
		return false ;
	}
	public Produto findProduto(int id) 
	{
		return em.find(Produto.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Produto> findAllProduto() 
	{
		Query query = em.createQuery("SELECT p FROM Produto p");
		return query.getResultList();
	}
}