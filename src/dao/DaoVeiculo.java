package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Veiculo;
import entidades.Vendedor;

public class DaoVeiculo {
	
	public boolean inserir(Veiculo veiculo) throws SQLException {
				
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "insert into veiculo (idveiculo, marca, modelo, ano, idvendedor) values(?, ? , ? , ?, ?);";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setInt(1, veiculo.getIdveiculo());
		ps.setString(2, veiculo.getMarca());
		ps.setString(3, veiculo.getModelo());
		ps.setInt(4, veiculo.getAno());
		ps.setInt(5, veiculo.getVendedor().getIdVendedor());
		
		ps.executeUpdate();
		return true;
		
	}

	public boolean atualizar(Veiculo veiculo) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "update veiculo set marca = ?, modelo = ?, ano = ?, where idveiculo = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, veiculo.getMarca());
		ps.setString(2, veiculo.getModelo());
		ps.setInt(3, veiculo.getAno());
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConexao();
		
		String sql = "delete from veiculo where idveiculo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public Veiculo buscar(int idBuscado) throws SQLException {
		
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from veiculo where idveiculo = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Veiculo veiculo = null;
		
		if( result.next() ) {
			int id = result.getInt("idveiculo");
			String marca = result.getString("marca");
			String modelo = result.getString("modelo");
			int ano = result.getInt("ano");
			int idVend = result.getInt("idvendedor");
			
			DaoVendedor daoVend = new DaoVendedor();
			Vendedor ven = daoVend.buscarPorId(id);
			
			veiculo = new Veiculo(id, marca, modelo, ano, ven);
		}
		return veiculo;
	}

	public List<Veiculo> buscarT() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from veiculo";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		ArrayList<Veiculo> veiculo = new ArrayList<Veiculo>();
		
		DaoVendedor daoVend = new DaoVendedor();

		while( result.next() ) {
			int id = result.getInt("idveiculo");
			String marca = result.getString("marca");
			String modelo = result.getString("modelo");
			int ano = result.getInt("ano");

			
			Vendedor ven = daoVend.buscarPorId(id);
			
			Veiculo v = new Veiculo(id, marca,modelo, ano, ven);
	
			veiculo.add(v);
		}
		
		return veiculo;
	}

	public List<Veiculo> pesquisarPorModelo(String texto) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from veiculo where modelo like ? ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%"+texto+"%");
		
		ResultSet result = ps.executeQuery();
		
		List<Veiculo> veiculo = new ArrayList<Veiculo>();
		
		DaoVendedor daoVend = new DaoVendedor();
		
		while( result.next() ) {
			int id = result.getInt("idveiculo");
			String marca = result.getString("marca");
			String modelo = result.getString("modelo");
			int ano = result.getInt("ano");
			int idVend = result.getInt("idvendedor");
			
			Vendedor vend = daoVend.buscarPorId(idVend);
			Veiculo v = new Veiculo(id, marca, modelo, ano, vend);
	
			veiculo.add(v);
		}
		
		return veiculo;
	}
	
}
