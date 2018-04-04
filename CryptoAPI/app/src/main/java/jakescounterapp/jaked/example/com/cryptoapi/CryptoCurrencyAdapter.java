package jakescounterapp.jaked.example.com.cryptoapi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by jaked on 24/03/2018.
 */

public class CryptoCurrencyAdapter extends ArrayAdapter<CryptoCurrency> {

    public CryptoCurrencyAdapter(Context context, ArrayList<CryptoCurrency> cryptoCurrencies){
        super(context,0,cryptoCurrencies);
    }

    // Adds each row to the list view
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CryptoCurrency cryptoCurrency = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_currency, parent, false);
        }

            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            TextView tvChange = (TextView) convertView.findViewById(R.id.tvChange);

            tvName.setText(getName(cryptoCurrency.name));
            tvPrice.setText("£ " + cryptoCurrency.price);

            // Sets the text colour to red if the currency has decreased in value
            // Sets the text colour to green if the currency has increased in value
            if(cryptoCurrency.change.substring(0,1).equals("-")){
                tvChange.setTextColor(Color.rgb(255,0,0));
                // Trims the percent change to 3 figures
                // Negative change comes with a minus symbol so the substring is slightly adjusted.
                tvChange.setText("- " + cryptoCurrency.change.substring(1,5) + "%");
            }
            else{
                tvChange.setTextColor(Color.rgb(0,128,0));
                tvChange.setText("+ " + cryptoCurrency.change.substring(0,4) + "%");
            }


            return convertView;


    }
    // Sets the full name for a currency from the abbreviated symbol.
    // Currently the API does not provide the currency's full name.
    public String getName(String name){
        switch (name){
            case "ADA":
                return "Cardano (ADA)";
            case "BCH":
                return "Bitcoin Cash (BCH)";
            case "BNB":
                return "Binance Coin (BNB)";
            case "BTC":
                return "Bitcoin (BTC)";
            case "DASH":
                return "DigitalCash (DASH)";
            case "EOS":
                return "EOS (EOS)";
            case "ETC":
                return "Etherum Classic (ETC)";
            case "ETH":
                return "Ethereum (ETH)";
            case "HT":
                return "Huobi Token (HT)";
            case "ICX":
                return "Icon Project (ICX)";
            case "IOST":
                return "IOS token (IOST)";
            case "LTC":
                return "Litecoin (LTC)";
            case "MTL":
                return "Metal (MTL)";
            case "NCASH":
                return "Nucleus Vision (NCASH)";
            case "NEO":
                return "NEO (NEO)";
            case "QTUM":
                return "QTUM (QTUM)";
            case "STORM":
                return "Storm (STORM)";
            case "TRX":
                return "Tronix (TRX)";
            case "XRp":
                return "Ripple (XRP)";
            case "XVG":
                return "Verge (XVG)";
            default: return "Unknown";
        }

    }
}
