package com.example.newproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class my_adapter extends RecyclerView.Adapter<my_adapter.viewHolder> {
    public ArrayList<user> users;
    public Context context;
    public my_adapter(ArrayList<user> users,Context context) {
        this.users=users;
        this.context=context;
    }

    @NonNull
    @Override
    public my_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.db_layout, parent, false);

        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull my_adapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fname.setText(users.get(position).getFirstname());
        holder.lname.setText(users.get(position).getLastname());
        holder.email.setText(users.get(position).getEmail());
        user objuser= users.get(position);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,update.class);
                i.putExtra("id",objuser.getId());
                i.putExtra("fname",objuser.getFirstname());
                i.putExtra("lname",objuser.getLastname());
                i.putExtra("email",objuser.getEmail());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof  Dblist){
                    ((Dblist) context).delete(objuser.getId(),position);
                    users.remove(position);
                    notifyDataSetChanged();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView fname;
        public TextView lname;
        public TextView email;
        public Button update;
        public Button delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.fname=itemView.findViewById(R.id.fname);
            this.lname=itemView.findViewById(R.id.lname);
            this.email=itemView.findViewById(R.id.email);
            this.update=itemView.findViewById(R.id.update);
            this.delete=itemView.findViewById(R.id.delete);
        }
    }
}
