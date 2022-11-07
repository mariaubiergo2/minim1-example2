package edu.upc.dsa.services;


import edu.upc.dsa.ShopManager;
import edu.upc.dsa.ShopManagerImpl;
import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOcredencials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.lang.Integer.parseInt;

@Api(value = "/shop")
@Path("/shop")
public class ShopService {

    private ShopManager sm;

    public ShopService() {
        this.sm = ShopManagerImpl.getInstance();
        if (sm.sizeUsers()==0) { //(String name, String surnames, String birthdate, String mail, String password)
            User user1 = this.sm.createUser("maria","ubiergo gomez", "avui", "meri@gmail", "gats");
            VOcredencials cred1 = this.sm.getCredentials(user1);
            User user2 = this.sm.createUser("laia", "Luis Fonsi", "dema","lia@lalia", "gossos");
            VOcredencials cred2 = this.sm.getCredentials(user2);
            User user3 = this.sm.createUser("Biel",  "Luis Fonsi", "desa", "aeros@love",  "gossos");
            VOcredencials cred3 = this.sm.getCredentials(user1);
        }

        if (sm.sizeObjectes()==0) {
            this.sm.addObject("taula", "te potes", 50);
            this.sm.addObject("jerro", "trencat", 2);
            this.sm.addObject("Play", "aparell electronic", 149);
        }

    }

    @GET
    @ApiOperation(value = "get all Users", notes = "Per veure un llistat dels usuaris")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.sm.findAllUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all User ordered Alphabeticaly Surname", notes = "En primer lloc es compararan els cognoms i en cas d'empat els noms")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/usersOrdered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortAlpha() {

        List<User> users = this.sm.sortAlpha();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all Users", notes = "Per veure un llistat dels usuaris")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/objectes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectes() {

        List<Objecte> users = this.sm.findAllObjectes();

        GenericEntity<List<Objecte>> entity = new GenericEntity<List<Objecte>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all Objects ordered by price", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objecte.class, responseContainer="List"),
    })
    @Path("/objectsOrdered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortNumObjectes() {

        List<Objecte> objectes = this.sm.sortNumObjectes();

        GenericEntity<List<Objecte>> entity = new GenericEntity<List<Objecte>>(objectes) {};
        return Response.status(201).entity(entity).build()  ;
    }



    @GET
    @ApiOperation(value = "get a User", notes = "pillar info d'un user especific")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User t = this.sm.getUser(parseInt(id));
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @POST
    @ApiOperation(value = "register a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        User u = this.sm.createUser(user.getName(), user.getSurnames(), user.getBirthdate(), user.getMail(), user.getPassword());
        if (this.sm.addUser(user)==null) return Response.status(500).build();
        else return Response.status(201).entity(u).build();
    }

    @POST
    @ApiOperation(value = "login", notes = "Realitzar el login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(VOcredencials credencials) {
        User u = this.sm.logIn(credencials);
        if (u==null)  return Response.status(500).build();
        else return Response.status(201).entity(u).build();
    }


/*
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

*/

    /*

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
    @ApiOperation(value = "register a user", notes = "asdasd")
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


 */
}
