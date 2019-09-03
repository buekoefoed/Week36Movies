package facades;

import entities.Movie;
import entities.MovieDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getMovieCount(){
        EntityManager em = getEntityManager();
        try{
            return (long)em.createQuery("select count (r) from Movie r").getSingleResult();
        }finally{  
            em.close();
        }
    }

    public Movie getMovieByID(Long id){
        EntityManager em = getEntityManager();
        try {
            return em.find(Movie.class, id);
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> getMovieDTOByTitle(String title){
        EntityManager em = getEntityManager();
        try {
            TypedQuery query = em.createQuery("select m from Movie m where m.title like :title", Movie.class)
                    .setParameter("title", title);

            List<MovieDTO> movieDTOList = new ArrayList<>();

            for (int i = 0; i < query.getResultList().size(); i++) {
                movieDTOList.add(new MovieDTO((Movie) query.getResultList().get(i)));
            }

            return movieDTOList;

        } finally {
            em.close();
        }
    }

}
