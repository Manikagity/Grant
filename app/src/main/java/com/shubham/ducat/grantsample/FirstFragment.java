package com.shubham.ducat.grantsample;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    TextView t1,t2;
    SharedPreferences pref;
    String name,phone,email,address,city,subject;
    SharedPreferences.Editor editor;



    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_first, container, false);
        pref=getActivity().getSharedPreferences("mypref",0);
        editor=pref.edit();
        t1=v.findViewById(R.id.textView6_flag);
        t2=v.findViewById(R.id.textView6);
        t1.setPaintFlags(t1.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //name=CommonData.getInstance().getName1();
        //phone=CommonData.getInstance().getPhone1();
        //email=CommonData.getInstance().getEmail1();
        name=pref.getString("name_key2",null);
        phone=pref.getString("phone_key2",null);
        email=pref.getString("email_key2",null);
        address=pref.getString("address_key2",null);
        city=pref.getString("city_key2",null);
        subject=pref.getString("subject_key2",null);
        t2.setText("Welcome\n"+name+" \n"+phone+" \n"+email+"\n"+address+"\n"+city+"\n"+subject);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
                editor.clear();
                editor.commit();
                Intent i=new Intent(getActivity(),Main3Activity.class);
                startActivity(i);
                getActivity().getFragmentManager().popBackStack();
            }
        });
        // Inflate the layout for this fragment
        return v;


        /*
        if(!(logout_done.equals("")))
                {
                        ft.addbackonstack();
                        }
        */
    }

}
