package fr.Security.Services;

import fr.Security.AES.Cryptography;
import fr.Security.RSA.RSAKeyGenerator;
import fr.Security.RSA.RSAKeyManagement;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by rl613611 on 21/02/2017.
 */
@Path("/security")
public class SecurityServicesImpl implements SecurityServices {

   private static final String publicKeyFile = "C:\\Users\\Greg\\Desktop\\test\\publickey.txt";

    @Override
    public Response getPublicKey() {
        try {
            RSAKeyGenerator.GeneratingKeys("","");
            String encodedPublicKey = Cryptography.chiffrementAES(RSAKeyManagement.lectureClePublique(publicKeyFile).toString());
            return Response.ok(encodedPublicKey).build();
        }catch (Exception e){
            return Response.status(204).build();
        }
    }
}
