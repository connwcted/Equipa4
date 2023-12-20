package equipa4;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class AdministradorService 
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
	
	private boolean saveData(Administrador admin) {

        try {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
	
	public AdministradorService() {
		this.em = getEM();
	}
	
	public AdministradorService(EntityManager em) 
	{
		this.em = em;
	}
	
	public Administrador addAdministrador(int id, String nome, String email, String senha) {
		   EntityManager em = getEM();
		   
		   try {
			   Administrador a = em.find(Administrador.class, id);
			   if(a == null) {
				   a = new Administrador();
				   a.setId(id);
			   }
			   
			   a.setNome(nome);
			   a.setEmail(email);
			   a.setSenha(senha);
			   
			   saveData(a);
			   return a;
		   }
		   finally{
			   em.close();  
		   }
	   }
	
	public Administrador updateAdministrador(Administrador administrador) {
		   EntityManager em = getEM();
		   
		   try {
			   Administrador a = em.find(Administrador.class, administrador.getId());
			   
			   if(a == null) {
				   saveData(administrador);
				   return administrador;
			   }
			   a.setId(administrador.getId());
			   a.setNome(administrador.getNome());
			   a.setEmail(administrador.getEmail());
			   a.setSenha(administrador.getSenha());
			   return a;
		   }
		   finally {
			   em.close();
		   }
	   }
	   
	   public Administrador updateAdministrador(int id, String nome, String email, String senha) {
		   Administrador a = em.find(Administrador.class, id);
		   
		   if(a == null) {
			   a = new Administrador(id, nome, email, senha);
			   
		   }
		   
		   a.setId(id);
		   a.setNome(nome);
		   a.setEmail(email);
		   a.setSenha(senha);
		   saveData(a);
		   return(a);
	   }
	
	
	
	
	
	/**
	public Administrador updateAdministrador(int id, String nome, String email, String senha) 
	{
		Administrador a = em.find(Administrador.class, id);
		if (a == null) 
		{
			a = new Administrador();
			em.persist(a);
		}
		a.setId(id);
		a.setNome(nome);
		a.setEmail(email);
		a.setSenha(senha);
		em.persist(a);
		return a;
	}*/
	
	public Administrador findAdministrador(int id) 
	{
		return em.find(Administrador.class, id);
	}
	
	public boolean removeAdministrador(int id) 
	{
		Administrador a = findAdministrador(id);
		 if(a != null) {
			 em.getTransaction().begin();
			 em.remove(a);
			 em.getTransaction().commit();
			 return true;
		 }
		 return false;
	}
	@SuppressWarnings("unchecked")
	public List<Administrador> findAllAdministradores() 
	{
		Query qd = em.createQuery("Select a from Administrador a");
		return qd.getResultList();
	}
}