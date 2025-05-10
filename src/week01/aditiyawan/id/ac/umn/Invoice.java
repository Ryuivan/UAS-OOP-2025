package week01.aditiyawan.id.ac.umn;

import week01.aditiyawan.id.ac.umn.interfaces.Printable;
import week01.aditiyawan.id.ac.umn.products.Item;

import java.time.LocalDate;

public class Invoice implements Printable {
    private Item item;
    private Integer quantity;
    private Double totalAmount;
    private LocalDate purchasedDate;

    public Invoice(Item item, Integer quantity, Double totalAmount, LocalDate purchasedDate) {
        this.item = item;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.purchasedDate = purchasedDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }


    @Override
    public String printDetails() {
        return String.format("""
                        Item Name: %s
                        Quantity: %d
                        Total Amount: %.2f
                        Purchased Date: %s""",
                getItem().getName(), getQuantity(), getTotalAmount(), getPurchasedDate());
    }
}
