package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
        return labseqService.labseqCalculation(n);
    }


}
