package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	Connection con;
	String sql;
	PreparedStatement pst;
	
	
	public void salvar(Aluno a) throws IOException {
		
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES ( ?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
			
			
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws IOException{
		try {
			List<Aluno> alunos = new ArrayList<Aluno>();
			Connection con = db.getConexao();
			
			sql = "SELECT * FROM aluno";
			
			pst = con.prepareStatement(sql);
			ResultSet rSet = pst.executeQuery();
			
			while(rSet.next()) 
			{
				Aluno aluno = new Aluno();
				aluno.setDt_nasc(rSet.getDate("dt_nasc"));
				aluno.setId(rSet.getInt("id"));
				aluno.setNome(rSet.getString("nome"));
				aluno.setSexo(rSet.getString("sexo"));
				alunos.add(aluno);
			}
	
			return alunos;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void apagar(int id) throws IOException {
		try {
			Connection con = db.getConexao();
			
			sql = "DELETE FROM Aluno where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			pst.execute();
			pst.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void alterar(Aluno a) throws IOException{
		try {
			Connection con = db.getConexao();
			
			sql = "UPDATE Aluno SET dt_nasc=?,sexo=?,nome=? where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(4, a.getId());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(1, dataSql);
			pst.setString(2, a.getSexo());
			pst.setString(3, a.getNome());
			
			pst.execute();
			pst.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

