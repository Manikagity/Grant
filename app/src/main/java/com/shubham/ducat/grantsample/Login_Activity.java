package com.shubham.ducat.grantsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Login_Activity extends Fragment {
    ImageView imageView;
EditText e1,e2;
CheckBox c1;
//static final String PREF="name";
Button b1;
SharedPreferences pref;
        SharedPreferences.Editor editor;

    String login_always;
String user_id,password;
    FragmentManager fm;
    FragmentTransaction ft;
String id_final;
String url_get_info="https://grantindiaall.000webhostapp.com/getDetails.php";

public static final String TAG_RESULT="result";
public static final String TAG_NAME="name";
public static final String TAG_PHONE="phone";
public static final String TAG_EMAIL="email";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_login,container,false);
        pref=getActivity().getSharedPreferences("mypref",0);
editor=pref.edit();
        e1=v.findViewById(R.id.editText_user);
        e2=v.findViewById(R.id.editText4_pass);
        b1=v.findViewById(R.id.button6);
        c1=v.findViewById(R.id.checkBox_remember);
        user_id=e1.getText().toString();
        //  id_final=user_id.substring(10);
        if(user_id.length()>10)
        {
            user_id=user_id.substring(10);
        }


        password=e2.getText().toString();
        if(!(user_id.equals("")) && !(password.equals("")))
        {
            b1.setEnabled(true);
        }

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked==true)
                {   login_always="yes";
                    editor.putString("login_key","yes");
                    editor.commit();
                    //CommonData.getInstance().setLogin(login_always);
                    Toast.makeText(getContext(), ""+login_always, Toast.LENGTH_SHORT).show();
                    b1.setEnabled(true);
                    CommonData.getInstance().setUserName(user_id);
                    CommonData.getInstance().setPassword(password);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // else{
                    user_id = e1.getText().toString();
                    password = e2.getText().toString();
              /*  if (user_id.equals("") || password.equals("")) {
                    Toast.makeText(Login_Activity.this, "Fill Details", Toast.LENGTH_SHORT).show();
                  //  onClick(v);
                } else {*/
                    new DataProcess4().execute();
                    //}
               // }
            }
        });
      //  ft.addToBackStack("");
        //ft.commit();
        return v;
    }



    class DataProcess4 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd2;

        @Override
        protected void onPreExecute() {
            pd2=new ProgressDialog(getContext());
            pd2.setMessage("Getting Details..");
            pd2.setCanceledOnTouchOutside(false);
            pd2.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", user_id));
            params.add(new BasicNameValuePair("password",password));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_get_info);
            InputStream inputStream = null;
            String result = null;
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity entity = httpResponse.getEntity();
                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");
                }
                result = sb.toString().trim();
            } catch (Exception e)

            {

            } finally {

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray(TAG_RESULT);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    String name1 = obj.getString(TAG_NAME);
                    if (name1.equals("0")) {
                        login_always="";
                        editor.putString("login_key","");
                        editor.commit();
                        //CommonData.getInstance().setLogin(login_always);
                        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    } else {

                        String phone1 = obj.getString(TAG_PHONE);
                        String email1 = obj.getString(TAG_EMAIL);
                        String address1=obj.getString("address");
                        String city1=obj.getString("city");
                        String subject1=obj.getString("subjects");
                        Toast.makeText(getContext(), "" + name1 + phone1 + email1, Toast.LENGTH_LONG).show();
                        editor.putString("name_key2",name1);
                        editor.putString("phone_key2",phone1);
                        editor.putString("email_key2",email1);
                        editor.putString("address_key2",address1);
                        editor.putString("subject_key2",subject1);
                        editor.commit();
                        CommonData.getInstance().setName1(name1);
                        CommonData.getInstance().setPhone1(phone1);
                        CommonData.getInstance().setEmail(email1);

                      //  FirstFragment firstFragment=new FirstFragment();
                       // Bundle b=new Bundle();
                        fm=getFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.tab3,new FirstFragment());   //ft.replace(R.id.main_page,new Second_Fragment()); jab koi object nhi banaya hua ho toh
                        ft.addToBackStack(null);   // to get back to the fragment
                        ft.commit();

                    /*a1.add((i+1)+")\nName : "+name1+"\nPhone : "+phone1+"\nEmail : "+email1+"\n");
                    name.add(name1);
                    phone.add(phone1);
                    email.add(email1);
                    onj.notifyDataSetChanged();*/
                    }
                }
                pd2.dismiss();

                // Toast.makeText(Main3Activity.this, "got done successfully", Toast.LENGTH_LONG).show();
                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }

    }

}
