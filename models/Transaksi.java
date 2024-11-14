package models;

import java.util.ArrayList;

public class Transaksi {
    private Customer customer;
    private ArrayList<Barang> barang;
    private boolean isApproved;

    public Transaksi(Customer customer, ArrayList<Barang> barang) {
        this.customer = customer;
        this.barang = barang;
        this.isApproved = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void approve() {
        this.isApproved = true;
    }
}

