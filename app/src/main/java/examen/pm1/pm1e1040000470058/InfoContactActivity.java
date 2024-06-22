package examen.pm1.pm1e1040000470058;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Contactos;
import service.impl.infoContactServiceImpl;
import view.infoContactView;

public class InfoContactActivity extends AppCompatActivity implements infoContactView {

    private infoContactServiceImpl service = new infoContactServiceImpl(this);
    ImageView imgPhoto;

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
        imgPhoto = findViewById(R.id.imgContactSave);
        service.getData(getIntent().getIntExtra("id", 0));
    }

    @Override
    public void fillData(Contactos e, Bitmap bm) {
        imgPhoto.setImageBitmap(bm);
    }
}