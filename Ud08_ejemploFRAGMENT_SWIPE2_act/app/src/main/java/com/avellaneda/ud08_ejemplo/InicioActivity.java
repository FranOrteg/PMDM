package com.avellaneda.ud08_ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_options, menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_data:

                Intent i = new Intent(this,MainActivity.class );
                startActivity(i);
                return true;
            case R.id.help:

                Intent ii = new Intent(this,MainActivity.class );
                startActivity(ii);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}