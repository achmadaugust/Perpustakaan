/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import model.Petugas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AT
 */
public class PetugasDaoJdbc {
    
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getIdByNameStatement;
    private PreparedStatement getLoginStatement;

    private final String insertQuery = "insert into PETUGAS(nama,password) " +
            " values(?,?)";
    private final String updateQuery = "update PETUGAS set nama=?, " +
            " password=? where id=?";
    private final String deleteQuery = "delete from PETUGAS where id=?";
    private final String getByIdQuery = "select * from PETUGAS where id =?";
    private final String getAllQuery = "select * from PETUGAS";
    private final String getIdByNameQuery = "SELECT * from PETUGAS WHERE nama=?";
    private final String getLoginQuery = "SELECT * from PETUGAS WHERE nama=? and password=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
        getIdByNameStatement = this.connection.prepareStatement(getIdByNameQuery);
        getLoginStatement = this.connection.prepareStatement(getLoginQuery);
    }

    public Petugas save(Petugas petugas) throws SQLException{
        if (petugas.getId() == 0) {
            insertStatement.setString(1, petugas.getNama());
            insertStatement.setString(2, petugas.getPassword());
            int xid = (int) insertStatement.executeUpdate();
//            ambil id
            ResultSet rs = insertStatement.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                petugas.setId(id);
            }
//            person.setId(getIdByName(person.getName()));
        } else {
            updateStatement.setString(1, petugas.getNama());
            updateStatement.setString(2, petugas.getPassword());
            updateStatement.setLong(3, petugas.getId());
            updateStatement.executeUpdate();
        }
        return petugas;
    }

    public Petugas delete(Petugas petugas) throws SQLException{
        deleteStatement.setLong(1, petugas.getId());
        deleteStatement.executeUpdate();
        return petugas;
    }

    public Petugas getById(int id) throws SQLException{
        getByIdStatement.setLong(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Petugas petugas = new Petugas();
            petugas.setId(rs.getInt("id"));
            petugas.setNama(rs.getString("nama"));
            petugas.setPassword(rs.getString("password"));
            return petugas;
        }
        return null;
    }

    public List<Petugas> getAll() throws SQLException{
        List<Petugas> petugass = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Petugas petugas = new Petugas();
            petugas.setId(rs.getInt("id"));
            petugas.setNama(rs.getString("nama"));
            petugas.setPassword(rs.getString("password"));
            petugass.add(petugas);
        }
        return petugass;
    }

    public int getIdByName(String name) throws SQLException{
        getIdByNameStatement.setString(1, name);
        ResultSet rs = getIdByNameStatement.executeQuery();
        if(rs.next()){
            int id = rs.getInt("id");
            return id;
        }
        return 0;
    }
        public Petugas login(String nama, String password) throws SQLException{
        getLoginStatement.setString(1, nama);
        getLoginStatement.setString(2, password);
        ResultSet rs = getLoginStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Petugas petugas = new Petugas();
            petugas.setId(rs.getInt("id"));
            petugas.setNama(rs.getString("nama"));
            return petugas;
        }
        return null;
    }
}
