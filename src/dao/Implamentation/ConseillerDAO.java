package dao.Implamentation;

import dao.Interfaces.ConseillerDAOInterface;
import database.Connect;
import models.Client;
import models.Conseiller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ConseillerDAO implements ConseillerDAOInterface {
    private final Connection connection;

    public ConseillerDAO() {
        this.connection = Connect.getInstance().getConnection();
    }

    @Override
    public void addConseiller(Conseiller conseiller) {
        String sql = "INSERT INTO conseillers (id, nom, prenom, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, conseiller.getId());
            stmt.setString(2, conseiller.getNom());
            stmt.setString(3, conseiller.getPrenom());
            stmt.setString(4, conseiller.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConseiller(UUID id) {
        String sql = "DELETE FROM conseillers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Conseiller> getConseillerById(UUID id) {
        String sql = "SELECT * FROM conseillers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Conseiller conseiller = new Conseiller();
                conseiller.setId(UUID.fromString(rs.getString("id")));
                conseiller.setNom(rs.getString("nom"));
                conseiller.setPrenom(rs.getString("prenom"));
                conseiller.setEmail(rs.getString("email"));
                return Optional.of(conseiller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Client> getClientsByConseillerId(UUID conseillerId) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, conseillerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(UUID.fromString(rs.getString("id")));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
