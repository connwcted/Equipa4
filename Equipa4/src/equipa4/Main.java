package equipa4;
import java.util.List;
import java.util.ArrayList;
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
		List<Menu> menus = null;
		List<Cliente> clientes = null;
		List<Administrador> administradores = null;
		List<Desconto> descontos = null;
		List<Encomenda> encomendas = null;

		em.getTransaction().begin();

		AdministradorService as = new AdministradorService(getEM());
		List<Administrador> administradorList = as.findAllAdministradores();
		for (Administrador a : administradorList) 
		{
			as.removeAdministrador(a.getId());
		}

		ClienteService cs = new ClienteService(getEM());
		List<Cliente> clienteList = cs.findAllClientes();
		for (Cliente c : clienteList) 
		{
			cs.removeCliente(c.getId());
		}

		ProdutoService ps = new ProdutoService(getEM());
		List<Produto> produtoList = ps.findAllProduto();
		for (Produto p : produtoList) 
		{
			ps.removeProduto(p.getId());
		}

		MenuService ms = new MenuService(getEM());
		List<Menu> menuList = ms.findAllMenus();
		for (Menu m : menuList) 
		{
			ms.removeMenu(m.getIdM());
		}
		
		DescontoService ds = new DescontoService(getEM());
		List<Desconto> descontosList = ds.findAllDescontos();
		for (Desconto d : descontosList) 
		{
			ds.removeDesconto(d.getDesconto());
		}
		
		EncomendaService es = new EncomendaService(getEM());
		List<Encomenda> encomendaList = es.findAllEncomendas();
		for (Encomenda e : encomendaList) 
		{
			es.removeEncomenda(e.getNumEnc());
		}

		em.getTransaction().commit();

		System.out.println("========");
		System.out.println("  CLEANED DB  ");
		System.out.println("========\n\n");

		em.getTransaction().begin();

		Administrador a1 = as.updateAdministrador(0, "Admin", "admin@gmail.com", "Admin123!");

		Cliente c1 = cs.updateCliente(0, "Andre", "andre123@gmail.com", "Andre123");
		Cliente c2 = cs.updateCliente(0, "Carlos", "carlitos123@gmail.com", "Joaquim123");

		Produto p1 = ps.updateProduto(0, "Hamburguer Vegano", "Hamburguer vegano delicioso e facil de fazer", "Comida", "Grao-de-bico, Cebola, Alho, Farinha de trigo, Pimenta-do-reino, Sal, Salsinha, Azeite", 200, "Nenhum", 5.99f);
		Produto p2 = ps.updateProduto(0, "Lasanha a Bolonhesa", "Lasanha classica italiana", "Comida", "Massa de lasanha, Molho de tomate, Carne moida, Queijo mussarela, Queijo parmesao, Cebola, Alho, Azeite, Sal, Pimenta-do-reino", 350, "Nenhum", 14.99f);
		Produto p3 = ps.updateProduto(0, "Pizza Margherita", "Pizza classica italiana", "Comida", "Massa de pizza, Molho de tomate, Mussarela, Manjericao", 250, "Nenhum", 9.99f);
		Produto p4 = ps.updateProduto(0, "Batatas Fritas", "Deliciosas batatas fritas crocantes", "Comida", "Batatas, Oleo, Sal", 312, "Nenhum", 2.99f);
		Produto p5 = ps.updateProduto(0, "Arroz Branco", "Arroz branco soltinho e delicioso", "Comida", "Arroz, Agua, Sal", 300, "Nenhum", 1.99f);
		Produto p6 = ps.updateProduto(0, "Refrigerante Caseiro", "Bebida gaseificada caseira e refrescante", "Bebida", "Agua com gas, Suco de limao, Açúcar", 150, "Nenhum", 3.99f);

		int menuKCal = p1.getInfNutricional() + p4.getInfNutricional();
		Menu m1 = ms.updateMenu(0, "Menu Vegano", "Menu delicioso e saudavel para veganos", "Menu", menuKCal, "Nenhum", 7.99f);
		m1.getProdutos().add(p1);
		m1.getProdutos().add(p4);
		
		Desconto d1 = ds.updateDesconto(20f);
		Desconto d2 = ds.updateDesconto(10f);
		d1.getProdutos().add(p2);
		d1.getProdutos().add(p3);
		d2.getProdutos().add(p4);
		
		float encPreco = m1.getPrecoM();
		Encomenda e1 = es.updateEncomenda(0, encPreco);
		e1.getMenus().add(m1);
		
		encPreco = m1.getPrecoM() + p6.getPreco();
		Encomenda e2 = es.updateEncomenda(0, encPreco);
		e2.getMenus().add(m1);
		e2.getProdutos().add(p6);
		
		encPreco = 0;
		Encomenda e3 = es.updateEncomenda(0, encPreco);
		e3.getDescontos().add(d1);
		e3.getDescontos().add(d2);
		
		
		em.getTransaction().commit();

		administradores = as.findAllAdministradores();
		System.out.println("*------------------------------------------------------*");
		System.out.println("                   ADMINISTRADOR                ");
		for (Administrador aa : administradores) 
		{
			System.out.println(aa);
		}
		System.out.println("\n*------------------------------------------------------*");
		
		clientes = cs.findAllClientes();
		System.out.println("\n\n\n*------------------------------------------------------*");
		System.out.println("                   LISTA DE CLIENTES                 ");
		for (Cliente ca : clientes) 
		{
			System.out.println(ca);
		}
		System.out.println("\n*------------------------------------------------------*");
		
		menus = ms.findAllMenus();
		System.out.println("\n\n\n*------------------------------------------------------*");
		System.out.println("                   LISTA DE MENUS                   ");
		for (Menu ma : menus) 
		{
			System.out.println(ma);
		}
		System.out.println("\n*------------------------------------------------------*");
		
		produtos = ps.findAllProduto();
		System.out.println("\n\n\n*------------------------------------------------------*");
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
			if (pa.getPreco() >= 10) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n\n       PRODUTOS COM O PRECO IGUAL OU ACIMA DE 5€");
		for (Produto pa : produtos) 
		{
			if (pa.getPreco() >= 5 && pa.getPreco() < 10) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n\n          PRODUTOS COM O PRECO ABAIXO DE 5€");
		for (Produto pa : produtos) 
		{
			if (pa.getPreco() < 5) 
			{
				System.out.println(pa);
			}
		}
		System.out.println("\n*------------------------------------------------------*");
		
		descontos = ds.findAllDescontos();
		System.out.println("\n\n\n*------------------------------------------------------*");
		System.out.println("                  PRODUTOS COM DESCONTO                ");
		for (Desconto da : descontos) 
		{
			System.out.println(da);
		}
		System.out.println("\n*------------------------------------------------------*");
		
		encomendas = es.findAllEncomendas();
		System.out.println("\n\n\n*------------------------------------------------------*");
		System.out.println("                   TODAS AS ENCOMENDAS                  ");
		for (Encomenda ea : encomendas) 
		{
			System.out.println(ea);
		}
		System.out.println("\n*------------------------------------------------------*");
		System.out.println("\n\nAcabou!");
	}
	public static EntityManager getEM() 
	{
		if (emanager == null) 
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