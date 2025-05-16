package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.services.LabseqService;

import java.math.BigInteger;

@Path("/labseq")
@ApplicationScoped
public class LabseqController {


    @Inject
    LabseqService labseqService;


    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigInteger labseqCalculation(@PathParam("n") BigInteger n) {
        System.out.println(n);

        if(n.compareTo(BigInteger.ZERO)<0){
            throw new WebApplicationException("Input must be non-negative", 400);
        }
        return labseqService.labseqCalculation(n);
    }


}
