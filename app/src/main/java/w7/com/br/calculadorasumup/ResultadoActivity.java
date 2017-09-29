package w7.com.br.calculadorasumup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ResultadoActivity extends AppCompatActivity {

    public static final String PARAM = "Parametros";

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

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PARAM, 0);
        float debito = settings.getFloat("debito", 0);
        float credito_vista = settings.getFloat("credito_vista", 0);
        float credito_parcelado = settings.getFloat("credito_parcelado", 0);
        float credito_parcelado_acrescimo = settings.getFloat("credito_parcelado_acrescimo", 0);
    }

}
