package com.os.humanhelpss.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.os.humanhelpss.InfoSujet;
import com.os.humanhelpss.Model.Sujet;
import com.os.humanhelpss.R;

import java.util.List;

public class AdapterSujet extends RecyclerView.Adapter<AdapterSujet.ViewHolder> {

    private List<Sujet> userList;
    private Context context;


    public AdapterSujet(Context context, List<Sujet> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public com.os.humanhelpss.Adapter.AdapterSujet.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sujet, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull com.os.humanhelpss.Adapter.AdapterSujet.ViewHolder holder, int i) {

        byte[] ressource = userList.get(i).getImage();
        String name = userList.get(i).getTitle();
        String msg = userList.get(i).getDescription();

        holder.setData(ressource, name, msg);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infos(userList.get(i));
            }
        });
    }
    public void infos (Sujet model){
        Intent  intent= new Intent(context, InfoSujet.class);
        intent.putExtra("image",model.getImage());
        intent.putExtra("title",model.getTitle());
        intent.putExtra("description",model.getDescription());
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {


        private ConstraintLayout click;


        private ImageView imageview;
        private TextView textview3;
        private TextView textview4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.item_img);
            textview3 = itemView.findViewById(R.id.item_titre);
            textview4 = itemView.findViewById(R.id.item_description);
            click = itemView.findViewById(R.id.click);


        }

        public void setData(byte[] ressource, String name, String msg) {

            textview3.setText(name);
            textview4.setText(msg);

            Bitmap bitmap = BitmapFactory.decodeByteArray(ressource, 0, ressource.length);
            imageview.setImageBitmap(bitmap);


        }
    }


}