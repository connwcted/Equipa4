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
public class Menus 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idM;
	private String nomeM;
	private String descricaoM;
	private String grupoM;
	private String ingredientesM;
	private int infNM;
	private String alergenicosM;
	private float precoM;
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Produto> produtos = new ArrayList<Produto>();
	public Menus() 
	{
	}
	public int getIdM() 
	{
		return idM;
	}
	public void setIdM(int idM) 
	{
		this.idM = idM;
	}
	public String getNomeM() 
	{
		return nomeM;
	}
	public void setNomeM(String nomeM) 
	{
		this.nomeM = nomeM;
	}
	public String getDescricaoM() 
	{
		return descricaoM;
	}
	public void setDescricaoM(String descricaoM) 
	{
		this.descricaoM = descricaoM;
	}
	public String getGrupoM() 
	{
		return grupoM;
	}
	public void setGrupoM(String grupoM) 
	{
		this.grupoM = grupoM;
	}
	public String getIngredientesM() 
	{
		return ingredientesM;
	}
	public void setIngredientesM(String ingredientesM) 
	{
		this.ingredientesM = ingredientesM;
	}
	public int getInfNM() 
	{
		return infNM;
	}
	public void setInfNM(int infNM) 
	{
		this.infNM = infNM;
	}
	public String getAlergenicosM() 
	{
		return alergenicosM;
	}
	public void setAlergenicosM(String alergenicosM) 
	{
		this.alergenicosM = alergenicosM;
	}
	public float getPrecoM() 
	{
		return precoM;
	}
	public void setPrecoM(float precoM) 
	{
		this.precoM = precoM;
	}
	public List<Produto> getProdutos() 
	{
		return produtos;
	}
	@Override
	public String toString() 
	{
		String x = "\nMenu " + idM + " [ Nome: " + nomeM + "; Descricao: " + descricaoM + "; Grupo: " + grupoM + ";\n"
				+ "       [ Informacao Nutricional: " + infNM + " kCal; Alergenios: " + alergenicosM + ";\n"
				+ "       [ Preço: " + precoM + "€ ]\n\n";
		     x += "       Produtos incluidos no menu:\n\n";
		for (Produto p : produtos) 
		{
			x += "       Produto " + p.getId() + " [ Nome: " + p.getNome() + ";\n" 
		       + "                 [ Ingredientes: " + p.getIngredientes() + " ]\n";
		}
		return x;
	}
}
