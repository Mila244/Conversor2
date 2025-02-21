package com.example.conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerConversion;
    private EditText etValor;
    private TextView txtTemp, txtMoneda, txtLongitud;
    private Button btnConvertir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos de la interfaz
        spinnerConversion = findViewById(R.id.spinnerConversion);
        etValor = findViewById(R.id.etValor);
        txtTemp = findViewById(R.id.txtTemp);
        txtMoneda = findViewById(R.id.txtMoneda);
        txtLongitud = findViewById(R.id.txtLongitud);
        btnConvertir = findViewById(R.id.btnConvertir);

        // Configurar Spinner con opciones
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Temperatura", "Moneda", "Longitud"});
        spinnerConversion.setAdapter(adapter);

        // Evento de clic en el botón Convertir
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarConversion();
            }
        });
    }

    private void realizarConversion() {
        String seleccion = spinnerConversion.getSelectedItem().toString();
        String valorStr = etValor.getText().toString();

        if (valorStr.isEmpty()) {
            Toast.makeText(this, "Ingrese un valor", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor = Double.parseDouble(valorStr);
        DecimalFormat df = new DecimalFormat("#.##");

        if (seleccion.equals("Temperatura")) {
            double fahrenheit = (valor * 9/5) + 32;
            double kelvin = valor + 273.15;
            txtTemp.setText("Celsius: " + df.format(valor) + " | Fahrenheit: " + df.format(fahrenheit) + " | Kelvin: " + df.format(kelvin));
        }
        else if (seleccion.equals("Moneda")) {
            double dolares = valor / 3.8;  // Suponiendo 1 USD = 3.8 PEN
            double euros = dolares * 0.92; // Suponiendo 1 USD = 0.92 EUR
            txtMoneda.setText("Soles: " + df.format(valor) + " | Dólares: " + df.format(dolares) + " | Euros: " + df.format(euros));
        }
        else if (seleccion.equals("Longitud")) {
            double pies = valor * 3.281;
            double pulgadas = valor * 39.37;
            txtLongitud.setText("Metros: " + df.format(valor) + " | Pies: " + df.format(pies) + " | Pulgadas: " + df.format(pulgadas));
        }
    }
}