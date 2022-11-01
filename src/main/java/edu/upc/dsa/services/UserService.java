package edu.upc.dsa.services;


import edu.upc.dsa.UsersManager;
import edu.upc.dsa.UsersManagerImpl;
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

    private UsersManager tm;

    public UserService() {
        this.tm = UsersManagerImpl.getInstance();
        if (tm.size()==0) { //(String name, String surnames, String birthdate, String mail, String password)
            this.tm.createUser("maria","ubiergo gomez", "avui", "meri@gmail", "gats");
            this.tm.createUser("laia", "Luis Fonsi", "dema","lia@lalia", "gossos");
            this.tm.createUser("Biel",  "Luis Fonsi", "desa", "aeros@love",  "gossos");
        }


    }

    @GET
    @ApiOperation(value = "get all User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.tm.findAll();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User t = this.tm.getUser(id);
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
        User t = this.tm.getUser(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteUser(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updateUser(User user) {

        User t = this.tm.updateUser(user);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }


    @PUT
    @ApiOperation(value = "update a Users Coins", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updateUserCoins(User user) {

        User t = this.tm.updateUserCoins(user);

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
        if (this.tm.addUser(user)==null)
            Response.status(500).entity(user).build();
        return Response.status(201).entity(user).build();
    }

}
