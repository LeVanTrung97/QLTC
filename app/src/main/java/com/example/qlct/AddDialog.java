package com.example.qlct;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;

public class AddDialog extends DialogFragment {

    private TextView txtTopic;
    private EditText edtName;

    public AddDialog() {
    }

    public static AddDialog newInstance(String topic) {
        AddDialog frag = new AddDialog();
        Bundle args = new Bundle();
        args.putString("topic", topic);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTopic = view.findViewById(R.id.txt_topic);
        edtName = view.findViewById(R.id.edt_name);

        String topic = getArguments().getString("topic", "Enter Name");
        getDialog().setTitle(topic);
    }

}
