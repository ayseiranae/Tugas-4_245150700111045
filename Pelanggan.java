import java.util.*;

class Pelanggan {
    private String noPelanggan;
    private String nama;
    private String pin;
    private double saldo;
    private boolean diblokir = false;
    private int percobaanGagal = 0;

    public Pelanggan(String noPelanggan, String nama, String pin, double saldo) {
        this.noPelanggan = noPelanggan;
        this.nama = nama;
        this.pin = pin;
        this.saldo = saldo;
    }

    public String getNoPelanggan() {
        return noPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isDiblokir() {
        return diblokir;
    }

    public boolean autentikasi(String pinInput) {
        if (diblokir) return false;

        if (pin.equals(pinInput)) {
            percobaanGagal = 0;
            return true;
        } else {
            percobaanGagal++;
            System.out.println();
            Typography.center(Typography.RED + "PIN salah." + Typography.RESET);
            if (percobaanGagal >= 3) {
                diblokir = true;
                Typography.center(Typography.RED + "Akun telah diblokir karena 3x gagal autentikasi." + Typography.RESET);
            }
            return false;
        }
    }

    public void topUp(double jumlah) {
        if (!diblokir) {
            saldo += jumlah;
            Typography.center(Typography.GREEN + "Top up berhasil." + Typography.RESET);
            System.out.println("Saldo sekarang: Rp" + saldo);
        } else {
            Typography.center(Typography.RED + "Akun telah diblokir." + Typography.RESET);
        }
    }

    public void beli(double total) {
        if (diblokir) {
            Typography.center(Typography.RED + "Akun diblokir, tidak bisa transaksi." + Typography.RESET);
            return;
        }

        double cashback = hitungCashback(total);
        double totalPembayaran = total - cashback;

        if (saldo - totalPembayaran >= 10000) {
            saldo -= totalPembayaran;
            Typography.center(Typography.GREEN + "Pembelian berhasil!" + Typography.RESET);
            System.out.println("Cashback Rp" + Typography.RpF.IND.format(cashback));
            System.out.println("Saldo sekarang: Rp" + saldo);
        } else {
            Typography.center(Typography.RED + "Saldo tidak mencukupi untuk transaksi. Transaksi gagal." + Typography.RESET);
        }
    }

    private double hitungCashback(double total) {
        if (total < 1000000) return 0;

        String kode = noPelanggan.substring(0, 2);
        switch (kode) {
            case "38":
                return total * 0.05;
            case "56":
                return total * 0.07;
            case "74":
                return total * 0.10;
            default:
                return 0;
        }
    }
}