package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {
    List<String> mobileList=new ArrayList<>();
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }
    @POST
    @Consumes(MediaType.TEXT_PLAIN) // consumes input as mobileName in postman(Body in text format)
    @Produces(MediaType.TEXT_PLAIN)  //produces output as mobileName in postman(response in text format)
    public Response addNewMobile(String mobileName){
        mobileList.add(mobileName);
        return Response.ok(mobileName).build();
    }
    @PUT
    @Path("/{oldmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("oldmobilename") String oldMobileName,@QueryParam("newmobilename") String newMobileName){
        mobileList=mobileList.stream().map(mobile->{
            if(mobile.equals(oldMobileName))
                return newMobileName;
            else
                return mobile;
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }


    @DELETE
    @Path("/{mobiletodelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam ("mobiletodelete") String mobileToDelete){
        boolean isRemoved=mobileList.remove(mobileToDelete);
        if(isRemoved)
            return Response.ok(mobileList).build();
        else
            return  Response.status(Response.Status.BAD_REQUEST).build();
    }

}
