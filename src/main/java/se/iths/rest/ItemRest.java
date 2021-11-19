package se.iths.rest;

import se.iths.entity.Item;
import se.iths.service.ItemService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemRest {

    @Inject
    ItemService itemService;

    @Path("new")
    @POST
    public Response createItem(Item item){
        itemService.createItem(item);
        if(item.getCategory().length() < 2) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Category " + item.getCategory() + " is not long enough. Must be more 2 characters.")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(item).build();
    }

    @Path("update")
    @PUT
    public Response updateItem(Item item) {
        itemService.updateItem(item);
        return Response.ok(item).build();
    }

    @Path("{id}")
    @GET
    public Response getItem(@PathParam("id") Long id) {
        Item foundItem = itemService.findItemById(id);
        if (foundItem == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with ID " + id + " was not found in database")
                    .type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundItem).build();
    }

    @Path("getall")
    @GET
    public Response getAllItems() {
        List<Item> foundItems = itemService.getAllItems();
        return Response.ok(foundItems).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteItem(@PathParam("id") Long id) {
        itemService.deleteItem(id);
        return Response.ok().build();
    }

//    @Path("getallbycategory")
//    @GET
//    public Response getAllItemsByCategory(@QueryParam("category") String category) {
//        // Here's the logic that filters all items of chosen category
//
//    }

    @Path("getname")
    @GET
    public Response getName(@QueryParam("name") String name) {
        return Response.status(200).entity("Users name is " + name).build();
    }

    @Path("updatename/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("name") String name) {
        Item updatedItem = itemService.updateName(id, name);
        return Response.ok(updatedItem).build();
    }

    //JPQL Queries
    @Path("getallnames")
    @GET
    public List<Item> getAllNames() {
        return itemService.getAllNames();
    }

    @Path("getallitemssortedbycategory")
    @GET
    public List<Item> getAllItemsSortedByCategory() {
        return itemService.getAllItemsSortedByCategory();
    }

    @Path("selectmaxprice")
    @GET
    public double selectMaxPrice() {
        return itemService.selectMaxPrice();
    }

    @Path("getallwithnamedquery")
    @GET
    public List<Item> getAllWithNamedQuery() {
        return itemService.getAllWithNamedQuery();
    }

    @Path("updateprice")
    @GET
    public void updatePrice() {
        itemService.updatePrice();
    }

    @Path("getbyname_dynamicquery/{name}")
    @GET
    public List<Item> getByNameDynamicQuery(@PathParam("name") String name) {
        return itemService.getNameByDynamicQuery(name);
    }

    @Path("getbyname_named/{name}")
    @GET
    public List<Item> getByNameNamedParameter(@PathParam("name") String name) {
        return itemService.getByNameNamedParameters(name);
    }

    @Path("getbyname_positional/{name}")
    @GET
    public List<Item> getByNamePositional(@PathParam("name") String name) {
        return itemService.getByNamePositionalParameters(name);
    }

    @Path("getallitemsbetweenprice/{minPrice}/{maxPrice}")
    @GET
    public List<Item> getByNameDynamicQuery(@PathParam("minPrice") double minPrice, @PathParam("maxPrice") double maxPrice) {
        return itemService.getAllItemsBetweenPrice(minPrice, maxPrice);
    }

}
