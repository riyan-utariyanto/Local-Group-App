package com.riyan.localgrupchat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by riyan on 01/11/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.VHolder> {

    JSONArray jsonArray;

    public ChatAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.foto.setImageResource(jsonObject.getInt("foto"));
            holder.nama.setText(jsonObject.getString("nama"));
            holder.pesan.setText(jsonObject.getString("pesan"));
            holder.tanggal.setText(jsonObject.getString("tanggal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nama, pesan, tanggal;
        public VHolder(View itemView) {
            super(itemView);
            foto =  (ImageView)itemView.findViewById(R.id.foto);
            nama = (TextView)itemView.findViewById(R.id.tv_nama);
            pesan = (TextView)itemView.findViewById(R.id.tv_pesan);
            tanggal = (TextView)itemView.findViewById(R.id.tanggal);
        }
    }
}
