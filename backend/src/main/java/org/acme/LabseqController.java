package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.services.LabseqService;

import java.math.BigInteger;

/**
 * REST Controller that exposes the endpoint to calculate labseq sequence values
 */
@Path("/labseq")
@ApplicationScoped
public class LabseqController {


    @Inject
    LabseqService labseqService;

    /**
     * Endpoint that returns the nth labseq number
     * Example usage: GET /labseq/10
     * @param n the index of the sequence value to be returned, must be a non-negative value
     * @return the value present at index n in the labseq sequence, calculated or retrieved from cache
     */
    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigInteger labseqCalculation(@PathParam("n") BigInteger n) {

        if(n.compareTo(BigInteger.ZERO)<0){
            throw new WebApplicationException("Input must be non-negative", 400);
        }

        try {
            return labseqService.labseqCalculation(n);
        } catch (Exception e){
            throw new WebApplicationException(e.getMessage());
        }

    }


}
