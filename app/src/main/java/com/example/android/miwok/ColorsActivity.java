/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.res.Configuration;
import android.media.Image;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class ColorsActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    ArrayList<Word> arColors;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        arColors = new ArrayList<>();

        arColors.add(new Word(getString(R.string.red),"wetetti",R.drawable.color_red));
        arColors.add(new Word(getString(R.string.mustard_yellow),"chiwiiǝ",R.drawable.color_mustard_yellow));
        arColors.add(new Word(getString(R.string.dusty_yellow),"topiisǝ",R.drawable.color_dusty_yellow));
        arColors.add(new Word(getString(R.string.green),"chokokki",R.drawable.color_green));
        arColors.add(new Word(getString(R.string.brown),"takaaki",R.drawable.color_brown));
        arColors.add(new Word(getString(R.string.gray),"topoppi",R.drawable.color_gray));
        arColors.add(new Word(getString(R.string.black),"kululli",R.drawable.color_black));
        arColors.add(new Word(getString(R.string.white),"kelelli",R.drawable.color_white));

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i !=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        WordAdapter itemsAdapter = new WordAdapter(this, arColors,R.color.category_colors);

        listView = (ListView)findViewById(R.id.rootView);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new OnClickHandler());
    }

    private class OnClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            textToSpeech.speak(arColors.get(i).getDefaultTranslation(),TextToSpeech.QUEUE_FLUSH,null);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem btnEnlish = menu.findItem(R.id.btnEnglish);
        MenuItem btnSwati = menu.findItem(R.id.btnSwati);
        btnEnlish.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = getBaseContext().getResources().getConfiguration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
                return false;
            }
        });
        btnSwati.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Locale locale = new Locale("ss");
                Locale.setDefault(locale);
                Configuration config = getBaseContext().getResources().getConfiguration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
