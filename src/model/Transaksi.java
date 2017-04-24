/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AT
 */
public class Transaksi {
    
    private int idTrans;
    private int npm;
    private String nama;
    private int IdBuku;
    private String judul;
    private String datePinjam;
    private String dateKembali;
    private String dateDiKembalikan;

    public int getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(int idTrans) {
        this.idTrans = idTrans;
    }

    public int getNpm() {
        return npm;
    }

    public void setNpm(int npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIdBuku() {
        return IdBuku;
    }

    public void setIdBuku(int IdBuku) {
        this.IdBuku = IdBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDatePinjam() {
        return datePinjam;
    }

    public void setDatePinjam(String datePinjam) {
        this.datePinjam = datePinjam;
    }

    public String getDateKembali() {
        return dateKembali;
    }

    public void setDateKembali(String dateKembali) {
        this.dateKembali = dateKembali;
    }

    public String getDateDiKembalikan() {
        return dateDiKembalikan;
    }

    public void setDateDiKembalikan(String dateDiKembalikan) {
        this.dateDiKembalikan = dateDiKembalikan;
    }
    
}
