package equipa4;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Encomenda 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int numEnc;
	private float precoEnc;
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Produto> produtos = new ArrayList<Produto>();
	List<Menu> menus = new ArrayList<Menu>();
	List<Desconto> descontos = new ArrayList<Desconto>();
	public Encomenda() 
	{
	}
	public int getNumEnc() 
	{
		return numEnc;
	}
	public void setNumEnc(int numEnc) 
	{
		this.numEnc = numEnc;
	}
	public float getPrecoEnc() 
	{
		return precoEnc;
	}
	public void setPrecoEnc(float precoM) 
	{
		this.precoEnc = precoM;
	}
	public List<Produto> getProdutos() 
	{
		return produtos;
	}
	public List<Menu> getMenus() 
	{
		return menus;
	}
	public List<Desconto> getDescontos() 
	{
		return descontos;
	}
	@Override
	public String toString() 
	{
		String x = "\nNumero de encomenda: " + numEnc 
				 + "\nPreco total: " + precoEnc + "€"
		         + "\nItems incluidos na encomenda:\n\n";
		for (Produto p : produtos) 
		{
			x += "       Produto " + p.getId() + " [ Nome: " + p.getNome() + ";\n" 
		       + "                 [ Preco: " + String.format("%.2f", p.getPreco()) + " ]\n";
		}
		for (Menu m : menus) 
		{
			x += "       Menu " + m.getIdM() + " [ Nome: " + m.getNomeM() + ";\n" 
		       + "              [ Preco:" + String.format("%.2f", m.getPrecoM()) + " ]\n";
		}
		for (Desconto e : descontos) 
		{
			x += e.toString();
		}
		return x;
	}
}