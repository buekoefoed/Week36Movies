package entities;

import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CreateTestData {

    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        Movie m1 = new Movie("Kill Bill: Volume 1","Quentin Tarantino",2003);
        Movie m2 = new Movie("Kill Bill: Volume 2","Quentin Tarantino",2004);
        Movie m3 = new Movie("The Matrix","The Wachowskis",1999);
        Movie m4 = new Movie("The fellowship of the ring","Peter Jackson",2001);

        try {
            em.getTransaction().begin();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
