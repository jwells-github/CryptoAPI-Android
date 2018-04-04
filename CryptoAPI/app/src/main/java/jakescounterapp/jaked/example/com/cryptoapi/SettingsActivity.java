package jakescounterapp.jaked.example.com.cryptoapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;

/**
 * Created by jaked on 26/03/2018.
 */

public class SettingsActivity extends AppCompatActivity  {

    public static final String EXTRA_CHOSEN_CURRENCIES = "jaked.android.cryptoapi.chosen_currencies";

    public ArrayList<String> chosenCurrencies = new ArrayList<String>();

    public boolean mADA,mBCH,mBNB,mBTC,mDASH,mEOS,mETC,
            mETH,mHT,mICX,mIOST,mLTC,mMTL,mNCASH,mNEO,
            mQTUM,mSTORM,mTRX,mXRP,mXVG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CompoundButton.OnCheckedChangeListener multiListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                switch (v.getId()){
                    case R.id.ADA:
                        mADA = isChecked;
                        break;
                    case R.id.BCH:
                        mBCH = isChecked;
                        break;
                    case R.id.BNB:
                        mBNB = isChecked;
                        break;
                    case R.id.BTC:
                        mBTC = isChecked;
                        break;
                    case R.id.DASH:
                        mDASH = isChecked;
                        break;
                    case R.id.EOS:
                        mEOS = isChecked;
                        break;
                    case R.id.ETC:
                        mETC = isChecked;
                        break;
                    case R.id.ETH:
                        mETH = isChecked;
                        break;
                    case R.id.HT:
                        mHT = isChecked;
                        break;
                    case R.id.ICX:
                        mICX = isChecked;
                        break;
                    case R.id.IOST:
                        mIOST = isChecked;
                        break;
                    case R.id.LTC:
                        mLTC = isChecked;
                        break;
                    case R.id.MTL:
                        mMTL = isChecked;
                        break;
                    case R.id.NCASH:
                        mNCASH = isChecked;
                        break;
                    case R.id.NEO:
                        mNEO = isChecked;
                        break;
                    case R.id.QTUM:
                        mQTUM = isChecked;
                        break;
                    case R.id.STORM:
                        mSTORM = isChecked;
                        break;
                    case R.id.TRX:
                        mTRX = isChecked;
                        break;
                    case R.id.XRP:
                        mXRP = isChecked;
                        break;
                    case R.id.XVG:
                        mXVG = isChecked;
                        break;

                }


            }
        };
        //on each switch
        ((Switch) findViewById(R.id.ADA)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.BCH)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.BNB)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.BTC)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.DASH)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.EOS)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.ETC)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.ETH)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.HT)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.ICX)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.IOST)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.LTC)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.MTL)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.NCASH)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.NEO)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.QTUM)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.STORM)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.TRX)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.XRP)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.XVG)).setOnCheckedChangeListener(multiListener);


        // Sets previously selected currencies as checked & true
        getSettings();

    }

    // Saves the users selected currencies
    public void saveSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Status_size", chosenCurrencies.size());

        // Target API level does not support 'String Sets'
        // Loops through the array of currencies and adds each item as its own string with a given Id, i
        for(int i = 0; i <chosenCurrencies.size(); i++){
            editor.remove("Status_" + i);
            editor.putString("Status_" + i, chosenCurrencies.get(i));
        }
        editor.commit();
    }

    // Sets previously selected currencies as checked
    public void getSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("Status_size",0);

        // Retrives the saved strings and adds them to an array of currencies
        for (int i=0;i<size;i++){
            chosenCurrencies.add(sharedPreferences.getString("Status_" + i, null));
        }

        // Enables the swicth for previously saved currencies
        if(chosenCurrencies.contains("ADA")){
            ((Switch) findViewById(R.id.ADA)).setChecked(true);
        }
        if(chosenCurrencies.contains("BCH")){
            ((Switch) findViewById(R.id.BCH)).setChecked(true);
        }
        if(chosenCurrencies.contains("BNB")){
            ((Switch) findViewById(R.id.BNB)).setChecked(true);
        }
        if(chosenCurrencies.contains("BTC")){
            ((Switch) findViewById(R.id.BTC)).setChecked(true);
        }
        if(chosenCurrencies.contains("DASH")){
            ((Switch) findViewById(R.id.DASH)).setChecked(true);
        }
        if(chosenCurrencies.contains("EOS")){
            ((Switch) findViewById(R.id.EOS)).setChecked(true);
        }
        if(chosenCurrencies.contains("ETC")){
            ((Switch) findViewById(R.id.ETC)).setChecked(true);
        }
        if(chosenCurrencies.contains("ETH")){
            ((Switch) findViewById(R.id.ETH)).setChecked(true);
        }
        if(chosenCurrencies.contains("HT")){
            ((Switch) findViewById(R.id.HT)).setChecked(true);
        }
        if(chosenCurrencies.contains("ICX")){
            ((Switch) findViewById(R.id.ICX)).setChecked(true);
        }
        if(chosenCurrencies.contains("IOST")){
            ((Switch) findViewById(R.id.IOST)).setChecked(true);
        }
        if(chosenCurrencies.contains("LTC")){
            ((Switch) findViewById(R.id.LTC)).setChecked(true);
        }
        if(chosenCurrencies.contains("MTL")){
            ((Switch) findViewById(R.id.MTL)).setChecked(true);
        }
        if(chosenCurrencies.contains("NCASH")){
            ((Switch) findViewById(R.id.NCASH)).setChecked(true);
        }
        if(chosenCurrencies.contains("NEO")){
            ((Switch) findViewById(R.id.NEO)).setChecked(true);
        }
        if(chosenCurrencies.contains("QTUM")){
            ((Switch) findViewById(R.id.QTUM)).setChecked(true);
        }
        if(chosenCurrencies.contains("STORM")){
            ((Switch) findViewById(R.id.STORM)).setChecked(true);
        }
        if(chosenCurrencies.contains("TRX")){
            ((Switch) findViewById(R.id.TRX)).setChecked(true);
        }
        if(chosenCurrencies.contains("XRP")){
            ((Switch) findViewById(R.id.XRP)).setChecked(true);
        }
        if(chosenCurrencies.contains("XVG")){
            ((Switch) findViewById(R.id.XVG)).setChecked(true);
        }

        chosenCurrencies.clear();
    }


    @Override
    public void finish() {
        // Adds the chosen currencies to the chosenCurrencies array
        getChosenCurrencies();
        // Saves the user's chosen currencies
        saveSettings();
        super.finish();
    }

    // Checks which switchs are enabled and adds the relevant currencies to the chosenCurrencies array
    public void getChosenCurrencies(){
        if(mADA == true && !chosenCurrencies.contains("ADA")){
            chosenCurrencies.add("ADA");
        }
        if(mBCH == true && !chosenCurrencies.contains("BCH")){
            chosenCurrencies.add("BCH");
        }
        if(mBNB == true && !chosenCurrencies.contains("BNB")){
            chosenCurrencies.add("BNB");
        }
        if(mBTC == true && !chosenCurrencies.contains("BTC")){
            chosenCurrencies.add("BTC");
        }
        if(mDASH == true && !chosenCurrencies.contains("DASH")){
            chosenCurrencies.add("DASH");
        }
        if(mEOS == true && !chosenCurrencies.contains("EOS")){
            chosenCurrencies.add("EOS");
        }
        if(mETC == true && !chosenCurrencies.contains("ETC")){
            chosenCurrencies.add("ETC");
        }
        if(mETH == true && !chosenCurrencies.contains("ETH")){
            chosenCurrencies.add("ETH");
        }
        if(mHT == true && !chosenCurrencies.contains("HT")){
            chosenCurrencies.add("HT");
        }
        if(mICX == true && !chosenCurrencies.contains("ICX")){
            chosenCurrencies.add("ICX");
        }
        if(mIOST == true && !chosenCurrencies.contains("IOST")){
            chosenCurrencies.add("IOST");
        }
        if(mLTC == true && !chosenCurrencies.contains("LTC")){
            chosenCurrencies.add("LTC");
        }
        if(mMTL == true && !chosenCurrencies.contains("MTL")){
            chosenCurrencies.add("MTL");
        }
        if(mNCASH == true && !chosenCurrencies.contains("NCASH")){
            chosenCurrencies.add("NCASH");
        }
        if(mNEO == true && !chosenCurrencies.contains("NEO")){
            chosenCurrencies.add("NEO");
        }
        if(mQTUM == true && !chosenCurrencies.contains("QTUM")){
            chosenCurrencies.add("QTUM");
        }
        if(mSTORM == true && !chosenCurrencies.contains("STORM")){
            chosenCurrencies.add("STORM");
        }
        if(mTRX == true && !chosenCurrencies.contains("TRX")){
            chosenCurrencies.add("TRX");
        }
        if(mXRP == true && !chosenCurrencies.contains("XRP")){
            chosenCurrencies.add("XRP");
        }
        if(mXVG == true && !chosenCurrencies.contains("XVG")){
            chosenCurrencies.add("XVG");
        }
    }

}





