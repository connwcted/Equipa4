package equipa4;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Cliente 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String nome;
	private String email;
	private String senha;
	List<Encomenda> encomendas = new ArrayList<Encomenda>();
	public Cliente() 
	{
	}
	
	public Cliente(int id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}



	public int getId() 
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getSenha() 
	{
		return senha;
	}
	public void setSenha(String senha) 
	{
		this.senha = senha;
	}
	public List<Encomenda> getEncomendas() 
    {
        return encomendas;
    }
	@Override
    public String toString() 
    {
        String x = "\nCliente " + id + " [ Nome: " + nome + "; Email: " + email + " ]"
        		+ "\nTodas as encomendas:\n";
        for (Encomenda e : encomendas) 
        {
            x += e.toString();
        }
        return x;
    }
}