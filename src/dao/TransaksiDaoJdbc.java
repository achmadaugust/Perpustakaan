/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import model.Transaksi;

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
public class TransaksiDaoJdbc {
    
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getIdByBukuStatement;

    private final String insertQuery = "insert into PINJAM(id_buku,npm,"
            + "tgl_dipinjam,tgl_kembali,tgl_dikembalikan) values(?,?,?,?,?)";
    private final String updateQuery = "update PINJAM set id_buku=?, " +
            " npm=? tgl_dipinjam=? tgl_kembali=? tgl_dikembalikan=? " + 
            "where id_trans=?";
    private final String deleteQuery = "delete from PINJAM where id_trans=?";
    private final String getByIdQuery = "select * from PINJAM where id_trans=?";
    private final String getAllQuery = "select * from PINJAM";
    private final String getIdByBukuQuery = "SELECT * from PINJAM WHERE id_buku=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
        getIdByBukuStatement = this.connection.prepareStatement(getIdByBukuQuery);
    }

    public Transaksi save(Transaksi transaksi) throws SQLException{
        if (transaksi.getIdTrans() == 0) {
            insertStatement.setInt(1, transaksi.getIdBuku());
            insertStatement.setInt(2, transaksi.getNpm());
            insertStatement.setInt(3, transaksi.getDatePinjam());
            insertStatement.setInt(4, transaksi.getDateKembali());
            insertStatement.setInt(5, transaksi.getDateDiKembalikan());
            int xid = (int) insertStatement.executeUpdate();
//            ambil id
            ResultSet rs = insertStatement.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                transaksi.setIdTrans(id);
            }
//            person.setId(getIdByName(person.getName()));
        } else {
            updateStatement.setInt(1, transaksi.getIdBuku());
            updateStatement.setInt(2, transaksi.getNpm());
            updateStatement.setInt(3, transaksi.getDatePinjam());
            updateStatement.setInt(4, transaksi.getDateKembali());
            updateStatement.setInt(5, transaksi.getDateDiKembalikan());
            updateStatement.setInt(6, transaksi.getIdTrans());
            updateStatement.executeUpdate();
        }
        return transaksi;
    }

    public Transaksi delete(Transaksi transaksi) throws SQLException{
        deleteStatement.setInt(1, transaksi.getIdTrans());
        deleteStatement.executeUpdate();
        return transaksi;
    }

    public Transaksi getById(int id) throws SQLException{
        getByIdStatement.setInt(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Transaksi transaksi = new Transaksi();
            transaksi.setIdTrans(rs.getInt("id"));
            transaksi.setNpm(rs.getInt("npm"));
            transaksi.setIdBuku(rs.getInt("buku"));
            transaksi.setDatePinjam(rs.getInt("pinjam"));
            transaksi.setDateKembali(rs.getInt("kembali"));
            transaksi.setDateDiKembalikan(rs.getInt("dikembalikan"));
            return transaksi;
        }
        return null;
    }

    public List<Transaksi> getAll() throws SQLException{
        List<Transaksi> transaksis = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Transaksi transaksi = new Transaksi();
            transaksi.setIdTrans(rs.getInt("id"));
            transaksi.setNpm(rs.getInt("npm"));
            transaksi.setIdBuku(rs.getInt("buku"));
            transaksi.setDatePinjam(rs.getInt("pinjam"));
            transaksi.setDateKembali(rs.getInt("kembali"));
            transaksi.setDateDiKembalikan(rs.getInt("dikembalikan"));
            transaksis.add(transaksi);
        }
        return transaksis;
    }

    public int getIdByBuku(int buku) throws SQLException{
        getIdByBukuStatement.setInt(1, buku);
        ResultSet rs = getIdByBukuStatement.executeQuery();
        if(rs.next()){
            int id = rs.getInt("id");
            return id;
        }
        return 0;
    }

}
