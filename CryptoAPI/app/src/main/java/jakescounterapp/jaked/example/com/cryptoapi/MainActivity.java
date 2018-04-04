package jakescounterapp.jaked.example.com.cryptoapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> returnedCurrencies = new ArrayList<String>();
    public String currencyRequest ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tbCrypto);
        setSupportActionBar(myToolbar);

        // If previously set, gets the users' chosen currencies
        getSettings();
        // Makes a JSON request to the API for the user's chosen currencies
        getJson();
    }


    // If previously set, gets the users' chosen currencies
    public void getSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        returnedCurrencies.clear();

        // Retrives the saved strings and adds them to an array of currencies
        int size = sharedPreferences.getInt("Status_size",0);
        for (int i=0;i<size;i++){
            returnedCurrencies.add(sharedPreferences.getString("Status_" + i, null));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            // Calls the API again to update the details of the list items
            case R.id.refresh:
                getJson();
                return true;
            // Takes the user to the settingsActivity where they can choose which currencies to display
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Once the user returns from the settings menu, we retrieve their chosen currencies and update the list
    @Override
    protected void onResume() {
        super.onResume();
        currencyRequest = "";
        getSettings();
        getJson();
    }

    public void getJson(){

       // Creates the string that is used to tell the API which currency information to return
       // The final string will look something like; "ADA,BTC,BTH"
       if (currencyRequest.isEmpty()){
           if( returnedCurrencies != null){
               for (String s : returnedCurrencies){
                   if (!s.equals("null")){
                       currencyRequest += s + ",";
                   }
               }
           }
        }

        ArrayList<CryptoCurrency> arrayOfCurrencies = new ArrayList<CryptoCurrency>();
        final CryptoCurrencyAdapter adapter = new CryptoCurrencyAdapter(this, arrayOfCurrencies);

        ListView listView = (ListView) findViewById(R.id.lvCrypto);
        listView.setAdapter(null);
        listView.setAdapter(adapter);



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // The API URL
        String url ="https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+ currencyRequest + "&tsyms=GBP";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonRaw = response.getJSONObject("RAW");
                        // Creates an object for each currency and add it to the listView
                        for (String s : returnedCurrencies){
                            if (!s.equals("null")){
                                JSONObject addCurrency = jsonRaw.getJSONObject(s);
                                CryptoCurrency newCurrency = new CryptoCurrency(addCurrency);
                                adapter.add(newCurrency);
                            }
                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);


    }



}
