package com.wolfie.waterreminder;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;


public class setting extends Fragment {
ListView lv,lv2;
    TextView tv,tv1;
    AlertDialog.Builder builder;
    public setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        SharedPreferences pref =this.getActivity().getSharedPreferences("BasicInfo",0);
        Float weight = pref.getFloat("Weight",0);
        lv = view.findViewById(R.id.p_lv);
        lv2 = view.findViewById(R.id.glv);
        tv = view.findViewById(R.id.textView9);
       tv1 = view.findViewById(R.id.textView5);
        SharedPreferences pref1 = this.getActivity().getSharedPreferences("gender",0);
        String gender = pref1.getString("gender","Male");
        tv1.setText(gender);
        tv.setText(""+weight+" Kg");
        ArrayList<String> ar = new ArrayList<>();
        ar.add("Gender");
        ar.add("Weight");
        ar.add("Wake Up Time");
        ar.add("Bed Time");
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,ar)
        { @Override
        public View getView(int position, View convertView, ViewGroup parent){
            // Get the Item from ListView
            View view = super.getView(position, convertView, parent);

            // Initialize a TextView for ListView each Item
            TextView tv = (TextView) view.findViewById(android.R.id.text1);

            // Set the text color of TextView (ListView Item)
            tv.setTextColor(Color.parseColor("#FF1A237E"));

            // Generate ListView Item using TextView
            return view;
        }
    };
        lv.setAdapter(adapter);
        glv();
    return view;
    }

void glv(){
    ArrayList<String> ar = new ArrayList<>();
    ar.add("Why App is not working for you");
    ar.add("Privacy Policy");
    ar.add("Reset Data");
    ArrayAdapter<String>adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,ar)
    { @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the Item from ListView
        View view = super.getView(position, convertView, parent);

        // Initialize a TextView for ListView each Item
        TextView tv = (TextView) view.findViewById(android.R.id.text1);

        // Set the text color of TextView (ListView Item)
        tv.setTextColor(Color.parseColor("#FF1A237E"));

        // Generate ListView Item using TextView
        return view;
    }
    };
    lv2.setAdapter(adapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position==0)
            {
                gender();
            }
            else if(position==1)
            {
weight();
            }
        }
    });
}
void gender()
{           final SharedPreferences pref = this.getActivity().getSharedPreferences("gender",0);
    final SharedPreferences.Editor editor = pref.edit();
    new FancyGifDialog.Builder(this.getActivity())
            .setTitle("Please Choose Your Gender")
            .setNegativeBtnText("Male")
            .setPositiveBtnBackground("#ffb6c1")
            .setPositiveBtnText("Female")
            .setNegativeBtnBackground("#add8e6")
            .setGifResource(R.drawable.gender)   //Pass your Gif here
            .isCancellable(true)
            .OnPositiveClicked(new FancyGifDialogListener() {
                @Override
                public void OnClick() {
editor.putString("gender","Female");
editor.commit();
                    String gender = pref.getString("gender","");
                    tv1.setText(gender);
                }
            })
            .OnNegativeClicked(new FancyGifDialogListener() {
                @Override
                public void OnClick() {
editor.putString("gender","Male");
editor.commit();
                    String gender = pref.getString("gender","");
                    tv1.setText(gender);
                }
            })
            .build();
}

void weight()
{
    LayoutInflater li = LayoutInflater.from(this.getActivity());
    View promptsView = li.inflate(R.layout.weightchange, null);
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            this.getActivity(),R.style.CustomAlertDialog);
    alertDialogBuilder.setView(promptsView);


    alertDialogBuilder
            .setCancelable(true);

    // create alert dialog
    AlertDialog alertDialog = alertDialogBuilder.create();

    // show it
    alertDialog.show();
    alertDialog.getWindow().setLayout(750, 835);



}
}

