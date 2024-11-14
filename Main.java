import models.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Akun> akunList;
    private Driver driverAkun;
    private ListBarang listBarang;

    public Main() {
        akunList = new ArrayList<>();
        listBarang = new ListBarang();
        loadAkun();
        loadBarang();
    }

    // Memuat akun dari file
    private void loadAkun() {
    try (BufferedReader br = new BufferedReader(new FileReader("akun.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String role = parts[0];
                String id = parts[1];
                String username = parts[2];
                String password = parts[3];
                
                if ("Admin".equals(role)) {
                    akunList.add(new Admin(id, username, password));
                } else if ("Customer".equals(role)) {
                    akunList.add(new Customer(id, username, password));
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error saat membaca akun: " + e.getMessage());
    }
}

    // Menyimpan akun ke file
    private void saveAkun() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("akun.txt", false))) { // mode overwrite
            for (Akun akun : akunList) {
                String role = akun instanceof Admin ? "Admin" : "Customer";
                bw.write(role + "," + akun.getId() + "," + akun.getUsername() + "," + akun.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saat menyimpan akun: " + e.getMessage());
        }
    }

    // Mendaftarkan akun baru
    public void register(Scanner scanner) {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Apakah Anda ingin mendaftar sebagai Admin? (y/n): ");
        String roleChoice = scanner.nextLine();

        Akun newAkun;
        if (roleChoice.equalsIgnoreCase("y")) {
            newAkun = new Admin(id, username, password);
            System.out.println("Akun Admin berhasil dibuat.");
        } else {
            newAkun = new Customer(id, username, password);
            System.out.println("Akun Customer berhasil dibuat.");
        }

        akunList.add(newAkun);
        saveAkun();  // Simpan ke file akun.txt
        
    }

    // Memuat barang dari file
    private void loadBarang() {
        try (BufferedReader br = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nama = parts[0];
                    double harga = Double.parseDouble(parts[1]);
                    listBarang.tambahBarang(new Barang(nama, harga));
                }
            }
        } catch (IOException e) {
            System.out.println("Error saat membaca barang: " + e.getMessage());
        }
    }

    // Menyimpan barang ke file
    private void saveBarang() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("barang.txt", true))) { // mode append
            for (Barang barang : listBarang.getBarangList()) {
                int hargaInt = (int) barang.getHarga();  
                bw.write(barang.getNama() + "," + hargaInt); 
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saat menyimpan barang: " + e.getMessage());
        }
    }

public void login() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Username: ");
    String username = scanner.nextLine();
    System.out.print("Password: ");
    String password = scanner.nextLine();

    // Mencari akun berdasarkan username dan password
    Akun loggedInAkun = null;
    for (Akun akun : akunList) {
        if (akun.getUsername().equals(username) && akun.getPassword().equals(password)) {
            loggedInAkun = akun;
            break;
        }
    }

    // Verifikasi login
    if (loggedInAkun == null) {
        System.out.println("Username atau password salah.");
    } else {
        // Inisialisasi driver berdasarkan tipe akun
        if (loggedInAkun instanceof Admin) {
            driverAkun = new AdminDriver((Admin) loggedInAkun, listBarang);
            System.out.println("Login berhasil sebagai Admin.");
            adminMenu(scanner);
        } else if (loggedInAkun instanceof Customer) {
            driverAkun = new CustomerDriver((Customer) loggedInAkun, listBarang);
            System.out.println("Login berhasil sebagai Customer.");
            customerMenu((CustomerDriver) driverAkun, scanner);
        }
    }
}

    // Menu untuk Admin
    private void adminMenu(Scanner scanner) {
        AdminDriver adminDriver = (AdminDriver) driverAkun;
        while (true) {
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline
    
            if (choice == 1) {
                System.out.print("Nama Barang: ");
                String nama = scanner.nextLine();
                System.out.print("Harga Barang: ");
                double harga = scanner.nextDouble();
                scanner.nextLine();  // consume newline
                adminDriver.tambahBarang(new Barang(nama, harga));
                saveBarang();  // Simpan perubahan ke barang.txt
                System.out.println("Barang berhasil ditambahkan.");
            } else if (choice == 2) {
                System.out.print("Nama Barang yang akan dihapus: ");
                String nama = scanner.nextLine();
                Barang barangDihapus = listBarang.cariBarang(nama);
                if (barangDihapus != null) {
                    adminDriver.hapusBarang(barangDihapus);
                    saveBarang();  // Simpan perubahan ke barang.txt
                    System.out.println("Barang berhasil dihapus.");
                } else {
                    System.out.println("Barang tidak ditemukan.");
                }
            } else if (choice == 3) {
                System.out.println("Keluar dari sistem Admin.");
                break;
            }
        }
    }
    
    // Menu untuk Customer
    private void customerMenu(CustomerDriver customerDriver, Scanner scanner) {
        while (true) {
            System.out.println("1. Lihat Daftar Barang");
            System.out.println("2. Tambah Barang ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (choice == 1) {
                listBarang.tampilkanBarang();
            } else if (choice == 2) {
                System.out.print("Nama Barang yang ingin ditambahkan: ");
                String nama = scanner.nextLine();
                Barang barang = listBarang.cariBarang(nama);
                if (barang != null) {
                    customerDriver.tambahKeKeranjang(barang);
                    System.out.println("Barang berhasil ditambahkan ke keranjang.");
                } else {
                    System.out.println("Barang tidak ditemukan.");
                }
            } else if (choice == 3) {
                customerDriver.checkout();
            } else if (choice == 4) {
                System.out.println("Keluar dari sistem Customer.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Online Shop!");

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Daftar Akun Baru");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                app.login();
            } else if (choice == 2) {
                app.register(scanner);  // Memanggil register dengan scanner
            } else if (choice == 3) {
                System.out.println("Terima kasih! Sampai jumpa.");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
