package w7.com.br.calculadorasumup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ResultadoActivity extends AppCompatActivity {

    public static final String PARAM = "Parametros";
    private AdView ad_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ad_banner = (AdView) findViewById(R.id.ad_banner);
        ad_banner.loadAd(loadRequest());

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PARAM, 0);
        float debito = settings.getFloat("debito", 0);
        float credito_vista = settings.getFloat("credito_vista", 0);
        float credito_parcelado = settings.getFloat("credito_parcelado", 0);
        float credito_parcelado_acrescimo = settings.getFloat("credito_parcelado_acrescimo", 0);
    }


    private AdRequest loadRequest(){
        return new AdRequest.Builder()
                //.addTestDevice(getString(R.string.adsmob_testdevice_id))
                .build();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
