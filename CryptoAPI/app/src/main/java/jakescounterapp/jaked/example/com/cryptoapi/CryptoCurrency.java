package jakescounterapp.jaked.example.com.cryptoapi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jaked on 24/03/2018.
 */

public class CryptoCurrency {

    public String name;
    public String price;
    public String change;

    //Gets the relevant details for each currency from the JSON API
    public CryptoCurrency(JSONObject object){
        try{
            JSONObject GBP = object.getJSONObject("GBP");
            this.name = GBP.getString("FROMSYMBOL");
            this.price = GBP.getString("PRICE");
            this.change = GBP.getString("CHANGEPCT24HOUR");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}
