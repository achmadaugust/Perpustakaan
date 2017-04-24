/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.Petugas;
import model.Buku;
import model.Mahasiswa;
import model.Transaksi;
import service.ServiceJdbc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author AT
 */
public class Main {

    public static void main(String[] args) {
        caraDao();
    }
    
    private static void caraDao(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("achmad");
        dataSource.setDatabaseName("PERPUSTAKAAN?serverTimezone=UTC");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);

        ServiceJdbc service = new ServiceJdbc();
        service.setDataSource(dataSource);
        Scanner in = new Scanner(System.in);
        
        Boolean loggedin = false;
        Petugas petugas = null;
        
        while (!loggedin) {
            System.out.println("Silakan login terlebih dahulu!\n");
            System.out.print("Username : ");
            String username = in.nextLine();
            System.out.print("Password : ");
            String password = in.nextLine();

            petugas = service.getLogin(username, password);

            if (petugas != null) {
                loggedin = true;
                System.out.println("\nSelamat datang, " + petugas.getNama() + "!");
                
                while (loggedin) {
                    getMainMenu();
                    String pilihan = in.nextLine();
                    switch (Integer.parseInt(pilihan)) {
                        case 1:
                            getPeminjamanMenu(service, petugas);
                            break;
                        case 2:
                            anggota(service);
                            break;
                        case 3:
                          getBukuMenu(service);
                            break;
                        case 4:
                            System.out.println("\nAnda telah berhasil keluar!\n");
                            petugas = null;
                            loggedin = false;
                            break;
                        default:
                            break;
                    }
                }
                
            } else {
                System.out.println("\nUsername atau password yang anda masukkan salah!\n");
            }
        }
        
