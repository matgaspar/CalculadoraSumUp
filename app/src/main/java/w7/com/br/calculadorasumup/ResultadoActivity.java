package w7.com.br.calculadorasumup;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    public static final String PARAM = "Parametros";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView tx_ac_debito = (TextView) findViewById(R.id.tx_ac_debito);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PARAM, 0);
        float debito = settings.getFloat("debito", 0);
        tx_ac_debito.setText(Float.toString(debito));
    }
}
