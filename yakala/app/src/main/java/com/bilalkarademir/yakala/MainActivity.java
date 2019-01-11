package com.bilalkarademir.yakala;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText,timeText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();


        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        ResimGizle();
        score = 0;
        sure();






    }

    public void tanimla(){

        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        imageView = findViewById(R.id.imageView1);
        imageView2= findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8= findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);


    }

    public void increaseScore(View view){

        score++;
        scoreText.setText("Score: "+ score);//imageviewlere tıklandıkça score artıyor

    }


    public void ResimGizle(){

        handler = new Handler();
        runnable = new Runnable() {//runnable işlemi başlıyor
            @Override
            public void run() {//runnable çalıştığı sürece bu işlemleri yap

                for(ImageView image : imageArray){

                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);//rasgele seçim seçiyor
                imageArray[i].setVisibility(View.VISIBLE);//resimleri gösteriyor

                handler.postDelayed(this,680);//600 ms ekranda resim görünüyor
            }
        };

        handler.post(runnable);//handler çalıştırılıyor


    }


    public void sure(){

        //sürenin sayması için
        new CountDownTimer(10000,1000){//10 saniyeden başlasın 1 saniye azalarak gitsin


            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText("Time: "+millisUntilFinished/1000);//üreyi text e yazdırdık ve saniye geri geri sayıyor

            }

            @Override
            public void onFinish() {
                timeText.setText("Süre Bitti");
                handler.removeCallbacks(runnable);//sürenin ilerlemesini durduruyor
                for(ImageView image : imageArray){//resimleri görünmez yapıyoruz

                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);//uyarı penceresi açıyoruz
                alert.setTitle("Tekrar Oyna");
                alert.setMessage("Baştan Başlasın mı?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {//evet seçilirse olacak işlemler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {//hayır seçilirse olacak işlemler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);

                        Toast.makeText(getApplicationContext(),scoreText.getText().toString(),Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.show();
            }
        }.start();



    }




}


