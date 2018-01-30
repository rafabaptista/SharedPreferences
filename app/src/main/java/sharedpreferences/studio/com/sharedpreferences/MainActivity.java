package sharedpreferences.studio.com.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText textNome;
    private Button botaoSalvar;
    private TextView textViewResultado;

    //constante
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNome = (EditText)findViewById(R.id.textNome);
        botaoSalvar = (Button)findViewById(R.id.botaoSalvar);
        textViewResultado = (TextView)findViewById(R.id.textViewResultado);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0); //0 = privado
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(textNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Nome é necessário!", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("nome", textNome.getText().toString());
                    editor.commit();
                    textViewResultado.setText("Olá, " +  textNome.getText().toString() + ".");
                }
            }
        });

        //recuperar os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "<usuario>");
            textViewResultado.setText("Olá, " +  nomeUsuario + ".");
        } else {
            textViewResultado.setText("Olá, <usuário>");
        }
    }
}
