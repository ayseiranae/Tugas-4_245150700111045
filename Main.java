import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Pelanggan> database = new HashMap<>();

        database.put("3812345678", new Pelanggan("3812345678", "Amba", "1234", 1500000));
        database.put("5612345678", new Pelanggan("5612345678", "Azril", "5678", 2000000));
        database.put("7412345678", new Pelanggan("7412345678", "Andre", "9999", 3000000));

        while (true) {
            System.out.println("____________________________________________________________");
            Typography.center("MENU TRANSAKSI");
            System.out.println();
            System.out.print("Masukkan Nomor Pelanggan: ");
            String no = sc.nextLine();

            Pelanggan pl = database.get(no);
            if (pl == null) {
                Typography.center(Typography.RED + "Pelanggan tidak ditemukan." + Typography.RESET);
                continue;
            }

            if (pl.isDiblokir()) {
                Typography.center(Typography.RED + "Akun diblokir." + Typography.RESET);
                continue;
            }

            System.out.print("Masukkan PIN: ");
            String pin = sc.nextLine();

            pl.autentikasi(pin);
            if (!pl.autentikasi(pin)) { continue; }

            System.out.println("Selamat datang, " + pl.getNama());
            System.out.println("Saldo Anda: Rp" + Typography.RpF.IND.format(pl.getSaldo()));
            System.out.println("1. Top Up");
            System.out.println("2. Pembelian");
            System.out.print("Pilih menu: ");
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1:
                    System.out.print("Masukkan jumlah top up: ");
                    double topup = Double.parseDouble(sc.nextLine());
                    pl.topUp(topup);
                    break;
                case 2:
                    System.out.print("Masukkan total belanja: ");
                    double total = Double.parseDouble(sc.nextLine());
                    pl.beli(total);
                    break;
                default:
                    Typography.center(Typography.RED+ "Menu tidak tersedia." + Typography.RESET);
            }
        }
    }
}
