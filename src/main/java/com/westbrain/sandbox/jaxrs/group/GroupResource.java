package com.westbrain.sandbox.jaxrs.group;

import com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;



@Service
@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    @Autowired
    private GroupRepository repository;

    @Context
    private Response response;
    @Context
    private UriInfo uriInfo;

    @GET
    public Iterable<Group> getAllGroups() {
        return repository.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getGroupById(@PathParam("id") Long id) {
        Group group = repository.findOne(id);
        if (group == null) {
            return notFound();
        }
        return Response.ok(group).build();
    }

    @POST
    public Response createGroup(Group group) {
        if (group == null) {
            return badRequest();
        }
        Group savedGroup = repository.save(group);
        return Response.created(uriInfo.getAbsolutePathBuilder().path("{id}").build(savedGroup.getId())).entity(savedGroup).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateGroup(@PathParam("id") Long id, Group group) {
        if (repository.findOne(id) == null) {
            return notFound();
        }
        if (group == null || !Objects.equal(group.getId(), id)) {
            return badRequest();
        }
        if (group.getId() == null) {
            // make sure we have the proper id set before we update
            group.setId(id);
        }
        repository.save(group);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}")
    public Response partialUpdateGroup(@PathParam("id") Long id, Group group) {
        // TODO: implement ability to perform a partial update via POST
        return notFound();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGroup(@PathParam("id") Long id) {
        Group removedGroup = null;
        if (id != null) {
            removedGroup = repository.delete(id);
        }
        if (removedGroup == null) {
            return notFound();
        } else {
            return Response.noContent().build();
        }
    }

    private Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }



}
