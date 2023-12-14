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
public class Desconto 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private float desconto;
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Produto> produtos = new ArrayList<Produto>();
	public Desconto() 
	{
	}
	
	public Desconto(float desconto, List<Produto> produtos) {
		this.desconto = desconto;
		this.produtos = produtos;
	}



	public float getDesconto() 
	{
		return desconto;
	}
	public void setDesconto(float desconto) 
	{
		this.desconto = desconto;
	}
	public List<Produto> getProdutos() 
	{
		return produtos;
	}
	@Override
	public String toString() 
	{
		String x = "\nDesconto: " + String.format("%.0f", desconto) + "%";
		x += "\nProdutos:\n";
		for (Produto p : produtos) 
		{
		    float pDesconto = p.getPreco()*(1-(desconto/100));
		    String formattedPrice = String.format("%.2f", pDesconto);
		    x += "          Produto " + p.getId() + " [ Nome: " + p.getNome() + ";\n" 
		       + "                    [ Preco: " + formattedPrice + "€ ]\n";
		}
		return x;
	}
}
