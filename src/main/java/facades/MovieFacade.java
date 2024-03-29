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

        List<Movie> movieList;
        List<MovieDTO> movieDTOList = new ArrayList<>();

        try {
            movieList = em.createQuery("select m from Movie m where m.title like :title", Movie.class)
                    .setParameter("title", title).getResultList();

            movieList.forEach((movie) -> movieDTOList.add(new MovieDTO(movie)));

            return movieDTOList;
        } finally {
            em.close();
        }

    }

    public List<Movie> getMovieByDirector(String director){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select m from Movie m where m.director like :director", Movie.class)
                    .setParameter("director", director).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Movie> getAllMovies(){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select m from Movie m", Movie.class).getResultList();
        } finally {
            em.close();
        }
    }

}
