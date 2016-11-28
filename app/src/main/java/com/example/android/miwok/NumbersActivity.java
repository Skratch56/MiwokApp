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
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NumbersActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    ArrayList<Word> arNumbers;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        arNumbers = new ArrayList<>();
        arNumbers.add(new Word(getString(R.string.one), "lutti", R.drawable.number_one));
        arNumbers.add(new Word(getString(R.string.two), "otiiko", R.drawable.number_two));
        arNumbers.add(new Word(getString(R.string.three), "tolookosu", R.drawable.number_three));
        arNumbers.add(new Word(getString(R.string.four), "oyyisa", R.drawable.number_four));
        arNumbers.add(new Word(getString(R.string.five), "massokka", R.drawable.number_five));
        arNumbers.add(new Word(getString(R.string.six), "temmokka", R.drawable.number_six));
        arNumbers.add(new Word(getString(R.string.seven), "kanekaku", R.drawable.number_seven));
        arNumbers.add(new Word(getString(R.string.eight), "kawinta", R.drawable.number_eight));
        arNumbers.add(new Word(getString(R.string.nine), "wo'e", R.drawable.number_nine));
        arNumbers.add(new Word(getString(R.string.ten), "na'aacha", R.drawable.number_ten));

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i !=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        WordAdapter itemsAdapter = new WordAdapter(this, arNumbers, R.color.category_numbers);

        listView = (ListView) findViewById(R.id.rootView);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new OnClickHandler());

    }

    private class OnClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            textToSpeech.speak(arNumbers.get(i).getDefaultTranslation(),TextToSpeech.QUEUE_FLUSH,null);
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

