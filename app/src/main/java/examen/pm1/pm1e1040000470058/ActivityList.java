package examen.pm1.pm1e1040000470058;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

import service.impl.listServiceImpl;
import utils.Permissions;
import view.listView;

public class ActivityList extends AppCompatActivity implements listView {

    private listServiceImpl service = new listServiceImpl(this);
    ListView listaContactos;
    Button btnBack, btnShare, btnShowImage, btnDelete, btnUpdate;
    String nombreContact;
    int ID = 0;
    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AlertDialog.Builder err = new AlertDialog.Builder(this);
        err.setTitle("Error").setMessage("No se puede realizar la acción, sin haber seleccionado un contacto.");
        listaContactos = findViewById(R.id.listaContacto);
        btnBack = findViewById(R.id.btnAtras);
        btnShare = findViewById(R.id.btnCompartir);
        btnShowImage = findViewById(R.id.btnVer);
        btnDelete = findViewById(R.id.btnEliminar);
        btnUpdate = findViewById(R.id.btnActualizar);

        service.getData();

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                ID = Integer.parseInt(selectedItem.split(" ")[0]);
                nombreContact = selectedItem.split("-")[1];
                int size = selectedItem.split(" ").length;
                phonenumber = selectedItem.split(" ")[size-1];
            }
        });
        listaContactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                int size = selectedItem.split(" ").length;
                phonenumber = selectedItem.split(" ")[size-1];
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityList.this);
                builder.setTitle("Acción").setMessage("Desea llamar a: " + phonenumber);
                builder.setPositiveButton(R.string.btnSi, (dialog, which) -> {
                    utils.Permissions.checkCallPermission(ActivityList.this, phonenumber);
                });
                builder.setNegativeButton(R.string.btnNo, (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.create().show();
                return false;
            }
        });

        btnShare.setOnClickListener(v -> {
            if(ID != 0) {
                Intent shareContact = new Intent(Intent.ACTION_SEND);
                shareContact.putExtra(Intent.EXTRA_TEXT, "Nombre: " + nombreContact + "\nTelefono: " + phonenumber);
                shareContact.setType("text/plain");
                Intent share = Intent.createChooser(shareContact, "Compartir contacto");
                startActivity(share);
            }else{
                err.create().show();
            }
        });
        btnShowImage.setOnClickListener(v -> {
            if(ID != 0) {
                Intent intent = new Intent(getApplicationContext(), InfoContactActivity.class);
                intent.putExtra("id", ID);
                startActivity(intent);
            }else{
                err.create().show();
            }
        });
        btnUpdate.setOnClickListener(v -> {
            if(ID != 0){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
                finish();
            }else{
                err.create().show();
            }
        });
        btnDelete.setOnClickListener(v -> {
            if(ID != 0){
                service.deleteContact(ID);
                ID = 0;
                service.getData();
            }else{
                err.create().show();
            }
        });
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Permissions.REQUEST_CALL_PHONE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                utils.Permissions.checkCallPermission(this, phonenumber);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void fillList(ArrayList<String> arr) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        listaContactos.setAdapter(adapter);
    }

    @Override
    public void deleteContact(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerta").setMessage(msg).create().show();
    }
}