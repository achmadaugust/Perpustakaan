package service;


import dao.PetugasDaoJdbc;
import dao.BukuDaoJdbc;
import dao.MahasiswaDaoJdbc;
import dao.TransaksiDaoJdbc;
import model.Petugas;
import model.Buku;
import model.Mahasiswa;
import model.Transaksi;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hahn on 16/03/17.
 * diambil dari buku Java Desktop by Ifnu Bima
 */
public class ServiceJdbc {

    private PetugasDaoJdbc petugasDao;
    private BukuDaoJdbc bukuDao;
    private MahasiswaDaoJdbc mahasiswaDao;
    private TransaksiDaoJdbc transaksiDao;
    private Connection connection;
    
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            petugasDao = new PetugasDaoJdbc();
            petugasDao.setConnection(connection);
            bukuDao = new BukuDaoJdbc();
            bukuDao.setConnection(connection);
            mahasiswaDao = new MahasiswaDaoJdbc();
            mahasiswaDao.setConnection(connection);
            transaksiDao = new TransaksiDaoJdbc();
            transaksiDao.setConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Petugas save(Petugas petugas){
        try {
            //set id
            connection.setAutoCommit(false);
            int id = petugasDao.getIdByName(petugas.getNama());
            connection.commit();
            connection.setAutoCommit(true);
            if(id != 0){
                petugas.setId(id);
                return petugas;
            }

            connection.setAutoCommit(false);
            petugasDao.save(petugas);
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return petugas;
    }
    public Petugas delete(Petugas petugas){
        try {
            connection.setAutoCommit(false);
            petugasDao.save(petugas);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return petugas;
    }
    public Petugas getPetugas(int id){
        try {
            return petugasDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Petugas getLogin(String nama, String password){
        try {
            return petugasDao.login(nama, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Buku save(Buku buku){
        try {
            //set id
            connection.setAutoCommit(false);
            int id = bukuDao.getIdByJudul(buku.getJudul());
            connection.commit();
            connection.setAutoCommit(true);
            if(id != 0){
                buku.setId(id);
                return buku;
            }
            connection.setAutoCommit(false);
            bukuDao.save(buku);
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return buku;
    }
    public Buku delete(Buku buku){
        try {
            connection.setAutoCommit(false);
            bukuDao.delete(buku);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return buku;
    }
    public Buku getBuku(int id){
        try {
            return bukuDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Buku> getAllBuku(){
        try {
            return bukuDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }    
    
    public Mahasiswa save(Mahasiswa mahasiswa){
        try {
            //set id
            connection.setAutoCommit(false);
            Long id = mahasiswaDao.getIdByName(mahasiswa.getNama());
            connection.commit();
            connection.setAutoCommit(true);
            if(id != null){
                mahasiswa.setNpm(id);
                return mahasiswa;
            }
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
//    public Mahasiswa update(Mahasiswa mahasiswa){
//        try{
//            connection.setAutoCommit(false);
//            mahasiswaDao.update(mahasiswa);
//            connection.commit();
//            connection.setAutoCommit(true);
//        } catch (SQLException ex){
//            try {
//                connection.rollback();
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//        return mahasiswa;
//    }
    public Mahasiswa delete(Mahasiswa mahasiswa){
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.delete(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa getMahasiswa(int id){
        try {
            return mahasiswaDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Mahasiswa> getAllMhs(){
        try {
            return mahasiswaDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Transaksi save(Transaksi transaksi){
        try {
            //set id
            connection.setAutoCommit(false);
            int id = transaksiDao.getIdByBuku(transaksi.getIdTrans());
            connection.commit();
            connection.setAutoCommit(true);
            if(id != 0){
                transaksi.setIdTrans(id);
                return transaksi;
            }

            connection.setAutoCommit(false);
            transaksiDao.save(transaksi);
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return transaksi;
    }
    public Transaksi delete(Transaksi transaksi){
        try {
            connection.setAutoCommit(false);
            transaksiDao.save(transaksi);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return transaksi;
    }
    public Transaksi getTransaksi(int id){
        try {
            return transaksiDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Transaksi> getAllTrans(){
        try {
            return transaksiDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}