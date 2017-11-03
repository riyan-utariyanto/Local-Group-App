package com.riyan.localgrupchat;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText etNama, etPesan;
    int gmabar = R.drawable.user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setTitle("Tambah Chat");

        etNama = (EditText) findViewById(R.id.et_nama);
        etPesan = (EditText) findViewById(R.id.et_pesan);
        preferences = getSharedPreferences(MainActivity.mPreff,0);
        editor = preferences.edit();
    }

    public void kirimPesan(View view) {
        int image = gmabar;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nama",etNama.getText().toString());
            jsonObject.put("pesan",etPesan.getText().toString());
            jsonObject.put("tanggal",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("foto",image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(preferences.contains("pesan")) {
            String dataPesan = preferences.getString("pesan","");

            try {
                JSONArray jsonArray = new JSONArray(dataPesan);
                jsonArray.put(jsonObject);
                editor.putString("pesan", jsonArray.toString());
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editor.putString("pesan", jsonArray.toString());
            editor.apply();
        }

        finish();
    }
}
