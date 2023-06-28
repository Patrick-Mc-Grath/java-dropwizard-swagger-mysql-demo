package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard Product API")
@Path("/api")
public class ProductController {
    private ProductService productService = new ProductService();

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts()
    {
        try {
            return Response.ok(productService.getAllProducts()).build();
        } catch (FailedToGetProductsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByID(@PathParam("id") int id)
    {
        try {
            return Response.ok(productService.getProductByID(id)).build();
        } catch(FailedToGetProductsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch(ProductDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @POST
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductRequest product) {
        try {
            return Response.status(productService.createProduct(product)).build();
        } catch (FailedToCreateProductException e) {
            System.err.println(e.getMessage());

            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidProductException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
    }

    @PUT
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id")int id, ProductRequest product) {
        try {
            productService.updateProduct(product, id);

            return Response.accepted().build();
        } catch(FailedToUpdateProductException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch(ProductDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InvalidProductException e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id")int id) {
        try{
            productService.deleteProduct(id);

            return Response.accepted().build();
        } catch(ProductDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch(FailedtoDeleteProductException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
