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
    private Long npm;
    private String nama;
    private String date;
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

    public Long getNpm() {
        return npm;
    }

    public void setNpm(Long npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
}
