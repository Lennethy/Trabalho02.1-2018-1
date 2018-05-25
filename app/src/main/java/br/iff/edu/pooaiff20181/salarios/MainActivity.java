package br.iff.edu.pooaiff20181.salarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner spCargo;
    private EditText etExtras;
    private EditText etFilhos;
    private EditText etFaltas;
    private Button btCalc;
    private TextView tvResultado;

    private String funcao;
    private int horas;
    private int filhos;
    private int faltas;
    private float salarioPiso;
    private float salarioLiquido;
    private float inss;
    private float vlrHora;
    private float vlrFaltas;
    private float vlrFilhos;
    private float proventos;
    private float descontos;

    DecimalFormat formato = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCargo = (Spinner) findViewById(R.id.spSelect);
        etExtras = (EditText) findViewById(R.id.etExtras);
        etFaltas = (EditText) findViewById(R.id.etFaltas);
        etFilhos = (EditText) findViewById(R.id.etFilhos);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btCalc = (Button) findViewById(R.id.btCalc);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.select,R.layout.support_simple_spinner_dropdown_item);
        spCargo.setAdapter(adapter);

        btCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
                resultado();
            }
        });
    }

    public void calcular(){

        funcao = spCargo.getSelectedItem().toString();

        if(funcao.equals("Gerente")) {
            salarioPiso = 2000;
            vlrHora = (salarioPiso / 240) * 2;
            vlrFaltas = (salarioPiso / 30);
            vlrFilhos = (float) (salarioPiso * 0.03);

            horas = Integer.parseInt(etExtras.getText().toString());
            faltas = Integer.parseInt(etFaltas.getText().toString());
            filhos = Integer.parseInt(etFilhos.getText().toString());

            vlrHora *= horas;
            vlrFaltas *= faltas;
            vlrFilhos *= filhos;

            proventos = (salarioPiso + vlrHora + vlrFilhos);
            inss = (float) (proventos * 0.1);
            descontos = vlrFaltas + inss;
            salarioLiquido = proventos - descontos;
        }else{
            if(funcao.equals("Supervisor")){
                salarioPiso = 900;
                vlrHora = (salarioPiso / 240) * 2;
                vlrFaltas = (salarioPiso / 30);
                vlrFilhos = (float) (salarioPiso * 0.03);

                horas = Integer.parseInt(etExtras.getText().toString());
                faltas = Integer.parseInt(etFaltas.getText().toString());
                filhos = Integer.parseInt(etFilhos.getText().toString());

                proventos = (salarioPiso + vlrHora + vlrFilhos);
                inss = (float) (proventos * 0.1);
                descontos = vlrFaltas + inss;
                salarioLiquido = proventos - descontos;
            }else{
                if(funcao.equals("Servente")){
                    salarioPiso = 300;
                    vlrHora = (salarioPiso / 240) * 2;
                    vlrFaltas = (salarioPiso / 30);
                    vlrFilhos = (float) (salarioPiso * 0.03);

                    horas = Integer.parseInt(etExtras.getText().toString());
                    faltas = Integer.parseInt(etFaltas.getText().toString());
                    filhos = Integer.parseInt(etFilhos.getText().toString());

                    vlrHora *= horas;
                    vlrFaltas *= faltas;
                    vlrFilhos *= filhos;

                    proventos = (salarioPiso + vlrHora + vlrFilhos);
                    inss = (float) (proventos * 0.1);
                    descontos = vlrFaltas + inss;
                    salarioLiquido = proventos - descontos;
                }
            }
        }
    }
    public void resultado(){
        tvResultado.setText("Proventos: R$" + formato.format(proventos) + "\nDescontos: R$ " + formato.format(descontos) +
        "\nSalário líquido: R$ " + formato.format(salarioLiquido));
    }
}
