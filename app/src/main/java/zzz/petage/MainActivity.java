package zzz.petage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    ImageButton btnCat, btnCavy, btnDogMed, btnDogBg, btnDogSmll;
    Button btnExit;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnCat = findViewById(R.id.btnCat);
        btnCat.setOnClickListener(this);

//        btnCavy = findViewById(R.id.btnCavy);
//        btnCavy.setOnClickListener(this);

        btnDogSmll = findViewById(R.id.btnDogSmll);
        btnDogSmll.setOnClickListener(this);

        btnDogBg = findViewById(R.id.btnDogBg);
        btnDogBg.setOnClickListener(this);

        btnDogMed = findViewById(R.id.btnDogMed);
        btnDogMed.setOnClickListener(this);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCat:
                startChooseAge("cat", 28);
                break;
            case R.id.btnExit:
                OnClickExit();
                break;
//            case R.id.btnCavy:
//                startChooseAge("cavy", 13);
//                break;
            case R.id.btnDogBg:
                startChooseAge("dogBig", 13);
                break;
            case R.id.btnDogMed:
                startChooseAge("dogMed", 16);
                break;
            case R.id.btnDogSmll:
                startChooseAge("dogSmall", 20);
        }
    }

    private void startChooseAge(String s, int i) {
        sPref = getSharedPreferences("Datas", MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString("AGE", String.valueOf(i));
        ed.putString("ANIMAL", s);
        ed.commit();

        Intent intent = new Intent(MainActivity.this, ChooseAge.class);
        startActivity(intent);
    }

    public void OnClickExit() {
        moveTaskToBack(true);
        super.onDestroy();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
}