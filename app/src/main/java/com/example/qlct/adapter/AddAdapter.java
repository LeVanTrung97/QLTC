package com.example.qlct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlct.R;
import com.example.qlct.model.Item;

import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.ViewHolder> {

    private List<Item> addList;
    private Context context;

    public AddAdapter(Context context, List<Item> addList) {
        this.addList = addList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_sub, viewGroup, false);
        return new ViewHolder(itemView, 0);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = addList.get(i);
        //todo set Image Bill
        viewHolder.txtTopic.setText(item.getTopic());
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtAmount.setText(item.getAmount());
        viewHolder.txtTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return addList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBill;
        TextView txtTopic;
        TextView txtName;
        TextView txtAmount;
        TextView txtTime;


        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            imgBill = itemView.findViewById(R.id.img_bill);
            txtTopic = itemView.findViewById(R.id.txt_topic);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAmount = itemView.findViewById(R.id.txt_amount);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}
