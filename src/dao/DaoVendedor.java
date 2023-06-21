package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Vendedor;

public class DaoVendedor {
	
	public void inserir(Vendedor vend) throws SQLException {
		
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "INSERT INTO vendedor (nome, senha) VALUES (?, SHA2(?, 256))";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setString(1, vend.getNome());
		ps.setString(2, vend.getSenha());

		ps.executeUpdate();
	}
	
	public Vendedor buscarPorId(int idBuscado) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from vendedor where idvendedor = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Vendedor vend = null;
		
		if( result.next() ) {
			int id = result.getInt("idvendedor");
			String nome = result.getString("nome");
			String senha = result.getString("SENHA");
			
			vend = new Vendedor(id, nome, senha);
		}
		
		return vend;
	}
	
	public List<Vendedor> buscarT() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from vendedor";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Vendedor> vend = new ArrayList<Vendedor>();
		
		while( result.next() ) {
			int id = result.getInt("idvendedor");
			String nome = result.getString("nome");
			String senha = result.getString("senha");
			
			Vendedor vende = new Vendedor(id, nome, senha);
	
			vend.add(vende);
		}
		
		return vend;
	}
}
