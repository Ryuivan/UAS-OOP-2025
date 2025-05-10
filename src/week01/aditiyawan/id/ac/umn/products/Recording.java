package week01.aditiyawan.id.ac.umn.products;

public class Recording extends Item {
    private Integer durationInMinutes;

    public Recording(Integer id, String name, Double price, Integer stock, String category, Integer durationInMinutes) {
        super(id, name, price, stock, category);
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public String printDetails() {
        return String.format(
                """
                        Type        : Recording
                        ID:         : %d
                        Name        : %s
                        Price       : Rp. %.2f
                        Stock       : %d
                        Category    : %s
                        Duration    : %d minutes""",
                getId(), getName(), getPrice(), getStock(), getCategory(), getDurationInMinutes()
        );
    }
}
