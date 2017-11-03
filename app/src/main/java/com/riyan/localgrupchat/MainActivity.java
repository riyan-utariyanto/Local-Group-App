package com.riyan.localgrupchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static String mPreff = "com.riyan.chat";
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.RV_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences pref = getSharedPreferences(mPreff,0);
        String dataMessage = pref.getString("pesan","");
        try {
            JSONArray jsonArray = new JSONArray(dataMessage);
            chatAdapter = new ChatAdapter(jsonArray);

            recyclerView.setAdapter(chatAdapter);
            chatAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON", dataMessage);
    }

    public void addMessage(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_hapus) {
            SharedPreferences sharedPreferences = getSharedPreferences(mPreff,0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    boolean doubleBacktoExitPressedOnce = false;

    @Override
    public void onBackPressed(){
        if(doubleBacktoExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBacktoExitPressedOnce = true;
        Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBacktoExitPressedOnce = false;
            }
        },1500);
    }
}
