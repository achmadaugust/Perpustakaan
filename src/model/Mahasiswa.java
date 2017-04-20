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
public class Mahasiswa {
    
//    private int Id_mhs;
    private int npm;
    private String nama;
    private int date;
    private String alamat;

//    public Long getId_mhs() {
//        return Id_mhs;
//    }
//
//    public Long getId() {
//        return Id_mhs;
//    }
//
//    public void setId(Long Id_mhs) {
//        this.Id_mhs = Id_mhs;
//    }
//
//    public void setId_mhs(Long Id_mhs) {
//        this.Id_mhs = Id_mhs;
//    }

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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
}
