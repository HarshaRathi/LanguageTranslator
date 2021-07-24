package com.example.languageconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.sql.Struct;
import java.util.Dictionary;
import java.util.LongSummaryStatistics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText1;
    private EditText editText2;
    private Spinner spinner1;
    private Spinner spinner2;
    private Button convert_button;
    private String Lang1;
    private String Lang2;
    private String l1;
    private String l2;
    private String stringtoconvert;
    private String covertedstring;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.lang1_text);
        editText2 = findViewById(R.id.lang2_text);
        spinner1 = findViewById(R.id.spinner_ip);
        spinner2 = findViewById(R.id.spinner2_ip);
        convert_button = findViewById(R.id.convert);

        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringtoconvert = editText1.getText().toString();
                Lang1 = spinner1.getSelectedItem().toString();
                Lang2 = spinner2.getSelectedItem().toString();
                String[] ab ={"Afrikaans","Arabic","Belarusian","Bulgarian","Bengali","Catalan","Czech","Welsh","Danish","German","Greek","English","Esperanto","Spanish","Estonian","Persian","Finnish","French","Irish","Galician","Gujarati","Hebrew","Hindi","Croatian","Haitian","Hungarian","Indonesian","Icelandic","Italian","Japanese","Georgian","Kannada","Korean","Lithuanian","Latvian","Macedonian","Marathi","Malay","Malay","Dutch","Norwegian","Polish","Portuguese","Romanian","Russian","Slovak","Slovenian","Albanian","Swedish","Swahili","Tamil","Telugu","Thai","Tagalog","Turkish","Ukranian","Urdu","Vietnamese"};
                int i;
                for(i=0; i<ab.length; i++){
                    if (Lang1.equals(ab[i])) {

                        l1 = Lang1;

                    }

                    if(Lang2.equals(ab[i])){

                        l2 = Lang2;

                    }

                }
               // editText1.setText(Lang1);
                //editText2.setText(Lang2);
             translate();
            }
        });
    }

    private void translate() {
        TranslatorOptions options = new TranslatorOptions
                .Builder()
                .setSourceLanguage(l1)
                .setTargetLanguage(l2)
                .build();

        final Translator translator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
       translator.downloadModelIfNeeded(conditions)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       translator.translate(stringtoconvert).addOnSuccessListener(new OnSuccessListener<String>() {
                           @Override
                           public void onSuccess(String s) {
                               //editText1.setText(Integer.toString(l1));
                               editText2.setText(s);
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(MainActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });

                   }
               })
               .addOnFailureListener(
                       new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(MainActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       }
               );


    }
    @Override
    public void onClick(View v) {

    }
}
