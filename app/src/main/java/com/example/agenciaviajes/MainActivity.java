package com.example.agenciaviajes;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[]destinos = {"Cartagena","santa marta", "san andres" , "medellin"};
    String destinosel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanciar y referenciar los IDs del archivo xml
        EditText nombre =  findViewById(R.id.etnombre);
        EditText fecha =  findViewById(R.id.etfecha);
        EditText personas =  findViewById(R.id.numpersonas);
        Spinner destino = findViewById(R.id.spdestino);
        RadioButton rb2 = findViewById(R.id.rb2);
        RadioButton rb4 = findViewById(R.id.rb4);
        RadioButton rb6 = findViewById(R.id.rb6);
        Button calcular = findViewById(R.id.btncalc);
        Button limpiar = findViewById(R.id.btnclean);
        Switch tour = findViewById(R.id.swtour);
        Switch discoteca = findViewById(R.id.swdiscoteca);
        TextView total = findViewById(R.id.tvtotal);

        ArrayAdapter ciudadestino = new ArrayAdapter( this, android.R.layout.simple_list_item_1, destinos);
        destino.setAdapter(ciudadestino);
        destino.setOnItemSelectedListener(this);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().isEmpty()) {
                    if (!fecha.getText().toString().isEmpty()) {
                        if (!personas.getText().toString().isEmpty()) {

                            double xnumeroPersonas = parseDouble(personas.getText().toString());
                            double tarifaDestino = 0;
                            switch (destinosel) {
                                case "Cartagena":
                                    tarifaDestino = 200000;
                                    break;
                                case "santa marta":
                                    tarifaDestino = 180000;
                                    break;
                                case "san andres":
                                    tarifaDestino = 170000;
                                    break;
                                case "medellin":
                                    tarifaDestino = 190000;
                                    break;
                            }
                            double descuento = 0;
                            if (parseDouble(personas.getText().toString()) > 5) {
                                descuento = 0.1;
                            }

                            if (parseDouble(personas.getText().toString()) >= 1 && parseDouble(personas.getText().toString()) <= 10) {


                                double nDias = 0;
                                if (rb2.isChecked()) {
                                    nDias = 2;
                                }
                                if (rb4.isChecked()) {
                                    nDias = 4;
                                }
                                if (rb6.isChecked()) {
                                    nDias = 6;
                                }
                                double pagarTour = 0;
                                double pagarDisco = 0;

                                if (tour.isChecked()){
                                    pagarTour = 60000*xnumeroPersonas;
                                }
                                if(discoteca.isChecked()){
                                    pagarDisco = 80000*xnumeroPersonas;
                                }
                                double totalDeuda =((tarifaDestino * xnumeroPersonas) * nDias ) -(((tarifaDestino * xnumeroPersonas) * nDias ) * descuento)+(pagarTour)+(pagarDisco);
                                DecimalFormat valueFormat = new DecimalFormat("###,###,###,###.#");
                                total.setText(valueFormat.format(totalDeuda));
                            }else{
                                Toast.makeText(getApplicationContext(), "Solo es posible 1 a 10 personas ", Toast.LENGTH_SHORT).show();
                            }



                        } else {
                            Toast.makeText(getApplicationContext(), "Numero de personas obligatorio", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Nombre es obligatorio", Toast.LENGTH_SHORT).show();
                }


            }

        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre.setText("");
                fecha.setText("");
                personas.setText("");
                tour.setChecked(false);
                discoteca.setChecked(false);
                rb2.setChecked(false);
                rb4.setChecked(false);
                rb6.setChecked(false);
                total.setText("");


            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        destinosel = destinos[position];
        Toast.makeText(getApplicationContext(),destinos[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}





