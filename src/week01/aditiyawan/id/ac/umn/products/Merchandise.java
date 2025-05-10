package week01.aditiyawan.id.ac.umn.products;

public class Merchandise extends Item {
    private String type;

    public Merchandise(Integer id, String name, Double price, Integer stock, String category, String type) {
        super(id, name, price, stock, category);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String printDetails() {
        return String.format(
                """
                        Type        : Merchandise
                        ID          : %d
                        Name        : %s
                        Price       : Rp. %.2f
                        Stock       : %d
                        Category    : %s
                        Type Detail : %s""",
                getId(), getName(), getPrice(), getStock(), getCategory(), getType()
        );
    }

}
