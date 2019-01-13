package com.anik.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    EditText etxtText;
    Button btnSpeak;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtText=findViewById(R.id.etxt_text);
        btnSpeak=findViewById(R.id.btn_speak);
        tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
               if (status!=TextToSpeech.ERROR)
               {
                   tts.setLanguage(Locale.US);
               }
            }
        });


        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=etxtText.getText().toString();

                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                //1.0f is default speech rate, increase to high and decrease to lower
                tts.setSpeechRate(1.0f);


            }
        });



    }


    @Override
    protected void onPause() {

        if (tts!=null)
        {
            //to stop speech
            tts.stop();
            tts.shutdown();

        }
        super.onPause();
    }
}
