package com.filagunas.contacto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactoDetalle extends AppCompatActivity {
    private Button btnedit;
    public Contacto contacto1;
    private TextView tvNombre;
    private TextView tvFecha;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    String nombre, fecha,telefono, email,descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);

        final Bundle parametros = getIntent().getExtras();
        nombre = parametros.getString(getResources().getString(R.string.pnombre));
        fecha = parametros.getString(getResources().getString(R.string.pfecha));
        telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        email = parametros.getString(getResources().getString(R.string.pemail));
         descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        tvNombre.setText(nombre);
        tvFecha.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripcion.setText(descripcion);
        btnedit = (Button) findViewById(R.id.editdat);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent= new Intent(ContactoDetalle.this, MainActivity.class);

                intent.putExtra(getResources().getString(R.string.nombre),tvNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.telefono),tvTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.email),tvEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.descripcion),tvDescripcion.getText().toString());

                intent.putExtra("campo1","valor");
                startActivity(intent);
               // setResult(RESULT_OK,intent);
               // finish();


            }
        });


    }

    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }
    public void enviarmail(View v){
    String email=tvEmail.getText().toString();
        Intent emailIntent=new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email"));

    }

}
