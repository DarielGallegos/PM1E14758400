package examen.pm1.pm1e1040000470058;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import models.Contactos;
import service.impl.mainActivityImpl;
import utils.Permissions;
import view.mainView;

public class MainActivity extends AppCompatActivity implements mainView {

    private mainActivityImpl service;
    Button btnSave, btnNextView, btnTakePhoto;
    Spinner cboCountries;
    TextView txtName, txtPhone, txtNote;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        service = new mainActivityImpl(this);
        btnSave = findViewById(R.id.btnSaveContact);
        btnNextView = findViewById(R.id.btnNextView);
        btnTakePhoto = findViewById(R.id.btnAddImage);
        cboCountries = findViewById(R.id.cboCountries);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtNote = findViewById(R.id.txtNote);
        imgPhoto = findViewById(R.id.imgContact);
        btnSave.setOnClickListener(v -> {
            int size = cboCountries.getSelectedItem().toString().split(" ").length;
            String code = cboCountries.getSelectedItem().toString().split(" ")[size - 1];
            String name = txtName.getText().toString().trim();
            String phone = txtPhone.getText().toString().trim();
            String note = txtNote.getText().toString().trim();
            Bitmap bm = (imgPhoto.getDrawable() != null) ? ((BitmapDrawable)imgPhoto.getDrawable()).getBitmap() : null;
            String photo = utils.ImagesConvert.convertBase64(bm);
            if(!code.equals("--") && !name.isEmpty() && !phone.isEmpty() && !note.isEmpty()){
                service.insertContact(new Contactos(name, phone, note, code, photo));
                txtName.setText("");
                txtPhone.setText("");
                txtNote.setText("");
                cboCountries.setSelection(0);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alerta Campos Vacios").setMessage("Favor llenar los campos: \n - Pais \n - Nombre \n - Telefono \n - Nota");
                builder.create().show();
            }
        });
        btnNextView.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityList.class));
        });
        btnTakePhoto.setOnClickListener(v -> {
            utils.Permissions.checkCameraPermission(this);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == utils.Permissions.REQUEST_ACCESS_CAMERA){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                utils.Permissions.takePhoto(this);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Permissions.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            if(data != null){
                Bitmap imagen = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                imgPhoto.setImageBitmap(imagen);
            }
        }
    }

    @Override
    public void insertContact(String msg) {
        Log.i("Insert", msg);
    }
}