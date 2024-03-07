package sample;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, name = "dish")
    private String name;
    @Column(nullable = false)
    private int weight;
    @Column(nullable = false)
    private int cost;
    @Column(nullable = true)
    private Integer discount;

    public Dish() {
    }

    public Dish(String name, int weight, int cost, int discount) {
        if (weight <= 0 || cost <= 0 || discount < 0 || discount > 100)
            throw new IllegalArgumentException();
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        if (discount == 0) {
            this.discount = null;
        } else {
            this.discount = discount;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", discount=" + discount + "%" +
                '}';
    }
}
