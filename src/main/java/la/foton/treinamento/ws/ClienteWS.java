package la.foton.treinamento.ws;

import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.services.ClienteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteWS {

    @Inject
    private ClienteService clienteService;

    @POST
    public Response cadastraCliente(Cliente cliente) {
        clienteService.cadastraCliente(cliente);
        return Response.created(UriBuilder.fromPath("cliente/{cpf}").build(cliente.getCpf())).build();
    }

    @GET
    public Response listaTodos() {
        return Response.ok(clienteService.listaTodos()).build();
    }

    @GET
    @Path("{cpf}")
    public Response pegarPorCPF(@PathParam("cpf") String cpf) {
        return Response.ok(clienteService.consultaPorCPF(cpf)).build();
    }
}
