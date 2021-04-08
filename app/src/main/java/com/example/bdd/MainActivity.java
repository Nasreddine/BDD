package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "preferences_file";

    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetesDB").build();

        PlaneteDao planeteDao = db.planeteDao();

        loadData(planeteDao);
    }

    private void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }

                List<Planete> planetes = planeteDao.getAll();

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Il y a [" + planetes.size() + "] Planètes dans la base de données" );
                        for (int i =0; i< planetes.size();i++) {
                            Toast.makeText(MainActivity.this, "Planete = " + planetes.get(i).getNom(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();

    }

    private void initData(PlaneteDao planeteDao) {

        ArrayList<Planete> planetes = new ArrayList<>();

        planetes.add(new Planete(1,"Mercure","4900"));
        planetes.add(new Planete(2,"Venus","12000"));
        planetes.add(new Planete(3,"Terre","12800"));
        planetes.add(new Planete(4,"Mars","6800"));
        planetes.add(new Planete(5,"Jupiter","144000"));
        planetes.add(new Planete(6,"Saturne","120000"));
        planetes.add(new Planete(7,"Uranus","52000"));
        planetes.add(new Planete(8,"Neptune","50000"));
        planetes.add(new Planete(9,"Pluton","2300"));

        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }
}