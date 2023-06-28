package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CustomerService;
import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Api("Engineering Academy Dropwizard Customer Api")
@Path("/api")
public class CustomerController {
    private CustomerService customerService = new CustomerService();

    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        try{
            return Response.ok(customerService.getAllCustomers()).build();
        } catch(FailedToGetCustomersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/customers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByID(@PathParam("id") int id) {
        try {
            return Response.ok(customerService.getCustomerByID(id)).build();
        } catch(FailedToGetCustomersException e){
            System.err.println(e.getMessage());

            return Response.serverError().entity(e.getMessage()).build();
        } catch(CustomerDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @POST
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerRequest customer){
        try{
            return Response.status(Response.Status.CREATED).entity(customerService.createCustomer(customer)).build();

        } catch(InvalidCustomerException e){

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch(FailedToCreateCustomerException e) {

            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
