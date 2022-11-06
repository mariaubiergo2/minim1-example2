package edu.upc.dsa.services;


import edu.upc.dsa.ShopManager;
import edu.upc.dsa.ShopManagerImpl;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users", description = "Endpoint to Track Service")
@Path("/users")
public class UserService {

    private ShopManager sm;

    public UserService() {
        this.sm = ShopManagerImpl.getInstance();
        if (sm.sizeUsers()==0) { //(String name, String surnames, String birthdate, String mail, String password)
            this.sm.addUser("maria","ubiergo gomez", "avui", "meri@gmail", "gats");
            this.sm.addUser("laia", "Luis Fonsi", "dema","lia@lalia", "gossos");
            this.sm.addUser("Biel",  "Luis Fonsi", "desa", "aeros@love",  "gossos");
        }

        if (sm.sizeObjectes()==0) {
            this.sm.addObject("taula", "te potes", 50);
            this.sm.addObject("jerro", "trencat", 2);
            this.sm.addObject("Play", "aparell electronic", 149);
        }

    }

    @GET
    @ApiOperation(value = "get all Users", notes = "per veure un llistat dels usuaris")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.sm.findAllUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all User ordered Alphabeticaly Surname", notes = "En primer lloc es compararan els " +
            "cognoms i en cas d'empat els noms")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortAlpha() {

        List<User> users = this.sm.sortAlpha();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }


    @GET
    @ApiOperation(value = "get a User", notes = "pillar info d'un user especific")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User t = this.sm.getUser(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        Integer t = this.sm.deleteUser(id);
        if (t == null) return Response.status(404).build();
        else return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updateUser(User user) {

        User t = this.sm.updateUser(user);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "create a new Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {

        if (user.getSurnames()==null || user.getName()==null)  return Response.status(500).entity(user).build();
        if (this.sm.addUser(user)==null)
            Response.status(500).entity(user).build();
        return Response.status(201).entity(user).build();
    }

}
