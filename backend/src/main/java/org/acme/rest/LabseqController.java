package org.acme.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.services.LabseqService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

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
     *
     * @param n the index of the sequence value to be returned, must be a non-negative value
     * @return the value present at index n in the labseq sequence, calculated or retrieved from cache
     */
    @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "The labseq sequence value was successfully retrieved."),
            @APIResponse(responseCode = "400", description = "Bad Request - The index in the request is invalid.")
    })

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public BigInteger labseqCalculation(
            @Parameter(name = "n", description = "Index of the labseq sequence, non-negative integer")
            @PathParam("n") BigInteger n) {

        try {
            return labseqService.labseqCalculation(n);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(e.getMessage())
                        .build()
            );
        }

    }


}