        try {
            dataSource.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getMainMenu() {
        System.out.println("Menu Utama : \n");
        System.out.println("1. Transaksi");
        System.out.println("2. Anggota");
        System.out.println("3. Buku");
        System.out.println("4. Keluar");
        
        System.out.print("\nPilihan : ");
    }
    
    public static void getPeminjamanMenu(ServiceJdbc service, Petugas petugas) {
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while (active) {
            System.out.println("\nMenu Peminjaman : \n");
            System.out.println("1. Lihat Daftar Peminjaman");
            System.out.println("2. Peminjaman Baru");
            System.out.println("3. Pengembalian Buku");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                   List<Transaksi> peminjamanR = service.getAllTrans();
                    if (peminjamanR.isEmpty()) {
                        System.out.println("\nBelum ada peminjaman.");
                    } else {
                        System.out.println("\n#\t| NPM\t\t| Nama\t\t\t| Judul\t\t\t\t\t| Waktu Pinjam\t| Waktu Kembali\t| Waktu Dikembalikan");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                        for (Transaksi transaksi : peminjamanR) {
                            System.out.println(transaksi.getIdTrans() + "\t| "
                                    + transaksi.getNpm()+ "\t| " 
                                    + transaksi.getNama()+ "\t\t| " 
                                    + transaksi.getJudul()+ "\t\t| " 
                                    + transaksi.getDatePinjam()+"\t| " 
                                    + transaksi.getDateKembali() +"\t| "
                                    + transaksi.getDateDiKembalikan());
                        }
                    }
                    break;
                case "2":
                    System.out.print("NPM : ");
                    String npmt = in.nextLine();
                    System.out.print("Id Buku : ");
                    String idbukut = in.nextLine();
                    System.out.print("Tanggal pinjam : ");
                    String tp = in.nextLine();
                    System.out.print("Tanggal kembali : ");
                    String tk = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Transaksi t = new Transaksi();
                        t.setNpm(Integer.parseInt(npmt));
                        t.setIdBuku(Integer.parseInt(idbukut));
                        t.setDatePinjam(tp);
                        t.setDateKembali(tk);
                        service.save(t);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan ID Transaksi : ");
                    String id_trans_x = in.nextLine();
                    Transaksi t = service.getTransaksi(Integer.parseInt(id_trans_x));
                    if (id_trans_x == null) {
                        System.out.println("Tidak ditemukan buku dengan ID " + id_trans_x);
                        break;
                    }
                    System.out.print("Tanggal dikembalikan : ");
                    String tdk = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String ubah_x = in.nextLine();
                    if (ubah_x.toLowerCase().equals("y")) {
                        t.setDateDiKembalikan(tdk);
                        service.save(t);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    
    public static void anggota(ServiceJdbc service){
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while(active) {
            System.out.println("\nMenu Pengelolaan Anggota : \n");
            System.out.println("1. Lihat Daftar Anggota");
            System.out.println("2. Tambah Data Anggota");
            System.out.println("3. Ubah Data Anggota");
            System.out.println("4. Hapus Data Anggota");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<Mahasiswa> mahasiswas = (List<Mahasiswa>) service.getAllMhs();
                    if (mahasiswas.isEmpty()) {
                        System.out.println("\nBelum ada anggota yang terdaftar. Silakan tambahkan anggota terlebih dahulu!");
                    } else {
                        System.out.println("\nNPM\t| Nama\t\t\t| Tgl Lahir\t\t\t| Alamat ");
                        System.out.println("--------------------------------------------------------------------------------------");
                        for (Mahasiswa anggota : mahasiswas) {
                            System.out.println(anggota.getNpm()+"\t| "+anggota.getNama()+"\t\t| "+anggota.getDate()+"\t\t\t| "+anggota.getAlamat());
                        }                        
                    }
                    break;
                case "2":
                    System.out.print("Nama : ");                    
                    String nama = in.nextLine();
                    System.out.print("Tgl Lahir : ");
                    String date = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Mahasiswa mhs = new Mahasiswa();
                        mhs.setNama(nama);
                        mhs.setDate(date);
                        mhs.setAlamat(alamat);
                        service.save(mhs);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan NPM : ");
                    String npm_x = in.nextLine();
                    Mahasiswa mhs_x = service.getMahasiswa(Integer.parseInt(npm_x));
                    if (mhs_x == null) {
                        System.out.println("Tidak ditemukan mahasiswa dengan NPM " + npm_x);
                        break;
                    }
                    System.out.print("Nama : ");                    
                    String nama_x = in.nextLine();
                    System.out.print("Tgl Lahir : ");                    
                    String date_x = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah_x = in.nextLine();
                    if (tambah_x.toLowerCase().equals("y")) {
                        mhs_x.setNpm(Long.parseLong(npm_x));
                        mhs_x.setNama(nama_x);
                        mhs_x.setDate(date_x);
                        mhs_x.setAlamat(alamat_x);                        
                        service.save(mhs_x);
                    }
                    break;
                case "4":
                    System.out.print("Masukkan NPM : ");
                    String npm_d = in.nextLine();
                    Mahasiswa mhs_d = service.getMahasiswa(Integer.parseInt(npm_d));
                    if (mhs_d == null) {
                        System.out.println("Tidak ditemukan mahasiswa dengan NPM "+npm_d);
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(mhs_d);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        } 
    }
    
    public static void getBukuMenu(ServiceJdbc service) {
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while (active) {
            System.out.println("\nMenu Pengelolaan Buku : \n");
            System.out.println("1. Lihat Daftar Buku");
            System.out.println("2. Tambah Data Buku");
            System.out.println("3. Ubah Data Buku");
            System.out.println("4. Hapus Data Buku");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<Buku> bukus = (List<Buku>) service.getAllBuku();
                    if (bukus.isEmpty()) {
                        System.out.println("\nBelum ada buku yang terdaftar. Silakan tambahkan buku terlebih dahulu!");
                    } else {
                        System.out.println("\nID Buku\t| Judul\t\t\t\t\t| Pengarang\t\t\t| Kategori");
                        System.out.println("------------------------------------------------------------------------------------------");
                        for (Buku buku : bukus) {
                            System.out.println(buku.getId() + "\t| " + buku.getJudul() + "\t\t| " + buku.getPenulis() + "\t\t\t| " + buku.getKategori());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Judul : ");
                    String judul = in.nextLine();
                    System.out.print("Pengarang : ");
                    String pengarang = in.nextLine();
                    System.out.print("Kategori : ");
                    String kategori = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Buku buku = new Buku();
                        buku.setJudul(judul);
                        buku.setPenulis(pengarang);
                        buku.setKategori(kategori);
//                        buku.setTahun(Integer.parseInt(tahun));
                        service.save(buku);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan ID Buku : ");
                    String id_buku_x = in.nextLine();
                    Buku buku_x = service.getBuku(Integer.parseInt(id_buku_x));
                    if (buku_x == null) {
                        System.out.println("Tidak ditemukan buku dengan ID " + id_buku_x);
                        break;
                    }
                    System.out.print("Judul : ");
                    String judul_x = in.nextLine();
                    System.out.print("Pengarang : ");
                    String pengarang_x = in.nextLine();
                    System.out.print("Kategori : ");
                    String penerbit_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String ubah_x = in.nextLine();
                    if (ubah_x.toLowerCase().equals("y")) {
                        buku_x.setId(Integer.parseInt(id_buku_x));
                        buku_x.setJudul(judul_x);
                        buku_x.setPenulis(pengarang_x);
                        buku_x.setKategori(penerbit_x);
                        service.save(buku_x);
                    }
                    break;
                case "4":
                    System.out.print("Masukkan ID Buku : ");
                    String id_buku_d = in.nextLine();
                    Buku buku_d = service.getBuku(Integer.parseInt(id_buku_d));
                    if (buku_d == null) {
                        System.out.println("Tidak ditemukan buku dengan ID " + id_buku_d);
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(buku_d);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
}
