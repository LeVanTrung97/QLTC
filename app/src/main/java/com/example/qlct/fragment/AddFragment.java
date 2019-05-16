package com.example.qlct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qlct.R;
import com.example.qlct.adapter.AddAdapter;
import com.example.qlct.adapter.PeriodicAdapter;
import com.example.qlct.dialog.AddDialog;
import com.example.qlct.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFragment extends Fragment {

    @BindView(R.id.rcv_add)
    RecyclerView rcvAdd;

    private List<Item> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        initData();
        initViews();
        return view;
    }

    private void initData() {
        addList.clear();
        Item a = new Item(1, "Tiền lương", "Công việc", "12/4/2019", "", "7.000.000", "");
        addList.add(a);
    }

    private void initViews() {
        addAdapter = new AddAdapter(getContext(), addList);
        rcvAdd.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rcvAdd.setLayoutManager(layoutManager);
        rcvAdd.setAdapter(addAdapter);
    }

    @OnClick(R.id.btn_add)
    void add(){
        final AddDialog addDialog = AddDialog.newInstance("Some Title", 1, new AddDialog.Callback() {
            @Override
            public void onResult() {
                //todo update lai data get tu realm
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });
        addDialog.show(getActivity().getSupportFragmentManager(), "dialog_add");
        addDialog.setCancelable(false);
    }

}