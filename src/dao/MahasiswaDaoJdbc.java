/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import model.Mahasiswa;

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
public class MahasiswaDaoJdbc {
    
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getIdByNameStatement;

    private final String insertQuery = "insert into MAHASISWA(nama,tgl_lahir," + 
            "alamat) values(?,?,?)";
    private final String updateQuery = "update MAHASISWA set npm=?, nama=?," +
            " tgl_lahir=? alamat=? where npm=?";
    private final String deleteQuery = "delete from MAHASISWA where npm=?";
    private final String getByIdQuery = "select * from MAHASISWA where npm=?";
    private final String getAllQuery = "select * from MAHASISWA";
    private final String getIdByNameQuery = "select * from MAHASISWA where nama=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
        getIdByNameStatement = this.connection.prepareStatement(getIdByNameQuery);
    }

    public Mahasiswa save(Mahasiswa mahasiswa) throws SQLException{
        if (mahasiswa.getNpm() == 0) {
//            insertStatement.setInt(1, mahasiswa.getNpm());
            insertStatement.setString(1, mahasiswa.getNama());
            insertStatement.setInt(2, mahasiswa.getDate());
            insertStatement.setString(3, mahasiswa.getAlamat());
            int xid = (int) insertStatement.executeUpdate();
//            ambil id
            ResultSet rs = insertStatement.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                mahasiswa.setNpm(id);
            }
//            person.setId(getIdByName(person.getName()));
        } else {
//            updateStatement.setInt(1, mahasiswa.getNpm());
            updateStatement.setString(1, mahasiswa.getNama());
            updateStatement.setInt(2, mahasiswa.getDate());
            updateStatement.setString(3, mahasiswa.getAlamat());
            updateStatement.setInt(4, mahasiswa.getNpm());
            updateStatement.executeUpdate();
        }
        return mahasiswa;
    }

    public Mahasiswa delete(Mahasiswa mahasiswa) throws SQLException{
        deleteStatement.setInt(1, mahasiswa.getNpm());
        deleteStatement.executeUpdate();
        return mahasiswa;
    }

    public Mahasiswa getById(int id) throws SQLException{
        getByIdStatement.setInt(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Mahasiswa mahasiswa = new Mahasiswa();
//            mahasiswa.setId_mhs(rs.getLong("id_mhs"));
            mahasiswa.setNpm(rs.getInt("Npm"));
            mahasiswa.setNama(rs.getString("Nama"));
            mahasiswa.setDate(rs.getInt("Date"));
            mahasiswa.setAlamat(rs.getString("Alamat"));
            return mahasiswa;
        }
        return null;
    }

    public List<Mahasiswa> getAll() throws SQLException{
        List<Mahasiswa> mahasiswas = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Mahasiswa mahasiswa = new Mahasiswa();
//            mahasiswa.setId_mhs(rs.getLong("id_mhs"));
            mahasiswa.setNpm(rs.getInt("Npm"));
            mahasiswa.setNama(rs.getString("Nama"));
            mahasiswa.setDate(rs.getInt("Date"));
            mahasiswa.setAlamat(rs.getString("Alamat"));
            mahasiswas.add(mahasiswa);
        }
        return mahasiswas;
    }

    public int getIdByName(String nama) throws SQLException{
        getIdByNameStatement.setString(1, nama);
        ResultSet rs = getIdByNameStatement.executeQuery();
        if(rs.next()){
            int id = rs.getInt("npm");
            return id;
        }
        return 0;
    }

}
