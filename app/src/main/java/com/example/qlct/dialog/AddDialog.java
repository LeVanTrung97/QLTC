package com.example.qlct.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.RelativeLayout;
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

    private static final int GALLERY_REQUEST_CODE = 1;
    private EditText edtTopic;
    private EditText edtName;
    private EditText edtAmount;
    private TextView txtTime;
    private ImageView imgTime;
    private ImageView imgBill;
    private Button btnSave;
    private Button btnCancel;
    private RelativeLayout loAddBill;

    private int type;
    private boolean addBill;
    private Callback callback;
    private Item item;

    public AddDialog() {
    }

    public static AddDialog newInstance(Item item, String topic, int type, Boolean addBill, Callback callback) {
        AddDialog frag = new AddDialog();
        Bundle args = new Bundle();
        args.putString("topic", topic);
        frag.setArguments(args);
        frag.callback = callback;
        frag.type = type;
        frag.addBill = addBill;
        if (item != null){
            frag.item = item;
        }
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
        imgBill = view.findViewById(R.id.img_bill);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);
        loAddBill = view.findViewById(R.id.lo_add_bill);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String topic = getArguments().getString("topic", "Enter Name");
        getDialog().setTitle(topic);

        if(item != null){
            edtTopic.setText(item.getTopic());
            edtName.setText(item.getName());
            edtAmount.setText(item.getAmount());
            txtTime.setText(item.getTime());
            if(item.getUrl() != ""){
                Uri uri = Uri.parse(item.getUrl());
                imgBill.setImageURI(uri);
            }
        } else {
            item = new Item();
            Date date = new Date();
            String strDateFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            txtTime.setText(sdf.format(date));

            imgBill.setImageResource(R.drawable.ic_launcher_background);
        }

        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        loAddBill.setVisibility(addBill ? View.VISIBLE : View.GONE);
        imgBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBillImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToRealm();
                callback.onResult(item);
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

    private void getBillImage() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    item.setUrl(String.valueOf(selectedImage));
                    imgBill.setImageURI(selectedImage);
                    break;
            }
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
        item.setType(type);
        item.setName(edtName.getText().toString());
        item.setTopic(edtTopic.getText().toString());
        item.setTime(txtTime.getText().toString());
        item.setAmount(edtAmount.getText().toString());
        //todo save to real
    }

    public interface Callback{
        void onResult(Item item);
    }

}
