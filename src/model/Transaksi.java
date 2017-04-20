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
    private int IdBuku;
    private int datePinjam;
    private int dateKembali;
    private int dateDiKembalikan;

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

    public int getIdBuku() {
        return IdBuku;
    }

    public void setIdBuku(int IdBuku) {
        this.IdBuku = IdBuku;
    }

    public int getDatePinjam() {
        return datePinjam;
    }

    public void setDatePinjam(int datePinjam) {
        this.datePinjam = datePinjam;
    }

    public int getDateKembali() {
        return dateKembali;
    }

    public void setDateKembali(int dateKembali) {
        this.dateKembali = dateKembali;
    }

    public int getDateDiKembalikan() {
        return dateDiKembalikan;
    }

    public void setDateDiKembalikan(int dateDiKembalikan) {
        this.dateDiKembalikan = dateDiKembalikan;
    }
    
}
