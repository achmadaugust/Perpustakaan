/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import db.KoneksiBatch;
//import db.KoneksiPrepared;
//import db.KoneksiStatement;
//import db.KoneksiTransaction;
//import db.KoneksiDb;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        KoneksiPrepared kp = new KoneksiPrepared();
        
        caraDao();
        
    }
    
    private static void caraDao(){

        //buka koneksi
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("achmad");
        dataSource.setDatabaseName("PERPUSTAKAAN?serverTimezone=UTC");
        dataSource.setServerName("localhost");

        dataSource.setPortNumber(3306);

        //service db
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
//                            getPeminjamanMenu(service, petugas);
                            anggota(service);
                            break;
                        case 2:
//                            getPengembalianMenu(service, petugas);
                            break;
                        case 3:
//                            getHistoriMenu();
                            break;
                        case 4:
//                            getAnggotaMenu(service);
//                            break;
//                        case 5:
//                            getBukuMenu(service);
//                            break;
//                        case 6:
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
        System.out.println("1. Anggota");
        System.out.println("2. Buku");
        System.out.println("3. Transaksi");
//        System.out.println("4. Pengelolaan Anggota");
//        System.out.println("5. Pengelolaan Buku");
        System.out.println("4. Keluar");
        
        System.out.print("\nPilihan : ");
    }
    
