package dao.Implamentation;

import dao.Interfaces.SinistreDAOInterface;
import database.Connect;
import enums.TypeSinistre;
import models.Sinistre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SinistreDAO implements SinistreDAOInterface {
    private final Connection connection;

    public SinistreDAO() {
        this.connection = Connect.getInstance().getConnection();
    }

    @Override
    public void addSinistre(Sinistre sinistre, UUID contratId) {
        String sql = "INSERT INTO sinistres (id, type_sinistre, date_time, cout, description, contrat_id) VALUES (?,?::type_sinistre_enum,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sinistre.getId().toString());
            stmt.setString(2, sinistre.getTypeSinistre().toString());
            stmt.setTimestamp(3, Timestamp.valueOf(sinistre.getDateTime()));
            stmt.setDouble(4, sinistre.getCout());
            stmt.setString(5, sinistre.getDescription());
            stmt.setObject(6, contratId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSinistre(UUID id) {
        String sql = "DELETE FROM sinistres WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Sinistre> getSinistreById(UUID id) {
        String sql = "SELECT * FROM sinistres WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sinistre sinistre = new Sinistre();
                sinistre.setId(UUID.fromString(rs.getString("id")));
                sinistre.setTypeSinistre(TypeSinistre.valueOf(rs.getString("type_sinistre")));
                sinistre.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
                sinistre.setCout(rs.getDouble("cout"));
                sinistre.setDescription(rs.getString("description"));
                return Optional.of(sinistre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Sinistre> getSinistresByContratId(UUID contratId) {
        List<Sinistre> sinistres = new ArrayList<>();
        String sql = "SELECT * FROM sinistres WHERE contrat_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contratId.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sinistre sinistre = new Sinistre();
                sinistre.setId(UUID.fromString(rs.getString("id")));
                sinistre.setTypeSinistre(TypeSinistre.valueOf(rs.getString("type_sinistre")));
                sinistre.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
                sinistre.setCout(rs.getDouble("cout"));
                sinistre.setDescription(rs.getString("description"));
                sinistres.add(sinistre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinistres;
    }
    @Override
    public List<Sinistre> getAllSinistres() {
        List<Sinistre> sinistres = new ArrayList<>();
        String sql = "SELECT * FROM sinistres";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sinistre sinistre = new Sinistre();
                sinistre.setId(UUID.fromString(rs.getString("id")));
                sinistre.setTypeSinistre(TypeSinistre.valueOf(rs.getString("type_sinistre")));
                sinistre.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
                sinistre.setCout(rs.getDouble("cout"));
                sinistre.setDescription(rs.getString("description"));
                sinistres.add(sinistre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinistres;
    }
}
