import java.util.ArrayList;
import java.util.Scanner;

public class JavaApplication1 {

    public static void main(String[] args) {
        ArrayList<Pesan> p = new ArrayList();
        Scanner sc = new Scanner(System.in);
        Integer pilihan = 0;

        do {
            /*
             * jika pilih 1, maka input data,
             * jika 2, maka tampilkan data
             * jika 3 maka keluar sistem
             */

            System.out.println("----------------------------");
            System.out.println("--- BINTANG BUCK COFFEE ----");
            System.out.println("----------------------------");
            System.out.println("  1. Pembelian");
            System.out.println("  2. Bayar");
            System.out.println("  3. Keluar sistem");
            System.out.println("----------------------------");
            System.out.print("  Pilihanmu: ");
            pilihan = sc.nextInt();

            if (pilihan == 1) {
                p = beli(p);
            } else if (pilihan == 2) {
                p = bayar(p);
            }
        } while (pilihan != 3);

    }

    private static ArrayList<Pesan> beli(ArrayList<Pesan> p) {
        Scanner sc = new Scanner(System.in);
        String nama, tipe, gula;
        Integer harga, qty;
        Boolean cocok;

        do {

            System.out.print("Nama: ");
            nama = sc.nextLine();
            cocok = cekMenu(nama);

        } while (cocok == false);

        System.out.print("Tipe: ");
        tipe = sc.nextLine();

        System.out.print("Gula: ");
        gula = sc.nextLine();

        System.out.print("Harga: ");
        harga = sc.nextInt();

        do {
            System.out.print("Qty: ");
            qty = sc.nextInt();
            cocok = cekQTY(qty);
        } while (cocok == false);

        p.add(new Pesan(nama, tipe, gula, harga, qty));

        return p;
    }

    private static Boolean cekQTY(Integer qty) {
        boolean cocok = false;
        int min = 1;
        if (qty < min) {
            System.out.println("Minimal Order : 1");
        } else {
            cocok = true;
        }
        return cocok;

    }

    private static Boolean cekMenu(String nama) {
        boolean cocok = false;
        if (nama.equalsIgnoreCase("Americano") || nama.equalsIgnoreCase("Latte") || nama.equalsIgnoreCase("Arabica")) {
            cocok = true;
        } else {
            System.out.println("Menu yang tersedia adalah : Americano, Latte, Arabica");
        }
        return cocok;
    }

    private static Boolean cekBayar(Integer total, Integer bayar) {
        boolean cocok = false;
        if (bayar >= total) {
            cocok = true;
        } else {
            System.out.println("Pembayaran Anda Kurang");
        }
        return cocok;
    }

    private static ArrayList<Pesan> bayar(ArrayList<Pesan> p) {
        Scanner sc = new Scanner(System.in);
        String nama, tipe, gula;
        Integer harga, qty, total, jumlah, bayar;
        Boolean ok;
        total = 0;
        jumlah = 0;

        // tampilkan data
        System.out.println("Jumlah data: " + p.size()); // ini error
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-3s | %-10s | %-10s | %-5s | %-7s | %-3s  | %-7s |",
                "No",
                "Nama",
                "Tipe",
                "Gula",
                "Harga",
                "Qty",
                "Jumlah");
        System.out.println();
        System.out.println("--------------------------------------------------------------------");

        for (int i = 0; i < p.size(); i++) {
            jumlah = p.get(i).getQty() * p.get(i).getHarga();
            System.out.printf("| %-3s | %-10s | %-10s | %-5s | %-7s | %-3s  | %-7s |",
                    i + 1,
                    p.get(i).getNama(),
                    p.get(i).getTipe(),
                    p.get(i).getGula(),
                    p.get(i).getHarga(),
                    p.get(i).getQty(),
                    (p.get(i).getHarga() * p.get(i).getQty()));
            total += jumlah;
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Total Bayar: " + total);

        do {
            System.out.print("Bayar: ");
            bayar = sc.nextInt();
            ok = cekBayar(total, bayar);
        } while (ok == false);

        p.clear();
        System.out.println();
        System.out.println("----- Terimakasih Telah Berbelanja di Coffee Shop Kami -----");
        System.out.println("--------------------------------------------------------------------");

        System.out.println("Tekan enter untuk lanjut...");
        sc.nextLine();

        return p;
    }

}