//    public static void getPeminjamanMenu(ServiceJdbc service, Petugas petugas) {
//        Scanner in = new Scanner(System.in);
//        Boolean active = true;
//        while (active) {
//            System.out.println("\nMenu Peminjaman : \n");
//            System.out.println("1. Lihat Daftar Peminjaman");
//            System.out.println("2. Lihat Detail Peminjaman");
//            System.out.println("3. Peminjaman Baru");
//            System.out.println("4. Pengembalian Buku");
//            System.out.println("\n0. Kembali ke Menu Utama");
//
//            System.out.print("\nPilihan : ");
//            String pilih = in.nextLine();
//            switch (pilih) {
//                case "1":
//                   List<Peminjaman> peminjamanR = service.getAllPeminjaman();
//                    if (peminjamanR.isEmpty()) {
//                        System.out.println("\nBelum ada peminjaman.");
//                    } else {
//                        System.out.println("\nID Peminjaman\t| Nama Mahasiswa\t| Jumlah Buku\t\t\t| Waktu Pinjam \t\t| Waktu Kembali");
//                        System.out.println("-------------------------------------------------------------------------------------------------------------------");
//                        for (Peminjaman peminjaman : peminjamanR) {
//                            System.out.println(peminjaman.getId() + "\t\t| " + peminjaman.getNamamahasiswa()+ "\t\t| " + peminjaman.getJumlahbuku() + "\t\t\t| " + peminjaman.getWaktupinjam()+ "\t\t" + peminjaman.getWaktukembali());
//                        }
//                    }
//                    break;
//                case "2":
//                    System.out.print("Masukkan ID Peminjaman : ");
//                    String id_peminjaman = in.nextLine();
//                    Peminjaman peminjaman = service.getPeminjaman(Integer.parseInt(id_peminjaman));
//                    if (peminjaman == null) {
//                        System.out.println("\nTidak ada peminjaman dengan ID "+id_peminjaman);
//                    } else {
//                        System.out.print("\nID Peminjaman \t : ");
//                        System.out.println(peminjaman.getId());
//                        System.out.print("Nama Mahasiswa \t : ");
//                        System.out.println(peminjaman.getNamamahasiswa());
//                        System.out.print("Nama Petugas \t : ");
//                        System.out.println(peminjaman.getNamapetugas());
//                        System.out.print("Jumlah Buku \t : ");
//                        System.out.println(peminjaman.getJumlahbuku());
//                        List<DetailPeminjaman> detailR = service.getDetailPeminjamanByIdPeminjaman(peminjaman.getId());
//                        if (detailR.isEmpty()) {
//                            System.out.println("\nTidak ada buku yang dipinjam.");
//                        } else {
//                            System.out.println("\nID Buku\t| Judul Buku");
//                            System.out.println("------------------------------------------------");
//                            for (DetailPeminjaman detail : detailR) {
//                                System.out.println(detail.getId() + "\t| " + detail.getJudulbuku());
//                            }
//                        }
//                    }
//                    break;
//                case "3":
//                    Boolean inform = true;
//                    int npm = 0;
//                    String nama_mhs = "";
//                    List<Buku> buku_pinjamR = new ArrayList<>();
//                    int jumlahbuku = 0;
//                    
//                    while (inform) {
//                        jumlahbuku = buku_pinjamR.size();
//                        System.out.println("\nFORM PEMINJAMAN\n");
//                        System.out.println("Nama Mahasiswa : " + nama_mhs);
//                        System.out.println("Jumlah Buku    : " + jumlahbuku);
//                        
//                        System.out.println("\n1. Pilih Mahasiswa");
//                        System.out.println("2. Input Buku");
//                        System.out.println("3. Selesai");
//                        System.out.println("\n0. Batalkan");
//                        
//                        System.out.print("\nPilihan : ");
//                        String pilihan = in.nextLine();
//                        switch (Integer.parseInt(pilihan)) {
//                            case 1:
//                                System.out.print("\nMasukkan NPM : ");
//                                String npm_p = in.nextLine();
//                                Mahasiswa mhs_pinjam = service.getMahasiswa(Integer.parseInt(npm_p));
//                                if (mhs_pinjam == null) {
//                                    System.out.println("Tidak terdapat mahasiswa dengan NPM " + npm_p);
//                                    break;
//                                }
//                                npm = mhs_pinjam.getNpm();
//                                nama_mhs = mhs_pinjam.getNama();
//                                break;
//                            case 2:
//                                Boolean done = false;
//                                while (!done) {
//                                    System.out.println("\nID Buku\t| Judul Buku");
//                                    System.out.println("------------------------------------------------");
//                                    for (Buku buku : buku_pinjamR) {
//                                        System.out.println(buku.getId() + "\t| " + buku.getJudul());
//                                    }
//                                    System.out.println("\n1. Input Berdasarkan ID Buku");
//                                    System.out.println("2. Cari Berdasarkan Judul");
//                                    System.out.println("\n0. Kembali ke Form Peminjaman");
//                                    
//                                    System.out.print("\nPilihan : ");
//                                    String pilih_x = in.nextLine();
//                                    pilihbuku: switch (Integer.parseInt(pilih_x)) {
//                                        case 1:
//                                            System.out.print("\nMasukkan ID Buku : ");
//                                            String id_buku_p = in.nextLine();
//                                            Buku buku_pinjam = service.getBuku(Integer.parseInt(id_buku_p));
//                                            if (buku_pinjam == null) {
//                                                System.out.println("Tidak terdapat buku dengan ID " + id_buku_p);
//                                                break;
//                                            }
//                                            System.out.print("Tambahkan buku "+buku_pinjam.getJudul()+" ke daftar pinjam? (Y/N)");
//                                            String tambah_buku = in.nextLine();
//                                            if (tambah_buku.toLowerCase().equals("y")) {
//                                                for (Buku buku_a : buku_pinjamR) {
//                                                    if (buku_pinjam.getId() == buku_a.getId()) {
//                                                        System.out.println("Buku ini sudah ada di daftar pinjam!");
//                                                        break pilihbuku;
//                                                    }
//                                                }
//                                                buku_pinjamR.add(buku_pinjam);
//                                            }
//                                            break;
//                                        case 2:
//                                            System.out.print("\nMasukkan Kata Kunci Pencarian Judul Buku : ");
//                                            String cari_s = in.nextLine();
//                                            List<Buku> buku_pinjam_sR = service.getBukuBySearch(cari_s);
//                                            if (buku_pinjam_sR.isEmpty()) {
//                                                System.out.println("\nTidak ada buku dengan judul yang mirip");
//                                                break;
//                                            } else {
//                                                System.out.println("\nHasil Pencarian :");
//                                                for (Buku buku : buku_pinjam_sR) {
//                                                    System.out.println(buku.getId() + ". " + buku.getJudul());
//                                                }
//                                                
//                                                System.out.print("\nMasukkan ID Buku : ");
//                                                String id_buku_s = in.nextLine();
//                                                Buku buku_pinjam_s = service.getBuku(Integer.parseInt(id_buku_s));
//                                                if (buku_pinjam_s == null) {
//                                                    System.out.println("Tidak terdapat buku dengan ID " + id_buku_s);
//                                                    break;
//                                                }
//                                                System.out.print("Tambahkan buku ke daftar pinjam? (Y/N)");
//                                                String tambah_buku_s = in.nextLine();
//                                                if (tambah_buku_s.toLowerCase().equals("y")) {
//                                                    for (Buku buku_a : buku_pinjamR) {
//                                                        if (buku_pinjam_s.getId() == buku_a.getId()) {
//                                                            System.out.println("Buku ini sudah ada di daftar pinjam!");
//                                                            break pilihbuku;
//                                                        }
//                                                    }
//                                                    buku_pinjamR.add(buku_pinjam_s);
//                                                }
//                                            }
//                                            break;
//                                        case 0:
//                                            done = true;
//                                            break;
//                                        default:
//                                            break;
//                                    }
//                                }
//                                break;
//                            case 3:
//                                System.out.print("Selesai input form peminjaman? (Y/N) : ");
//                                String selesaiform = in.nextLine();
//                                if (!selesaiform.toLowerCase().equals("y")) {
//                                    
//                                }
//                                break;
//                            case 0:
//                                inform = false;
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    break;
//                case "4":
//                    System.out.print("Masukkan ID Buku : ");
//                    String id_buku_x = in.nextLine();
//                    Buku buku_x = service.getBuku(Integer.parseInt(id_buku_x));
//                    if (buku_x == null) {
//                        System.out.println("Tidak ditemukan buku dengan ID " + id_buku_x);
//                        break;
//                    }
//                    System.out.print("Judul : ");
//                    String judul_x = in.nextLine();
//                    System.out.print("Pengarang : ");
//                    String pengarang_x = in.nextLine();
//                    System.out.print("Penerbit : ");
//                    String penerbit_x = in.nextLine();
//                    System.out.print("Tahun : ");
//                    String tahun_x = in.nextLine();
//                    System.out.print("Simpan? (Y/N) : ");
//                    String ubah_x = in.nextLine();
//                    if (ubah_x.toLowerCase().equals("y")) {
//                        buku_x.setId(Integer.parseInt(id_buku_x));
//                        buku_x.setJudul(judul_x);
//                        buku_x.setPengarang(pengarang_x);
//                        buku_x.setPenerbit(penerbit_x);
//                        buku_x.setTahun(Integer.parseInt(tahun_x));
//                        service.save(buku_x);
//                    }
//                    break;
//                case "5":
//                    System.out.print("Masukkan ID Buku : ");
//                    String id_buku_d = in.nextLine();
//                    Buku buku_d = service.getBuku(Integer.parseInt(id_buku_d));
//                    if (buku_d == null) {
//                        System.out.println("Tidak ditemukan buku dengan ID " + id_buku_d);
//                        break;
//                    }
//                    System.out.print("Hapus? (Y/N) : ");
//                    String hapus = in.nextLine();
//                    if (hapus.toLowerCase().equals("y")) {
//                        service.delete(buku_d);
//                    }
//                    break;
//                case "0":
//                    active = false;
//                    break;
//            }
//        }
//    }
    
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
                    List<Mahasiswa> mahasiswas = (List<Mahasiswa>) service.getAll();
                    if (mahasiswas.isEmpty()) {
                        System.out.println("\nBelum ada anggota yang terdaftar. Silakan tambahkan anggota terlebih dahulu!");
                    } else {
                        System.out.println("\nNPM\t| Nama\t\t\t| Alamat\t\t\t| No. HP");
                        System.out.println("--------------------------------------------------------------------------------------");
                        for (Mahasiswa anggota : mahasiswas) {
                            System.out.println(anggota.getNpm()+"\t| "+anggota.getNama()+"\t\t| "+anggota.getDate()+"\t\t\t| "+anggota.getAlamat());
                        }                        
                    }
                    break;
                case "2":
