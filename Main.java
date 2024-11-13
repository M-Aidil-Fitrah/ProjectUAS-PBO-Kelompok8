import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Contoh login sebagai Admin
        Admin admin = new Admin("admin123");
        AdminDriver adminDriver = new AdminDriver(admin);
        adminDriver.login();

        // Contoh login sebagai Customer
        Customer customer = new Customer("cust123");
        CustomerDriver customerDriver = new CustomerDriver(customer);
        customerDriver.login();

        // Contoh interaksi
        Barang barang1 = new Barang("001", "Laptop", 15000000, 5);
        Barang barang2 = new Barang("002", "Mouse", 150000, 10);

        adminDriver.getListBarang().tambahBarang(barang1);
        adminDriver.getListBarang().tambahBarang(barang2);

        customerDriver.setListBarang(adminDriver.getListBarang());
        customerDriver.tambahBarangKeKeranjang(barang1);
        customerDriver.checkout();

        scanner.close();
    }
}
