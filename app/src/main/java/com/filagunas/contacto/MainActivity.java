package com.filagunas.contacto;



import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
    EditText nombre; EditText telefono; EditText campofecha; EditText email; EditText descripcion ;
    String fechaselect;
      private Button btnsig;
      public Contacto contacto1;
    DatePicker fecha;
    String nombreedit,teledit,emailedit,descedit;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String nombre=data.getStringExtra("campo1");
                Toast.makeText(getApplicationContext(), "Recibidocontactodetalle:"+nombre,Toast.LENGTH_SHORT).show();
            }          }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        nombre = (EditText) findViewById(R.id.editText1);
        fecha = (DatePicker) findViewById(R.id.datePicker);
        telefono = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText4);
        descripcion = (EditText) findViewById(R.id.editText5);
        campofecha=(EditText)findViewById(R.id.editText2);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           campofecha.setText(fecha.getDayOfMonth()+" / "+ fecha.getMonth()+" / "+fecha.getYear());
            }
        });
        final Bundle leer=getIntent().getExtras();

        if (leer!=null){
            nombreedit = leer.getString(getResources().getString(R.string.nombre));
            teledit = leer.getString(getResources().getString(R.string.telefono));
            emailedit = leer.getString(getResources().getString(R.string.email));
            descedit = leer.getString(getResources().getString(R.string.descripcion));

            nombre.setText(nombreedit);
            telefono.setText(teledit);
            email.setText(emailedit);
            descripcion.setText(descedit);
            Toast.makeText(getApplicationContext(), "texto recibido"+nombreedit,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "texto recibido"+teledit,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "texto recibido"+emailedit,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "texto recibido"+descedit,Toast.LENGTH_SHORT).show();

        }
        //boton Siguiente
        btnsig = (Button) findViewById(R.id.btn2);
        btnsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fechaselect=fecha.getDayOfMonth()+" / "+ fecha.getMonth()+" / "+fecha.getYear();

                contacto1 = new Contacto(nombre.getText().toString(), fechaselect, telefono.getText().toString(), email.getText().toString(), descripcion.getText().toString());
                Intent intent=new Intent(MainActivity.this, ContactoDetalle.class);
                intent.putExtra(getResources().getString(R.string.pnombre),contacto1.getNombre());
                intent.putExtra(getResources().getString(R.string.pfecha),contacto1.getFecha());
                intent.putExtra(getResources().getString(R.string.ptelefono),contacto1.getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail),contacto1.getEmail());
                intent.putExtra(getResources().getString(R.string.pdescripcion),contacto1.getDescripcion());
                System.err.println(contacto1.getNombre());
                System.err.println(contacto1.getFecha());
                System.err.println(contacto1.getTelefono());
                System.err.println(contacto1.getEmail());
                System.err.println(contacto1.getDescripcion());
                Toast.makeText(getApplicationContext(), fecha.getDayOfMonth()+" / "+ fecha.getMonth()+" / "+fecha.getYear(),Toast.LENGTH_SHORT).show();
               startActivity(intent);
                /// / startActivityForResult(intent, 1);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }


        @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }
}
