package com.example.qlct;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class PeriodicAdapter extends RecyclerView.Adapter<PeriodicAdapter.ViewHolder> {

    private List<Item> periodicList;
    private Context context;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = periodicList.get(i);
        viewHolder.txtTopic.setText(item.getTopic());
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtAmount.setText(item.getAmount());
        viewHolder.cbPeriodic.setChecked(item.isChecked());
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

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txt_periodic_topic);
            txtName = itemView.findViewById(R.id.txt_periodic_name);
            txtAmount = itemView.findViewById(R.id.txt_periodic_amount);
            cbPeriodic = itemView.findViewById(R.id.rb_periodic);

        }
    }
}
