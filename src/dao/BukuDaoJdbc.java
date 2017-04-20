/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import model.Buku;

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
public class BukuDaoJdbc {
    
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getIdByJudulStatement;

    private final String insertQuery = "insert into BUKU(judul,penulis,) "
            + "values(?,?,?)";
    private final String updateQuery = "update BUKU set judul=?, " +
            " penulis=? kategori=? where id=?";
    private final String deleteQuery = "delete from BUKU where id=?";
    private final String getByIdQuery = "select * from BUKU where id =?";
    private final String getAllQuery = "select * from BUKU";
    private final String getIdByJudulQuery = "SELECT * from BUKU WHERE nama=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
        getIdByJudulStatement = this.connection.prepareStatement(getIdByJudulQuery);
    }

    public Buku save(Buku buku) throws SQLException{
        if (buku.getId() == 0) {
            insertStatement.setString(1, buku.getJudul());
            insertStatement.setString(2, buku.getPenulis());
            insertStatement.setString(3, buku.getKategori());
            int xid = (int) insertStatement.executeUpdate();
//            ambil id
            ResultSet rs = insertStatement.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                buku.setId(id);
            }
//            person.setId(getIdByName(person.getName()));
        } else {
            updateStatement.setString(1, buku.getJudul());
            updateStatement.setString(2, buku.getPenulis());
            updateStatement.setString(3, buku.getKategori());
            updateStatement.setLong(4, buku.getId());
            updateStatement.executeUpdate();
        }
        return buku;
    }

    public Buku delete(Buku buku) throws SQLException{
        deleteStatement.setInt(1, buku.getId());
        deleteStatement.executeUpdate();
        return buku;
    }

    public Buku getById(int id) throws SQLException{
        getByIdStatement.setLong(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Buku buku = new Buku();
            buku.setId(rs.getInt("id"));
            buku.setJudul(rs.getString("Judul"));
            buku.setPenulis(rs.getString("Penulis"));
            buku.setKategori(rs.getString("Kategori"));
            return buku;
        }
        return null;
    }

    public List<Buku> getAll() throws SQLException{
        List<Buku> bukus = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Buku buku = new Buku();
            buku.setId(rs.getInt("id"));
            buku.setJudul(rs.getString("Judul"));
            buku.setPenulis(rs.getString("Penulis"));
            buku.setKategori(rs.getString("Kategori"));
            bukus.add(buku);
        }
        return bukus;
    }

    public int getIdByJudul(String judul) throws SQLException{
        getIdByJudulStatement.setString(1, judul);
        ResultSet rs = getIdByJudulStatement.executeQuery();
        if(rs.next()){
            int id = rs.getInt("id");
            return id;
        }
        return 0;
    }

}
