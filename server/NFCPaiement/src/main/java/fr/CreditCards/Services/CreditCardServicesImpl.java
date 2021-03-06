package fr.CreditCards.Services;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import fr.CreditCards.Providers.CreditCardProviders;
import fr.Security.RSA.Cryptography;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rl613611 on 07/02/2017.
 */

@Path("/creditcard")
public class CreditCardServicesImpl implements CreditCardServices{

    @Override
    public Response getAllCreditCards(@PathParam("EncodedInfos") String EncodedInfos) {
        try{
            String customerId = Cryptography.dechiffrementPathRSA(EncodedInfos);
            ArrayList<Map<String, String>> allCreditCard = CreditCardProviders.getAllCreditCardsById(customerId);
            JSONArray jsonAllCreditCard = new JSONArray(allCreditCard);
            return Response.ok(Cryptography.chiffrementRSA(jsonAllCreditCard.toString())).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response addCreditCard(String EncodedInfos) {
        try {
            Map<String, String> creditCard = Cryptography.dechiffrementJsonRSA(EncodedInfos);
            CreditCardProviders.insertCreditCard(creditCard);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getCreditCard(String EncodedInfos) {
        try{
            Map<String,String> json = Cryptography.dechiffrementJsonRSA(EncodedInfos);
            Map<String, String> creditCard = CreditCardProviders.findCreditCardById(Integer.parseInt(json.get("creditCardId")), json.get("customerId"));
            JSONObject jsonCreditCard = new JSONObject(creditCard);
            return Response.ok(Cryptography.chiffrementRSA(jsonCreditCard.toString())).build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response deleteCreditCard(String EncodedInfos) {
        try{
            Map<String,String> json = Cryptography.dechiffrementJsonRSA(EncodedInfos);
            CreditCardProviders.deleteCreditCard(Integer.parseInt(json.get("creditCardId")));
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
