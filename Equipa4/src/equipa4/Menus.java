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
public class Menus {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idM;
	private String nomeM;
	private String descricaoM;
	private String grupoM;
	private String ingredientesM;
	private String infNM;
	private String alergenicosM;
	private float precoM;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Produto> produtos = new ArrayList<Produto>();
	
	public Menus() {
	}

	public int getIdM() {
		return idM;
	}

	public void setIdM(int idM) {
		this.idM = idM;
	}

	public String getNomeM() {
		return nomeM;
	}

	public void setNomeM(String nomeM) {
		this.nomeM = nomeM;
	}

	public String getDescricaoM() {
		return descricaoM;
	}

	public void setDescricaoM(String descricaoM) {
		this.descricaoM = descricaoM;
	}

	public String getGrupoM() {
		return grupoM;
	}

	public void setGrupoM(String grupoM) {
		this.grupoM = grupoM;
	}

	public String getIngredientesM() {
		return ingredientesM;
	}

	public void setIngredientesM(String ingredientesM) {
		this.ingredientesM = ingredientesM;
	}

	public String getInfNM() {
		return infNM;
	}

	public void setInfNM(String infNM) {
		this.infNM = infNM;
	}

	public String getAlergênicosM() {
		return alergenicosM;
	}

	public void setAlergenicosM(String alergenicosM) {
		this.alergenicosM = alergenicosM;
	}

	public float getPrecoM() {
		return precoM;
	}

	public void setPrecoM(float precoM) {
		this.precoM = precoM;
	}
	
	public List<Produto> getProdutos(){
		return produtos;
	}
	@Override
	public String toString() 
	{
		String x = "\nProduto " + idM 
			+  " [ Nome: " + nomeM + "; Descricao: " + descricaoM + "; Grupo: " + grupoM + ";\n"
			+  "          [ Ingredientes: " + ingredientesM + "; Informação Nutricional: " + infNM + "; Alergénios: " + alergenicosM + ";\n"
			+  "          [ Preço: " + precoM + "€";
		for(Produto p: produtos) {
			x += "Produtos [produto=" + p.getNome()+ "] \n";
		}
		return x;
	}
	
}

