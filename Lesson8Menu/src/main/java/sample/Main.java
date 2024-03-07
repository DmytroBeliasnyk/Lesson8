package sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("JPA");
        em = emf.createEntityManager();

        Dish dish1 = new Dish("Fries", 130, 130, 20);
        add(dish1);
        Dish dish2 = new Dish("Beef steak", 400, 420, 0);
        add(dish2);
        Dish dish3 = new Dish("Pork steak", 450, 350, 15);
        add(dish3);
        Dish dish4 = new Dish("Salmon steak", 350, 450, 10);
        add(dish4);
        Dish dish5 = new Dish("Salad with chicken", 300, 220, 0);
        add(dish5);
        Dish dish6 = new Dish("Fried vegetables", 300, 180, 15);
        add(dish6);
        Dish dish7 = new Dish("Soup", 450, 250, 40);
        add(dish7);

//        getAll();
//        System.out.println();

        List<Dish> list = doOrder(0, 400);
        for (Dish d : list) {
            System.out.println(d);
        }
    }

    public static List<Dish> doOrder(int minPrice, int maxPrice) {
        Query query = em.createQuery("SELECT d FROM Dish d WHERE " +
                "discount IS NOT NULL AND cost BETWEEN :min AND :max", Dish.class);
        query.setParameter("min", minPrice);
        query.setParameter("max", maxPrice);

        List<Dish> order = query.getResultList();

        int weight = 0;
        int index = 0;
        for (Dish d : order) {
            if (weight + d.getWeight() <= 1000) {
                weight += d.getWeight();
            } else {
                break;
            }
            index++;
        }
        return order.subList(0, index);
    }

    public static void add(Dish dish) {
        if (dish == null) throw new IllegalArgumentException();
        em.getTransaction().begin();
        em.persist(dish);
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void getAll() {
        Query query = em.createQuery("SELECT d FROM Dish d", Dish.class);
        List<Dish> result = query.getResultList();
        for (Dish d : result) {
            System.out.println(d);
        }
    }
}
