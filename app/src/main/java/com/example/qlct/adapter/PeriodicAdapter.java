package com.example.qlct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.qlct.model.Item;
import com.example.qlct.R;

import java.util.List;

public class PeriodicAdapter extends RecyclerView.Adapter<PeriodicAdapter.ViewHolder> {

    private List<Item> periodicList;
    private Context context;

    private PeriodicAdapter.OnItemClick onItemClick;

    public void setOnItemClick(PeriodicAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    public PeriodicAdapter(Context context, List<Item> periodicList) {
        this.periodicList = periodicList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_periodic, viewGroup, false);
        return new ViewHolder(itemView, 0);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Item item = periodicList.get(i);
        viewHolder.txtTopic.setText(item.getTopic());
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtAmount.setText(item.getAmount());
        viewHolder.cbPeriodic.setChecked(item.isChecked());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(i);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onDelete(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return periodicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTopic;
        TextView txtName;
        TextView txtAmount;
        CheckBox cbPeriodic;
        Button btnDelete;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txt_periodic_topic);
            txtName = itemView.findViewById(R.id.txt_periodic_name);
            txtAmount = itemView.findViewById(R.id.txt_periodic_amount);
            cbPeriodic = itemView.findViewById(R.id.rb_periodic);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public interface OnItemClick{
        void onItemClick(int pos);
        void onDelete(int pos);
    }

}
