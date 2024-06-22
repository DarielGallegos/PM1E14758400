package examen.pm1.pm1e1040000470058;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import models.Contactos;
import service.impl.infoContactServiceImpl;
import view.infoContactView;

public class InfoContactActivity extends AppCompatActivity implements infoContactView {

    private infoContactServiceImpl service = new infoContactServiceImpl(this);
    ImageView imgPhoto;
    Button btnBack;
    TextView mensajes;
    String[] mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mensajes = findViewById(R.id.mensajes);
        imgPhoto = findViewById(R.id.imgContactSave);
        btnBack = findViewById(R.id.btnBackInfoContact);
        btnBack.setOnClickListener(v -> {
           finish();
        });
        service.getData(getIntent().getIntExtra("id", 0));

        mensaje = new String[]{
                "Un estilo realmente impresionante.",
                "Radiante y lleno de vida.",
                "Me encanta como luces hoy.",
                "Sonrisa deslumbrante.",
                "Fresco y renovado.",
                "Una presencia atractiva y segura.",
                "Luces bien con ese color.",
                "Es como un modelo de revista.",

        };

        mostrarMensajes();
    }

    private void mostrarMensajes() {

        Random random = new Random();
            int posicion = random.nextInt(mensaje.length);

            mensajes.setText(mensaje[posicion]);

    }

    @Override
    public void fillData(Contactos e, Bitmap bm) {
        imgPhoto.setImageBitmap(bm);
    }
}