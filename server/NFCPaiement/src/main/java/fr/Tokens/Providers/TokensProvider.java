package fr.Tokens.Providers;

import fr.Data.Services.DataBaseAccess;
import fr.Data.Services.DataBaseAccessImpl;

import java.util.Map;

/**
 * Created by rl613611 on 21/02/2017.
 */
public class TokensProvider {

    private final static String TOKEN_QUERY = "SELECT * FROM Token WHERE uuid = %1$s";

    private final static String INSERT_TOKEN = "INSERT INTO Token (customerId, creditCardId, somme, uuid) VALUES ( %1$d, %2$d, %3$d, %4$s)";

    private  final static  String DELETE_TOKEN = "DELETE FROM Token WHERE uuid = %1$s";

    public static Map<String, String> findTokenById(String token) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            return db.findOneAsMap(String.format(TOKEN_QUERY, token));
        }
    }

    public static boolean deleteToken (String token) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(DELETE_TOKEN, token));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean insertToken(int customerId, int creditCardId, int somme, String token) throws Exception{
        try (DataBaseAccess db = DataBaseAccessImpl.getDbConnection()) {
            db.query(String.format(INSERT_TOKEN, customerId, creditCardId, somme, token));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
