package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class ClienteService 
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
	
	private boolean saveData(Cliente cliente) {

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
	
	public ClienteService() {
		this.em= getEM();
	}
	
	public ClienteService(EntityManager em) 
	{
		this.em = em;
	}
	
	public Cliente addCliente(int id, String nome, String email, String senha) {
		   EntityManager em = getEM();
		   
		   try {
			   Cliente c = em.find(Cliente.class, id);
			   if(c == null) {
				   c = new Cliente();
				   c.setId(id);
			   }
			   
			   c.setNome(nome);
			   c.setEmail(email);
			   c.setSenha(senha);
			   
			   saveData(c);
			   return c;
		   }
		   finally{
			   em.close();  
		   }
	   }
	
	public Cliente updateCliente(Cliente cliente) {
		   EntityManager em = getEM();
		   
		   try {
			   Cliente c = em.find(Cliente.class, cliente.getId());
			   
			   if(c == null) {
				   saveData(cliente);
				   return cliente;
			   }
			   c.setId(cliente.getId());
			   c.setNome(cliente.getNome());
			   c.setEmail(cliente.getEmail());
			   c.setSenha(cliente.getSenha());
			   return c;
		   }
		   finally {
			   em.close();
		   }
	   }
	
	public Cliente updateCliente(int id, String nome, String email, String senha) {
		Cliente c = em.find(Cliente.class, id);
		   
		   if(c == null) {
			   c = new Cliente(id, nome, email, senha);
			   
		   }
		   
		   c.setId(id);
		   c.setNome(nome);
		   c.setEmail(email);
		   c.setSenha(senha);
		   saveData(c);
		   return(c);
	   }
	
	public Cliente findCliente(int id) 
	{
		return em.find(Cliente.class, id);
	}
	public boolean removeCliente(int id) 
	{
		Cliente r = findCliente(id);
		if (r != null) 
		{
			em.remove(r);
		}
		return false ;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientes() 
	{
		Query qd = em.createQuery("Select c from Cliente c");
		return qd.getResultList();
	}
}