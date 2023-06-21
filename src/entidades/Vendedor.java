package entidades;

public class Vendedor {
	private int idVendedor;
	private String nome;
	private String senha;
	
	public Vendedor() {
		
	}
	public Vendedor(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	public Vendedor(int idVendedor, String nome, String senha) {
		this.idVendedor = idVendedor;
		this.senha = senha;
		this.nome = nome;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
