package w7.com.br.calculadorasumup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String PARAM = "Parametros";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        // MobileAds.initialize(this, "ca-app-pub-3505870887693310/8124891209");

        TextView tx_ac_debito = (TextView) findViewById(R.id.tx_ac_debito);
        TextView tx_ac_credito_vista = (TextView) findViewById(R.id.tx_ac_credito_vista);
        TextView tx_ac_credito_parcelado = (TextView) findViewById(R.id.tx_ac_credito_parcelado);
        TextView tx_ac_credito_parcelado_acrescimo = (TextView) findViewById(R.id.tx_ac_credito_parcelado_acrescimo);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PARAM, 0);
        float debito = settings.getFloat("debito", 0);
        float credito_vista = settings.getFloat("credito_vista", 0);
        float credito_parcelado = settings.getFloat("credito_parcelado", 0);
        float credito_parcelado_acrescimo = settings.getFloat("credito_parcelado_acrescimo", 0);

        tx_ac_debito.setText(Float.toString(debito));
        tx_ac_credito_vista.setText(Float.toString(credito_vista));
        tx_ac_credito_parcelado.setText(Float.toString(credito_parcelado));
        tx_ac_credito_parcelado_acrescimo.setText(Float.toString(credito_parcelado_acrescimo));

        Button btn_calcular = (Button) findViewById(R.id.btn_calcular);
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);

                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PARAM, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("debito", (float)2.3);
        editor.putFloat("credito_vista", (float)4.6);
        editor.putFloat("credito_parcelado", (float)4.6);
        editor.putFloat("credito_parcelado_acrescimo", (float)1.5);

        // Commit the edits!
        editor.commit();
    }
}
