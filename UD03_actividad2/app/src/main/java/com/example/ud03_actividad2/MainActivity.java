package com.example.ud03_actividad2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etOperando1, etOperando2;
    TextView tvResultado;
    Button btnSumar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etOperando1 = (EditText) findViewById(R.id.etOperando1);
        etOperando2 = (EditText) findViewById(R.id.etOperando2);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btnSumar = (Button) findViewById(R.id.btnSumar);

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1, num2;

                try{
                    num1=Integer.parseInt(etOperando1.getText().toString());
                    num2=Integer.parseInt(etOperando2.getText().toString());
                    tvResultado.setText(""+(num1+num2));
                }catch(NumberFormatException e){
                    tvResultado.setText("Los números deben ser enteros");
                }
            }
        });
    }
}