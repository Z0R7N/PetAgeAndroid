package zzz.petage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAge extends AppCompatActivity {

    private EditText numY, numM; // переменные для полей Number
    private TextView outAge, txtYear; // для вывода результата и для вывода максимального возраста животного
    private double y, m;
    private String wordYear = "лет"; // для отображения правильного вывода слова - лет или год
    private double pAge; // для возраста животного
    private int hAge; // для возраса человека
    private double h; // для вывода формул
    private String maxPetAge; // максимальный возраст животного
    private int mxPtAge; // максимальный возраст животного
    String animal; // какое животное

    private ImageButton btnBack;

    private static SharedPreferences sPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_age);

        numY = findViewById(R.id.year);
        numM = findViewById(R.id.month);
        outAge = findViewById(R.id.out);

        sPref = getSharedPreferences("Datas", MODE_PRIVATE);
        maxPetAge = sPref.getString("AGE", "");
        animal = sPref.getString("ANIMAL", "");
        updateTextView(maxPetAge);
    }

    public void onClickCount(View v) {
        mxPtAge = Integer.parseInt(maxPetAge);
        if (checkDigits(numY.getText().toString(), numM.getText().toString())) {
            Keyboard.hide(v);
            switch (animal) {
                case "cat":
                    catCnt();
                    break;
//                case "cavy":
//                    cavyCnt();
//                    break;
                case "dogSmall":
                    dogSmallCnt();
                    break;
                case "dogMed":
                    dogMedCnt();
                    break;
                case "dogBig":
                    dogBigCnt();
                    break;
            }
        } else {
            Toast tst = Toast.makeText(getApplicationContext(), "Что-то не то...", Toast.LENGTH_SHORT);
            tst.setGravity(Gravity.TOP, 0, 50);
            tst.show();
        }
    }

    public void updateTextView(String s) {
        txtYear = (TextView) findViewById(R.id.textYear);
        txtYear.setText("до " + s);

        btnBack = findViewById(R.id.btnBack);

        switch (animal) {
            case "cat":
                btnBack.setImageResource(R.drawable.cat_140);
                break;
//            case "cavy":
//                btnBack.setImageResource(R.drawable.cavy_140);
//                break;
            case "dogSmall":
                btnBack.setImageResource(R.drawable.dog_small_140);
                break;
            case "dogMed":
                btnBack.setImageResource(R.drawable.dog_med_140);
                break;
            case "dogBig":
                btnBack.setImageResource(R.drawable.dog_big_140);
                break;
        }
    }

    private boolean checkDigits(String sYear, String sMonth) {
        if (!sYear.isEmpty()) {
            if (sMonth.isEmpty()) {
                m = 0;
            } else {
                m = Integer.parseInt(sMonth);
            }
            y = Integer.parseInt(sYear);
            if (y > mxPtAge || m > 11) return false;
            pAge = y + (m * 10 / 12) / 10;
            return true;
        }
        return false;
    }

    public void onClickBack(View v) {
        Intent intent = new Intent(ChooseAge.this, MainActivity.class);
        startActivity(intent);
    }

    protected void checkWord(int chk) {
        int num = chk % 10;
        if (num == 0 || num > 4 && num <= 9) wordYear = "лет";
        if (num == 1) wordYear = "год";
        if (num > 1 && num < 5) wordYear = "года";
        if (chk > 4 && chk < 20) wordYear = "лет";
    }

    protected void catCnt() {
        if (checkDigits(numY.getText().toString(), numM.getText().toString())) {
            if (pAge >= 1.5) {
                h = (pAge - 2) * 4 + 24;
            } else {
                h = (-2.5921 * (pAge * pAge * pAge) + 4.0353 * (pAge * pAge) + 13.3309 * pAge + 0.1953);
            }

            hAge = (int) Math.round(h);
            checkWord(hAge);
            outAge.setText("" + hAge + " " + wordYear);
        }
    }

    private void dogBigCnt() {
        if (checkDigits(numY.getText().toString(), numM.getText().toString())) {
            if (pAge <= 1) {
                h = dogsBeforeTwo(pAge);
            } else {
                h = (0.0078 * (pAge * pAge * pAge)) + (-0.2228 * (pAge * pAge) + (7.289 * pAge) + 7.8324);
               // y = 0.0078 x3 + −0.2228 x2 + 7.2890 x + 7.8324
            }

            hAge = (int) Math.round(h);
            checkWord(hAge);
            outAge.setText("" + hAge + " " + wordYear);
        }
    }

    private void dogMedCnt() {
        if (checkDigits(numY.getText().toString(), numM.getText().toString())) {
            if (pAge <= 1) {
                h = dogsBeforeTwo(pAge);
            } else {
                h = (0.0069 * (pAge * pAge * pAge) + (-0.2041 * (pAge * pAge)) + 6.3238 * pAge + 10.5907);
               // y = 0.0069 x3 + -0.2041 x2 + 6.3238 x + 10.5907
            }

            hAge = (int) Math.round(h);
            checkWord(hAge);
            outAge.setText("" + hAge + " " + wordYear);
        }
    }

    private void dogSmallCnt() {
        if (checkDigits(numY.getText().toString(), numM.getText().toString())) {
            if (pAge <= 1) {
                h = dogsBeforeTwo(pAge);
            } else {
                h = (0.0078 * (pAge * pAge * pAge)) + (-0.2339 * (pAge * pAge)) + (6.1112 * pAge) + 10.5;
               // y = 0.0078 x3 + -0.2339 x2 + 6.1112 x + 10.5000
            }

            hAge = (int) Math.round(h);
            checkWord(hAge);
            outAge.setText("" + hAge + " " + wordYear);
        }
    }

    private void cavyCnt() {
        if (checkDigits(numY.getText().toString(), numM.getText().toString())) {
            if (pAge >= 1.5) {
                h = (pAge - 2) * 4 + 24;
            } else {
                h = (-2.5921 * (pAge * pAge * pAge) + 4.0353 * (pAge * pAge) + 13.3309 * pAge + 0.1953);
            }

            hAge = (int) Math.round(h);
            checkWord(hAge);
            outAge.setText("" + hAge + " " + wordYear);
        }
    }

    private double dogsBeforeTwo(double dog2) {
        double x = 14.4108 * (Math.pow(dog2, 1.4108));
        return x;
        // y = -0.0298 x3 + 0.6507 x2 + -2.6940 x + 4.1840
        // y = -0.3583 x3 + 7.8167 x2 + -32.4 x + 50.4
        // ((-0.3583 * (dog2 * dog2 * dog2)) + (7.8167 * (dog2 * dog2)) + (-32.4 * dog2) + 50.4);
    }
}
