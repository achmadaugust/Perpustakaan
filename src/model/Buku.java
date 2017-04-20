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
public class Buku {
    
    private int IdBuku;
    private String judul;
//    private String ISBN;
    private String penulis;
    private String kategori;

    public int getId() {
        return IdBuku;
    }

    public void setId(int Id) {
        this.IdBuku = Id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

//    public String getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(String ISBN) {
//        this.ISBN = ISBN;
//    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
}
