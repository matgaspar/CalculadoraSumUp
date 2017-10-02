package w7.com.br.calculadorasumup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    public static final String PARAM = "Parametros";
    private SharedPreferences settings;
    private InterstitialAd ad_instersticial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, getString(R.string.ad_app_id));

        ad_instersticial = new InterstitialAd(this);
        ad_instersticial.setAdUnitId(getString(R.string.ad_intersticial_id));
        loadIntersticial();
        ad_instersticial.setAdListener(new AdListener(){
            @Override
            public void onAdClosed(){
                loadIntersticial();
                loadIntent();
            }
        });

        TextView tx_ac_debito = (TextView) findViewById(R.id.tx_ac_debito);
        TextView tx_ac_credito_vista = (TextView) findViewById(R.id.tx_ac_credito_vista);
        TextView tx_ac_credito_parcelado = (TextView) findViewById(R.id.tx_ac_credito_parcelado);
        TextView tx_ac_credito_parcelado_acrescimo = (TextView) findViewById(R.id.tx_ac_credito_parcelado_acrescimo);

        // Restore preferences
        settings = getSharedPreferences(PARAM, 0);
        tx_ac_debito.setText(Float.toString(settings.getFloat("debito", 0)));
        tx_ac_credito_vista.setText(Float.toString(settings.getFloat("credito_vista", 0)));
        tx_ac_credito_parcelado.setText(Float.toString(settings.getFloat("credito_parcelado", 0)));
        tx_ac_credito_parcelado_acrescimo.setText(Float.toString(settings.getFloat("credito_parcelado_acrescimo", 0)));

        Button btn_calcular = (Button) findViewById(R.id.btn_calcular);
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad_instersticial.show();
            }
        });

    }

    protected void setPreferencia(String indice, float valor){
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(indice, valor);
        editor.commit();
    }
    public void loadIntent(){
        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
        ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
    }

    private void loadIntersticial(){
        ad_instersticial.loadAd(loadRequest());
    }

    private AdRequest loadRequest(){
        return new AdRequest.Builder()
                //.addTestDevice(getString(R.string.adsmob_testdevice_id))
                .build();
    }

    @Override
    protected void onStop(){
        super.onStop();
        setPreferencia("debito", (float)2.3);
        setPreferencia("credito_vista", (float)4.6);
        setPreferencia("credito_parcelado", (float)4.6);
        setPreferencia("credito_parcelado_acrescimo", (float)1.5);
    }
}
