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
		em.getTransaction().begin();
		ProdutoService ps = new ProdutoService(getEM());
		List<Produto> produtoList = ps.findAllProduto();
		for (Produto p : produtoList) 
		{
			ps.removeProduto(p.getId());
		}
		em.getTransaction().commit();
		System.out.println("                       Cleaned DB                       ");
		System.out.println("--------------------------------------------------------");
		em.getTransaction().begin();
		Produto t1 = ps.updateProduto(1,"Hamburguer Vegan", "A melhor opção para todos os amantes de animais e de hamburguer", "Comida", "Tomate, Alface, Paprika, Alho, Grão de Bico, Pão, Cebola", "206 kCal", "Nenhum", 1);	
		Produto t2 = ps.updateProduto(2,"Lasanha", "A lasanha é um prato italiano feito com camadas de massa de lasanha, molho de tomate, molho béchamel, queijo e geralmente carne. As camadas são montadas e assadas até ficarem "
				+ "douradas e borbulhantes, resultando em um prato delicioso e reconfortante. ", "Comida", "A lasanha é um prato italiano feito com camadas de massa de lasanha, molho de tomate, molho béchamel, queijo e geralmente carne.",
				"Calorias: Cerca de 350-400 kcal Proteínas: 15-20g; Carboidratos: 30-40g; Gorduras: 15-20g; Fibras: 2-4g", "Glútean; Lactose; ", 20);
		Produto t3 = ps.updateProduto(3,"Pizza", "Esta obra-prima da culinária consiste em uma base de massa fina e crocante, tradicionalmente feita de farinha de trigo, água, sal e fermento, que é cuidadosamente estendida e moldada em um disco perfeito1", 
				"Grupo 2", "Farinha de trigo, Água, Sal, Fermento (ou fermento biológico seco), Azeite de oliva (opcional), Molho de tomate (ou molho de pizza), Tomates maduros (ou molho de tomate enlatado), Alho, Queijo", "100Kcal", "Gluten e lactose1", 7);
		em.getTransaction().commit();
		produtos = ps.findAllProduto();
		System.out.println("                   LISTA DE PRODUTOS                    \n");
		for (Produto pa : produtos)
		{
			System.out.println(pa);
		}
		System.out.println("--------------------------------------------------------");
		System.out.println("        LISTA DE PRODUTOS ORDENADAS POR PRECO\n");
		System.out.println("         PRODUTOS COM O PRECO ACIMA DE 9.99€\n");
		for (Produto pa : produtos) 
		{
			if(pa.getPreco() >= 10) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n\n         PRODUTOS COM O PRECO ACIMA DE 4.99€\n");
		for (Produto pa : produtos) 
		{
			if(pa.getPreco() >= 5  && pa.getPreco() < 10) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n\n         PRODUTOS COM O PRECO ABAIXO DE 5€\n");
		for (Produto pa : produtos) 
		{
			if(pa.getPreco() < 5 )
			{
				System.out.println(pa);
			}
		}
		System.out.println("--------------------------------------------------------");
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


