package com.example.qlct.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlct.R;
import com.example.qlct.model.Item;
import com.example.qlct.realm.RealmController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDialog extends DialogFragment {

    private EditText edtTopic;
    private EditText edtName;
    private EditText edtAmount;
    private TextView txtTime;
    private ImageView imgTime;
    private Button btnSave;
    private Button btnCancel;

    private int type;
    private Callback callback;
    private Item item;

    public AddDialog() {
    }

    public static AddDialog newInstance(String topic, int type, Callback callback) {
        AddDialog frag = new AddDialog();
        Bundle args = new Bundle();
        args.putString("topic", topic);
        frag.setArguments(args);
        frag.callback = callback;
        frag.type = type;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add, container, false);
        edtTopic = view.findViewById(R.id.edt_topic);
        edtName = view.findViewById(R.id.edt_name);
        edtAmount = view.findViewById(R.id.edt_amount);
        txtTime = view.findViewById(R.id.txt_time);
        imgTime = view.findViewById(R.id.img_time);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String topic = getArguments().getString("topic", "Enter Name");
        getDialog().setTitle(topic);

        Date date = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        txtTime.setText(sdf.format(date));

        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToRealm();
                callback.onResult();
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void getDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear+=1;
                txtTime.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
            }
        }, year, month, day);
        datePickerDialog.show();
        datePickerDialog.setCancelable(false);
    }

    private void saveToRealm() {
        item = new Item(type, edtName.getText().toString(), edtTopic.getText().toString(), txtTime.getText().toString(), null, edtAmount.getText().toString(), null);
        //todo save to real
    }

    public interface Callback{
        void onResult();
    }

}
