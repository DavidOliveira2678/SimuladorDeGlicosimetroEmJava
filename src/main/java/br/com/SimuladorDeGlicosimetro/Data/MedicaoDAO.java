package br.com.SimuladorDeGlicosimetro.Data;

import br.com.SimuladorDeGlicosimetro.Entities.Medicao;
import br.com.SimuladorDeGlicosimetro.Exceptions.DatabaseException;
import br.com.SimuladorDeGlicosimetro.Utils.ConectorBD;
import br.com.SimuladorDeGlicosimetro.Utils.Estado;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularLinkedList;
import br.com.SimuladorDeGlicosimetro.Utils.ListaCircular.CircularList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicaoDAO {
	
	/**
	 * Realiza o cadastro de uma {@code Medicao} no banco de dados.
	 * @param med o objeto do tipo {@code Medicao}
	 * @return <I>void</I>
	 * @throws DatabaseException se ocorrer algum erro interno no banco de dados.
	 */
	public void cadastrarMedicao(Medicao med) throws DatabaseException {
		
		String query = "INSERT INTO medicoes (glicemia, estado) VALUES (?, ?)";
		
		try (PreparedStatement ps = ConectorBD.conectar().prepareStatement(query)){
			
			ps.setInt(1, med.getMedicao());
			ps.setString(2, String.format("%s", med.getEstado()).replace("_", " "));
			
			ps.execute();
			
		} catch(SQLException e) {
			throw new DatabaseException("Erro ao cadastrar medição" + e);
		}
		
	}
	
	/**
	 * Realiza uma busca de todas medições registradas no banco de dados e as retorna em formato de {@code List}.
	 * @return Retorna uma lista ({@code List}) de {@code Medicao}
	 * @throws DatabaseException se ocorrer algum erro interno no banco de dados.
	 */
	public CircularList<Medicao> resgatarTodasMedicoes() throws DatabaseException {
		
		String query = "SELECT id, glicemia, estado, dataMedicao, horarioMedicao FROM medicoes;";
		CircularList<Medicao> medicoes = new CircularLinkedList<Medicao>();
		
		try(PreparedStatement ps = ConectorBD.conectar().prepareStatement(query)){
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					Medicao med = new Medicao(rs.getInt("id"), rs.getInt("glicemia"), Estado.fromString(rs.getString("estado")),
							rs.getDate("dataMedicao").toLocalDate(), rs.getTime("horarioMedicao").toLocalTime());
					
					medicoes.add(med);
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Erro ao cadastrar medição" + e);
		}
		
		return medicoes;
		
	}
	
	/**
	 * Realiza uma busca de todas medições <B>acima de 180</B> <I>(estados de HIPERGLICEMIA e HI (High))</I>
	 * registradas no banco de dados e as retorna em formato de {@code List}.
	 * @return Retorna uma lista ({@code List}) de {@code Medicao}
	 * @throws DatabaseException se ocorrer algum erro interno no banco de dados.
	 */
	public List<Medicao> resgatarHiperglicemias() throws DatabaseException {
		
		String query = "SELECT id, glicemia, estado, dataMedicao, horarioMedicao FROM medicoes WHERE glicemia > 180;";
		List<Medicao> medicoes = new ArrayList<Medicao>();
		
		try (PreparedStatement ps = ConectorBD.conectar().prepareStatement(query)) {
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Medicao med = new Medicao(rs.getInt("id"), rs.getInt("glicemia"), Estado.fromString(rs.getString("estado")),
							rs.getDate("dataMedicao").toLocalDate(), rs.getTime("horarioMedicao").toLocalTime());
					
					medicoes.add(med);
				}
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Erro ao cadastrar medição" + e);
		}
		
		return medicoes;
	}
	
	/**
	 * Realiza uma busca de todas medições <B>abaixo de 70</B> <I>(estado de HIPOGLICEMIA e LO (Low))</I>
	 * registradas no banco de dados e as retorna em formato de {@code List}.
	 * @return Retorna uma lista ({@code List}) de {@code Medicao}
	 * @throws DatabaseException se ocorrer algum erro interno no banco de dados.
	 */
	public List<Medicao> resgatarHipoglicemias() throws DatabaseException {
		
		String query = "SELECT id, glicemia, estado, dataMedicao, horarioMedicao FROM medicoes WHERE glicemia < 70;";
		List<Medicao> medicoes = new ArrayList<Medicao>();
		
		try (PreparedStatement ps = ConectorBD.conectar().prepareStatement(query)) {
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Medicao med = new Medicao(rs.getInt("id"), rs.getInt("glicemia"), Estado.fromString(rs.getString("estado")),
							rs.getDate("dataMedicao").toLocalDate(), rs.getTime("horarioMedicao").toLocalTime());
					
					medicoes.add(med);
				}
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Erro ao cadastrar medição" + e);
		}
		
		return medicoes;
	}
	
	/**
	 * Deleta todas as medições do banco de dados.
	 * @return <I>void</I>
	 * @throws DatabaseException se ocorrer algum erro interno no banco de dados.
	 */
	public void deletarMedicoes() throws DatabaseException {
		
		String query = "TRUNCATE TABLE medicoes;";
		
		try (PreparedStatement ps = ConectorBD.conectar().prepareStatement(query)){
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new DatabaseException("Erro ao cadastrar medição" + e);
		}
		
	}
	
}
