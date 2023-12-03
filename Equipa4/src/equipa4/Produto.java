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
public class Produto 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String nome;
	private String descricao;
	private String grupo;
	private String ingredientes;
	private int infNutricional;
	private String alergenios;
	private float preco;
	public Produto() 
	{
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	/** @return the nome */
	public String getNome() 
	{
		return nome;
	}
	/** @param nome the nome to set */
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	/** @return the descricao */
	public String getDescricao() 
	{
		return descricao;
	}
	/** @param descricao the descricao to set */
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	/** @return the grupo */
	public String getGrupo() 
	{
		return grupo;
	}
	/** @param grupo the grupo to set */
	public void setGrupo(String grupo) 
	{
		this.grupo = grupo;
	}
	/** @return the ingredientes */
	public String getIngredientes() 
	{
		return ingredientes;
	}
	/** @param ingredientes the ingredientes to set */
	public void setIngredientes(String ingredientes) 
	{
		this.ingredientes = ingredientes;
	}
	/** @return the infNutricional */
	public int getInfNutricional() 
	{
		return infNutricional;
	}
	/** @param infNutricional the infNutricional to set */
	public void setInfNutricional(int infNutricional) 
	{
		this.infNutricional = infNutricional;
	}
	/** @return the alergenios */
	public String getAlergenios() 
	{
		return alergenios;
	}
	/** @param alergenios the alergenios to set */
	public void setAlergenios(String alergenios) 
	{
		this.alergenios = alergenios;
	}
	/** @return the preco */
	public float getPreco() 
	{
		return preco;
	}
	/** @param preco the preco to set */
	public void setPreco(float preco) 
	{
		this.preco = preco;
	}
	@Override
	public String toString() 
	{
		return "\nProduto " + id + " [ Nome: " + nome + "; Descricao: " + descricao + "; Grupo: " + grupo + ";\n"
				+ "          [ Ingredientes: " + ingredientes + "; Informacao Nutricional: " + infNutricional
				+ " kCal; Alergenios: " + alergenios + ";\n" + "          [ Preço: " + preco + "€ ]";
	}
}
