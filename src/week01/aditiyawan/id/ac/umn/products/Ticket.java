package week01.aditiyawan.id.ac.umn.products;

import java.time.LocalDate;

public class Ticket extends Item {
    private LocalDate validUntil;

    // Constructor dengan menambahkan parameter validUntil
    public Ticket(Integer id, String name, Double price, Integer stock, String category, LocalDate validUntil) {
        super(id, name, price, stock, category);
        this.validUntil = validUntil;
    }

    // Getter dan Setter untuk validUntil
    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public double countDiscount(Integer quantity) {
        double discount = super.countDiscount(quantity);

        if (LocalDate.now().isAfter(validUntil)) {
            System.out.println("Tiket telah kadaluarsa!");
            return 0;
        }

        return discount;
    }

    @Override
    public String printDetails() {
        return String.format(
                """
                        Type        : Ticket
                        ID          : %d
                        Name        : %s
                        Price       : Rp. %.2f
                        Stock       : %d
                        Category    : %s
                        Valid Until : %s""",
                getId(), getName(), getPrice(), getStock(), getCategory(), getValidUntil()
        );
    }

}