//                    System.out.print("NPM : ");
//                    int npm = in.nextInt();
                    System.out.print("Nama : ");                    
                    String nama = in.nextLine();
                    System.out.print("Tgl Lahir : ");
                    String date = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat = in.nextLine();
                    System.out.print("NPM(kosongkan bila data baru) : ");
                    String npm = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Mahasiswa mhs = new Mahasiswa();
//                        
                        mhs.setNama(nama);
                        mhs.setDate(Integer.parseInt(date));
                        mhs.setAlamat(alamat);
//                        mhs.setNpm(Integer.parseInt(npm));
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
//                    System.out.print("No. HP : ");
//                    String nohp_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah_x = in.nextLine();
                    if (tambah_x.toLowerCase().equals("y")) {
                        mhs_x.setNpm(Integer.parseInt(npm_x));
                        mhs_x.setNama(nama_x);
                        mhs_x.setDate(Integer.parseInt(date_x));
                        mhs_x.setAlamat(alamat_x);
//                        mhs_x.setNohp(nohp_x);                        
//                        service.update(mhs_x);
                        
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
    
//    public static void getPengembalianMenu(ServiceJdbc service, Petugas petugas) {
//        System.out.println("Menu Peminjaman Buku : \n");
//        System.out.println("0. Kembali ke Menu Utama");
//        
//        System.out.print("\nPilihan : ");
//    }
//    
//    public static void getHistoriMenu() {
//        System.out.println("Menu Histori Peminjaman : \n");
//        System.out.println("0. Kembali ke Menu Utama");
//
//        System.out.print("\nPilihan : ");
//    }
//    
//    public static void getAnggotaMenu(ServiceJdbc service) {
//        Scanner in = new Scanner(System.in);
//        Boolean active = true;
//        while(active) {
//            System.out.println("\nMenu Pengelolaan Anggota : \n");
//            System.out.println("1. Lihat Daftar Anggota");
//            System.out.println("2. Tambah Data Anggota");
//            System.out.println("3. Ubah Data Anggota");
//            System.out.println("4. Hapus Data Anggota");
//            System.out.println("\n0. Kembali ke Menu Utama");
//
//            System.out.print("\nPilihan : ");
//            String pilih = in.nextLine();
//            switch (pilih) {
//                case "1":
//                    List<Mahasiswa> anggotaR = service.getAllMahasiswa();
//                    if (anggotaR.isEmpty()) {
//                        System.out.println("\nBelum ada anggota yang terdaftar. Silakan tambahkan anggota terlebih dahulu!");
//                    } else {
//                        System.out.println("\nNPM\t| Nama\t\t\t| Alamat\t\t\t| No. HP");
//                        System.out.println("--------------------------------------------------------------------------------------");
//                        for (Mahasiswa anggota : anggotaR) {
//                            System.out.println(anggota.getNpm()+"\t| "+anggota.getNama()+"\t\t| "+anggota.getAlamat()+"\t\t\t| "+anggota.getNohp());
//                        }                        
//                    }
//                    break;
//                case "2":
//                    System.out.print("NPM : ");
//                    String npm = in.nextLine();
//                    System.out.print("Nama : ");                    
//                    String nama = in.nextLine();
//                    System.out.print("Alamat : ");
//                    String alamat = in.nextLine();
//                    System.out.print("No. HP : ");
//                    String nohp = in.nextLine();
//                    System.out.print("Simpan? (Y/N) : ");
//                    String tambah = in.nextLine();
//                    if (tambah.toLowerCase().equals("y")) {
//                        Mahasiswa mhs = new Mahasiswa();
//                        mhs.setNpm(Integer.parseInt(npm));
//                        mhs.setNama(nama);
//                        mhs.setAlamat(alamat);
//                        mhs.setNohp(nohp);
//                        service.save(mhs);
//                    }
//                    break;
//                case "3":
//                    System.out.print("Masukkan NPM : ");
//                    String npm_x = in.nextLine();
//                    Mahasiswa mhs_x = service.getMahasiswa(Integer.parseInt(npm_x));
//                    if (mhs_x == null) {
//                        System.out.println("Tidak ditemukan mahasiswa dengan NPM " + npm_x);
//                        break;
//                    }
//                    System.out.print("Nama : ");                    
//                    String nama_x = in.nextLine();
//                    System.out.print("Alamat : ");
//                    String alamat_x = in.nextLine();
//                    System.out.print("No. HP : ");
//                    String nohp_x = in.nextLine();
//                    System.out.print("Simpan? (Y/N) : ");
//                    String tambah_x = in.nextLine();
//                    if (tambah_x.toLowerCase().equals("y")) {
//                        mhs_x.setNpm(Integer.parseInt(npm_x));
//                        mhs_x.setNama(nama_x);
//                        mhs_x.setAlamat(alamat_x);
//                        mhs_x.setNohp(nohp_x);
//                        service.update(mhs_x);
//                    }
//                    break;
//                case "4":
//                    System.out.print("Masukkan NPM : ");
//                    String npm_d = in.nextLine();
//                    Mahasiswa mhs_d = service.getMahasiswa(Integer.parseInt(npm_d));
//                    if (mhs_d == null) {
//                        System.out.println("Tidak ditemukan mahasiswa dengan NPM "+npm_d);
//                        break;
//                    }
//                    System.out.print("Hapus? (Y/N) : ");
//                    String hapus = in.nextLine();
//                    if (hapus.toLowerCase().equals("y")) {
//                        service.delete(mhs_d);
//                    }
//                    break;
//                case "0":
//                    active = false;
//                    break;
//            }
//        }
//    }
//    
//    public static void getBukuMenu(ServiceJdbc service) {
//        Scanner in = new Scanner(System.in);
//        Boolean active = true;
//        while (active) {
//            System.out.println("\nMenu Pengelolaan Buku : \n");
//            System.out.println("1. Lihat Daftar Buku");
//            System.out.println("2. Tambah Data Buku");
//            System.out.println("3. Ubah Data Buku");
//            System.out.println("4. Hapus Data Buku");
//            System.out.println("\n0. Kembali ke Menu Utama");
//
//            System.out.print("\nPilihan : ");
//            String pilih = in.nextLine();
//            switch (pilih) {
//                case "1":
//                    List<Buku> bukuR = service.getAllBuku();
//                    if (bukuR.isEmpty()) {
//                        System.out.println("\nBelum ada buku yang terdaftar. Silakan tambahkan buku terlebih dahulu!");
//                    } else {
//                        System.out.println("\nID Buku\t| Judul\t\t\t| Pengarang\t\t\t| Penerbit \t\t| Tahun");
//                        System.out.println("--------------------------------------------------------------------------------------");
//                        for (Buku buku : bukuR) {
//                            System.out.println(buku.getId() + "\t| " + buku.getJudul() + "\t\t| " + buku.getPengarang() + "\t\t\t| " + buku.getPenerbit() + "\t\t" + buku.getTahun());
//                        }
//                    }
//                    break;
//                case "2":
//                    System.out.print("Judul : ");
//                    String judul = in.nextLine();
//                    System.out.print("Pengarang : ");
//                    String pengarang = in.nextLine();
//                    System.out.print("Penerbit : ");
//                    String penerbit = in.nextLine();
//                    System.out.print("Tahun : ");
//                    String tahun = in.nextLine();
//                    System.out.print("Simpan? (Y/N) : ");
//                    String tambah = in.nextLine();
//                    if (tambah.toLowerCase().equals("y")) {
//                        Buku buku = new Buku();
//                        buku.setJudul(judul);
//                        buku.setPengarang(pengarang);
//                        buku.setPenerbit(penerbit);
//                        buku.setTahun(Integer.parseInt(tahun));
//                        service.save(buku);
//                    }
//                    break;
//                case "3":
//                    System.out.print("Masukkan ID Buku : ");
//                    String id_buku_x = in.nextLine();
//                    Buku buku_x = service.getBuku(Integer.parseInt(id_buku_x));
//                    if (buku_x == null) {
//                        System.out.println("Tidak ditemukan buku dengan ID " + id_buku_x);
//                        break;
//                    }
//                    System.out.print("Judul : ");
//                    String judul_x = in.nextLine();
//                    System.out.print("Pengarang : ");
//                    String pengarang_x = in.nextLine();
//                    System.out.print("Penerbit : ");
//                    String penerbit_x = in.nextLine();
//                    System.out.print("Tahun : ");
//                    String tahun_x = in.nextLine();
//                    System.out.print("Simpan? (Y/N) : ");
//                    String ubah_x = in.nextLine();
//                    if (ubah_x.toLowerCase().equals("y")) {
//                        buku_x.setId(Integer.parseInt(id_buku_x));
//                        buku_x.setJudul(judul_x);
//                        buku_x.setPengarang(pengarang_x);
//                        buku_x.setPenerbit(penerbit_x);
//                        buku_x.setTahun(Integer.parseInt(tahun_x));
//                        service.save(buku_x);
//                    }
//                    break;
//                case "4":
//                    System.out.print("Masukkan ID Buku : ");
//                    String id_buku_d = in.nextLine();
//                    Buku buku_d = service.getBuku(Integer.parseInt(id_buku_d));
//                    if (buku_d == null) {
//                        System.out.println("Tidak ditemukan buku dengan ID " + id_buku_d);
//                        break;
//                    }
//                    System.out.print("Hapus? (Y/N) : ");
//                    String hapus = in.nextLine();
//                    if (hapus.toLowerCase().equals("y")) {
//                        service.delete(buku_d);
//                    }
//                    break;
//                case "0":
//                    active = false;
//                    break;
//            }
//        }
//    }
//        Petugas petugas = new Petugas();
//        petugas.setNama("mradministrator");
//        petugas.setPassword("mrsuperadmin");
//        petugas= service.save(petugas);
        
//        Mahasiswa mahasiswa = new Mahasiswa();
//        mahasiswa.setNpm(1042021);
//        mahasiswa.setNama("Achmad Try August");
//        mahasiswa.setDate(19970803);
//        mahasiswa.setAlamat("Bandung");
//        mahasiswa= service.save(mahasiswa);

//        long ID = 2;
//        petugas.setId(ID);
//        petugas= service.delete(petugas);

//        System.out.println("id : " + petugas.getId());
//        System.out.println("name: " + petugas.getNama());
//
//        System.out.println("id : " + mahasiswa.getNpm());
//        System.out.println("nama : " + mahasiswa.getNama());

//        try {
//            dataSource.getConnection().close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
}
