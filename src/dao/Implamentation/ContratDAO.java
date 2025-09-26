package dao.Implamentation;

import dao.Interfaces.ContratDAOInterface;
import database.Connect;
import enums.TypeContrat;
import models.Contrat;

import java.sql.*;
import java.util.*;

public class ContratDAO implements ContratDAOInterface {
    private final Connection connection;
    public ContratDAO(){
        this.connection = Connect.getInstance().getConnection();
    };
    @Override
    public void addContrat(Contrat contrat, UUID clientId){
        String sql = "INSERT INTO contrats (id, type_contrat, date_debut, date_fin, client_id) VALUES (?,?::type_contrat_enum,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, contrat.getId());
            stmt.setString(2, contrat.getTypeContrat().toString());
            stmt.setDate(3, new java.sql.Date(contrat.getDateDebut().getTime()));
            stmt.setDate(4, new java.sql.Date(contrat.getDateFin().getTime()));
            stmt.setObject(5, clientId);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    };

    @Override
    public void deleteContrat(UUID id){
        String sql = "DELETE FROM contrats WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    };
    @Override
    public Optional<Contrat> getContratById(UUID id){
        String sql = "SELECT * FROM contrats WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Contrat contrat = new Contrat();
                contrat.setId(UUID.fromString(rs.getString("id")));
                contrat.setTypeContrat(TypeContrat.valueOf(rs.getString("type_contrat")));
                contrat.setDateDebut(rs.getDate("date_debut"));
                contrat.setDateFin(rs.getDate("date_fin"));
                return Optional.of(contrat);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    };
    @Override
    public List<Contrat> getContratsByClientId(UUID clientId){
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrats WHERE client_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Contrat contrat = new Contrat();
                contrat.setId(UUID.fromString(rs.getString("id")));
                contrat.setTypeContrat(TypeContrat.valueOf(rs.getString("type_contrat")));
                contrat.setDateDebut(rs.getDate("date_debut"));
                contrat.setDateFin(rs.getDate("date_fin"));
                contrats.add(contrat);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return contrats;
    };
    @Override
    public List<Contrat> getAllContrats(){
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrats";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Contrat contrat = new Contrat();
                contrat.setId(UUID.fromString(rs.getString("id")));
                contrat.setTypeContrat(TypeContrat.valueOf(rs.getString("type_contrat")));
                contrat.setDateDebut(rs.getDate("date_debut"));
                contrat.setDateFin(rs.getDate("date_fin"));
                contrats.add(contrat);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return contrats;
    };
}
