package la.foton.treinamento.ws;

import la.foton.treinamento.services.ContaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("conta")
@Produces(MediaType.APPLICATION_JSON)
public class ContaWS {

    @Inject
    private ContaService contaService;

    @GET
    @Path("hello")
    public Response hello() {
        return Response.ok().entity("API Rest Conta").build();
    }

    @GET
    @Path("{numero}")
    public Response consulta(@PathParam("numero") int numero) {
        return Response.ok(contaService.consultaPorNumero(numero)).build();
    }

    @PUT
    @Path("{numero}/deposita")
    public Response deposita(@PathParam("numero") int numero, @QueryParam("valor") double valor) {
        contaService.depositaEmConta(numero, valor);
        return Response.ok().build();
    }

}
