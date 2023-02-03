package se.richardalm.billing;

import io.quarkus.logging.Log;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;
import se.richardalm.billing.entity.Bill;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bills")
public class BillingResource {
    @Inject
    BillingService billingService;

    @GET
    public List<Bill> getAllBillings(){
        return Bill.getAll();
    }

    @POST
    public Bill createABill(Bill bill){
        return billingService.createBilling(bill);
    }

    @GET
    @Path("/{car}")
    public List<Bill> getAllBillsForACar(@RestPath String car){
        return Bill.getAllBillsForACar(car);
    }
}
