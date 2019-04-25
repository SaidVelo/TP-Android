package com.example.cdi182;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    private static final String TAG = "toto";
    private static final int ITEM_ID_TP = 25;
    private static final int ITEM_ID_DP = 26;
    private static final int ITEM_ID_AD = 27;
    private static final int ITEM_ID_SE = 28;
    private static final int ITEM_ID_NOTIF = 29;
    private static final int ITEM_ID_RV = 30;
    private static final int ITEM_ID_WEB = 31;
    private static final int ITEM_ID_CP = 32;


    //Déclarer les composants
    private Button btValider, btAnnuler;
    private RadioButton rbLike;
    private RadioButton rbDislike;
    private ImageView iv;
    private EditText et;
    private RadioGroup rg;

    //Données
    Calendar calendar;

    //OUtils
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Charge la vue
        setContentView(R.layout.activity_main);

        //Je récupère les instances
        btValider = findViewById(R.id.btValider);
        btAnnuler = findViewById(R.id.btAnnuler);
        rbLike = findViewById(R.id.rbLike);
        rbDislike = findViewById(R.id.rbDislike);
        iv = findViewById(R.id.iv);
        et = findViewById(R.id.et);
        rg = findViewById(R.id.rg);

        calendar = Calendar.getInstance();

        //Ecouter les clics
        btValider.setOnClickListener(this);
        btAnnuler.setOnClickListener(this);


    }

    /* -------------------
    // Menu
    --------------------- */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM_ID_TP, 0, "TimePicker Dialog");
        menu.add(0, ITEM_ID_DP, 0, "DatePicker Dialog");
        menu.add(0, ITEM_ID_AD, 0, "Alert Dialog");
        menu.add(0, ITEM_ID_SE, 0, "Service Exemple");
        menu.add(0, ITEM_ID_NOTIF, 0, "Notification Exemple");
        menu.add(0, ITEM_ID_RV, 0, "RecyclerView Exemple");
        menu.add(0, ITEM_ID_WEB, 0, "Web");
        menu.add(0, ITEM_ID_CP, 0, "Code Postal");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == ITEM_ID_TP) {
//je lance le code du timePicker
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 14, 33, true);
            timePickerDialog.show();

        }
        else if (item.getItemId() == ITEM_ID_DP) {
//Datepicker

            //Création de la fenêtre
            //Pour le callback -> Alt+entree -> implémente méthode -> Génère la méthode onTimeSet
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
//Afficher la fenêtre
            datePickerDialog.show();

        }
        else if (item.getItemId() == ITEM_ID_AD) {
//L'alertDialog
            //Préparation de la fenêtre
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//Message
            alertDialogBuilder.setMessage("Mon message");
//titre
            alertDialogBuilder.setTitle("Mon titre");
//bouton ok
            alertDialogBuilder.setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Affiche un toast apres le click sur le bouton ok
                            Toast.makeText(MainActivity.this, "Click sur ok",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
//Icone
            alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
//Afficher la fenêtre
            alertDialogBuilder.show();
        }
        else if (item.getItemId() == ITEM_ID_SE) {
            //Etape 1 : Est ce qu'on a déjà la permission ?
            //Attention prendre le Manifest d’android.util
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                startActivity(new Intent(this, ServiceExActivity.class));
            }
            else {
                //Etape 2 : On affiche la fenêtre de demande de permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

            }

        }
        else if (item.getItemId() == ITEM_ID_NOTIF) {
            startActivity(new Intent(this, NotificationActivity.class));
        }
        else if (item.getItemId() == ITEM_ID_RV) {
            startActivity(new Intent(this, RecyclerViewExActivity.class));
        }
        else if (item.getItemId() == ITEM_ID_WEB) {
            startActivity(new Intent(this, WebExActivity.class));
        }
        else if (item.getItemId() == ITEM_ID_CP) {
            startActivity(new Intent(this, CodePostalActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Etape 1 : Est ce qu'on a déjà la permission ?
        //Attention prendre le Manifest d’android.util
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            startActivity(new Intent(this, ServiceExActivity.class));
        }
        else {
            Toast.makeText(this, "Il faut la permission pour acceder à cette écran", Toast.LENGTH_SHORT).show();
        }
    }

    /* -------------------
    // CallBack Click
    --------------------- */

    @Override
    public void onClick(View v) {
        //Identifier la vue cliquée
        if (v == btValider) {
            if (rbLike.isChecked()) {
                et.setText(rbLike.getText());
            }
            else if (rbDislike.isChecked()) {
                et.setText(rbDislike.getText());
            }

            iv.setImageResource(R.mipmap.ic_done);
            iv.setColorFilter(Color.CYAN);
        }
        else if (v.getId() == btAnnuler.getId()) {
            rg.clearCheck();
            et.setText("");
            iv.setImageResource(R.mipmap.ic_delete);
            iv.setColorFilter(getResources().getColor(R.color.maCouleur, getTheme()));
        }
    }


    public void onBtNextClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("maCle", "Toto");
        startActivity(intent);
    }

    /* -------------------
    // Callback du TimePicker
    --------------------- */

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }

    /* -------------------
   // Callback du DatePicker
   --------------------- */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }
}
