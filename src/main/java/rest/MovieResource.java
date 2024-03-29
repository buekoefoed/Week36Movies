package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Connection established\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") Long id){
        return GSON.toJson(FACADE.getMovieByID(id));
    }

    @Path("title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieDTOByTitle(@PathParam("title") String title){
        return GSON.toJson(FACADE.getMovieDTOByTitle(title));
    }

    @Path("director/{director}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByDirector(@PathParam("director") String director){
        return GSON.toJson(FACADE.getMovieByDirector(director));
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies(){
        return GSON.toJson(FACADE.getAllMovies());
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Movie entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Movie entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
