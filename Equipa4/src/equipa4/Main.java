package equipa4;
import java.util.List;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class Main 
{
	private static final String PERSISTENCE_UNIT_NAME = "LibraryHamburger";
	private static EntityManagerFactory factory;
	private static EntityManager emanager = null;
	public static void fill() 
	{
		System.out.println("========");
		System.out.println("  FILL  ");
		System.out.println("========");
		EntityManager em = getEM();
		Query q = null;
		List<Produto> produtos = null;
		List<Menus> menus = null;
		
		em.getTransaction().begin();
		ProdutoService ps = new ProdutoService(getEM());
		List<Produto> produtoList = ps.findAllProduto();
		for (Produto p : produtoList) 
		{
			ps.removeProduto(p.getId());
		}
		
		MenusService ms = new MenusService(getEM());
		List<Menus> menusList = ms.findAllMenus();
		for (Menus m : menusList) 
		{
			ms.removeMenus(m.getIdM());
		}
		
		em.getTransaction().commit();
		System.out.println("========");
		System.out.println("  Cleaned DB  ");
		System.out.println("========\n\n");
		System.out.println("*------------------------------------------------------*");
		em.getTransaction().begin();
		
		Menus m1 = ms.updateMenus(1, "Hamburguer Vegan", "A melhor opção para todos os amantes de animais e de hamburguer", "Comida", "Tomate, Alface, Paprika, Alho, Grão de Bico, Pão, Cebola", "206 kCal", "Nenhum", 4.99f);
		Menus m2 = ms.updateMenus(2,"Lasanha", "A lasanha é um prato italiano delicioso e reconfortante", "Comida", "Camadas de massa de lasanha, Molho de tomate, Molho béchamel, Queijo, Carne", "400 kCal", "Gluten, Lactose", 15.99f);
		
		Produto t1 = ps.updateProduto(1,"Hamburguer Vegan", "A melhor opção para todos os amantes de animais e de hamburguer", "Comida", "Tomate, Alface, Paprika, Alho, Grão de Bico, Pão, Cebola", "206 kCal", "Nenhum", 4.99f);	
		Produto t2 = ps.updateProduto(2,"Lasanha", "A lasanha é um prato italiano delicioso e reconfortante", "Comida", "Camadas de massa de lasanha, Molho de tomate, Molho béchamel, Queijo, Carne", "400 kCal", "Gluten, Lactose", 15.99f);
		Produto t3 = ps.updateProduto(3,"Pizza", "Esta obra-prima da culinária italiana satifaz qualquer um", "Comida", "Farinha, Água, Sal, Fermento, Azeite, Molho de tomate, Alho, Queijo", "100 kCal", "Gluten, Lactose", 7.89f);
		
		em.getTransaction().commit();
		
		menus = ms.findAllMenus();
		System.out.println("----------------------------------------------------");
		System.out.println("                   LISTA DE Menus                   ");
		for (Menus ma : menus)
		{
			System.out.println(ma);
		}
		
		produtos = ps.findAllProduto();
		System.out.println("----------------------------------------------------");
		System.out.println("                   LISTA DE PRODUTOS                    ");
		for (Produto pa : produtos)
		{
			System.out.println(pa);
		}
		
		System.out.println("\n*------------------------------------------------------*");
		System.out.println("\n\n\n*------------------------------------------------------*");
		System.out.println("        LISTA DE PRODUTOS ORDENADAS POR PRECO");
		System.out.println("\n\n      PRODUTOS COM O PRECO IGUAL OU ACIMA DE 10€");
		for (Produto pa : produtos) 
		{
			if(pa.getPreco() >= 10) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n\n       PRODUTOS COM O PRECO IGUAL OU ACIMA DE 5€");
		for (Produto pa : produtos) 
		{
			if(pa.getPreco() >= 5  && pa.getPreco() < 10) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n\n          PRODUTOS COM O PRECO ABAIXO DE 5€");
		for (Produto pa : produtos) 
		{
			if(pa.getPreco() < 5 )
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n*------------------------------------------------------*");
		System.out.println("\n\nAcabou!");
	}
	public static EntityManager getEM() 
	{
		if(emanager == null) 
		{
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			emanager = factory.createEntityManager();
		}
		return emanager;
	}
	public static void main(String[] args) 
	{
		fill();
	}
}