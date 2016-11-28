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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class PhrasesActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    ArrayList<Word> arPhrases;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.word_list);

        arPhrases = new ArrayList<>();
        arPhrases.add(new Word(getString(R.string.where_are_you_going),"minto wuksus"));
        arPhrases.add(new Word(getString(R.string.what_is_your_name),"tinna oyaase na"));
        arPhrases.add(new Word(getString(R.string.my_name_is),"Dyaaset"));
        arPhrases.add(new Word(getString(R.string.how_are_you_feeling),"michaksas"));
        arPhrases.add(new Word(getString(R.string.im_feeling_good),"kuchi acit"));
        arPhrases.add(new Word(getString(R.string.are_you_coming),"ǝǝnǝs aa?"));
        arPhrases.add(new Word(getString(R.string.yes_im_coming),"bǝǝ ǝǝnǝm"));
        arPhrases.add(new Word(getString(R.string.im_coming),"ǝǝnem"));
        arPhrases.add(new Word(getString(R.string.lets_go),"yoowutis"));
        arPhrases.add(new Word(getString(R.string.come_here),"ǝninem"));

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i !=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        WordAdapter itemsAdapter = new WordAdapter(this, arPhrases,R.color.category_phrases);

        listView = (ListView)findViewById(R.id.rootView);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new OnClickHandler());

    }
    private class OnClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            textToSpeech.speak(arPhrases.get(i).getDefaultTranslation(),TextToSpeech.QUEUE_FLUSH,null);
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
