package week01.aditiyawan.id.ac.umn.products;

import week01.aditiyawan.id.ac.umn.interfaces.Discountable;
import week01.aditiyawan.id.ac.umn.interfaces.Printable;

import java.time.LocalDate;
import java.util.Random;

public abstract class Item implements Discountable, Printable {
    protected Integer id;
    protected String name;
    protected Double price;
    protected Integer stock;
    protected String category;

    public Item(Integer id, String name, Double price, Integer stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void minusStock(Integer stock) {
        this.stock -= stock;
    }

    public void addStock(Integer stock) {
        this.stock += stock;
    }

    @Override
    public double countDiscount(Integer quantity) {
        if (quantity > 3) {
            return 0.1;
        }

        LocalDate now = LocalDate.now();

        if (now.getDayOfMonth() == now.getMonthValue()) {
            Random random = new Random();

            return random.nextDouble();
        }

        return 0;
    }

    @Override
    public String printDetails() {
        return String.format("Ticket: %s | ID: %d | Price: %.2f | Stock: %d | Category: %s",
                getName(), getId(), getPrice(), getStock(), getCategory());
    }
}
