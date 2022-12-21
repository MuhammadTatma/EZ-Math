package com.example.ez_math.modhel;

public class StudentTrash {
    private static String email;
    private static int kelas;
    private static String namaLengkap;
    private static String namaPengguna;
    private static String namaSekolah;
    private static String profilImage;
    private static int totalLatihan;
    private static long totalXp;
    private static int totalBelajar;
    private static String uid;

    public StudentTrash() {
    }

    public StudentTrash(String email, int kelas, String namaLengkap, String namaPengguna, String namaSekolah,
                        String profilImage, int totalLatihan, long totalXp, int totalBelajar , String uid ){
        this.email = email;
        this.kelas = kelas;
        this.namaLengkap = namaLengkap;
        this.namaPengguna = namaPengguna;
        this.namaSekolah = namaSekolah;
        this.profilImage = profilImage;
        this.totalLatihan = totalLatihan;
        this.totalXp = totalXp;
        this.totalBelajar = totalBelajar;
        this.uid = uid;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        StudentTrash.email = email;
    }

    public static int getKelas() {
        return kelas;
    }

    public static void setKelas(int kelas) {
        StudentTrash.kelas = kelas;
    }

    public static String getNamaLengkap() {
        return namaLengkap;
    }

    public static void setNamaLengkap(String namaLengkap) {
        StudentTrash.namaLengkap = namaLengkap;
    }

    public static String getNamaPengguna() {
        return namaPengguna;
    }

    public static void setNamaPengguna(String namaPengguna) {
        StudentTrash.namaPengguna = namaPengguna;
    }

    public static String getNamaSekolah() {
        return namaSekolah;
    }

    public static void setNamaSekolah(String namaSekolah) {
        StudentTrash.namaSekolah = namaSekolah;
    }

    public static String getProfilImage() {
        return profilImage;
    }

    public static void setProfilImage(String profilImage) {
        StudentTrash.profilImage = profilImage;
    }

    public static int getTotalLatihan() {
        return totalLatihan;
    }

    public static void setTotalLatihan(int totalLatihan) {
        StudentTrash.totalLatihan = totalLatihan;
    }

    public static long getTotalXp() {
        return totalXp;
    }

    public static void setTotalXp(long totalXp) {
        StudentTrash.totalXp = totalXp;
    }

    public static int getTotalBelajar() {
        return totalBelajar;
    }

    public static void setTotalBelajar(int totalBelajar) {
        StudentTrash.totalBelajar = totalBelajar;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        StudentTrash.uid = uid;
    }
}
