package com.shubham.ducat.grantsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class EditProfile_Activity0 extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    String user_id,user_pass;
    ImageView imageView;
    String url_get_info="https://grantindiaall.000webhostapp.com/getDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_0);
        e1=findViewById(R.id.editText2);
        e2=findViewById(R.id.editText3);
        b1=findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = e1.getText().toString();
                user_pass = e2.getText().toString();
                new DataProcess5().execute();




                //logic to check password with id in database


            }
        });
    }

    class DataProcess5 extends AsyncTask<String,String,String>
    {

        ProgressDialog pd2;

        @Override
        protected void onPreExecute() {
            pd2=new ProgressDialog(EditProfile_Activity0.this);
            pd2.setMessage("Getting Details..");
            pd2.setCanceledOnTouchOutside(false);
            pd2.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", user_id));
            params.add(new BasicNameValuePair("password",user_pass));

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
                JSONArray array = object.getJSONArray("result");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    String name1 = obj.getString("name");
                    if (name1.equals("0")) {

                        //CommonData.getInstance().setLogin(login_always);
                        Toast.makeText(EditProfile_Activity0.this, "Login Failed", Toast.LENGTH_LONG).show();
                    } else {
                          String id1=obj.getString("id");
                        String phone1 = obj.getString("phone");
                        String email1 = obj.getString("email");
                        String address1=obj.getString("address");
                        String city1=obj.getString("city");
                        String subject1=obj.getString("subjects");
                        String latitude=obj.getString("latitude");
                        String longitude=obj.getString("longitude");
                       // Toast.makeText(EditProfile_Activity0.this, "" + name1 + phone1 + email1, Toast.LENGTH_LONG).show();

                        Intent in=new Intent(EditProfile_Activity0.this,EditProfile_Activity.class);
                        in.putExtra("id_key3",id1);
                        in.putExtra("name_key3",name1);
                        in.putExtra("phone_key3",phone1);
                        in.putExtra("email_key3",email1);
                        in.putExtra("address_key3",address1);
                        in.putExtra("city_key3",city1);
                        in.putExtra("subjects_key3",subject1);
                        in.putExtra("latitude_key3",latitude);
                        in.putExtra("longitude_key3",longitude);
                        startActivity(in);

                        CommonData.getInstance().setName1(name1);
                        CommonData.getInstance().setPhone1(phone1);
                        CommonData.getInstance().setEmail(email1);

                        //  FirstFragment firstFragment=new FirstFragment();
                        // Bundle b=new Bundle();
                    /*    fm=getFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.tab3,new FirstFragment());   //ft.replace(R.id.main_page,new Second_Fragment()); jab koi object nhi banaya hua ho toh
                        ft.addToBackStack(null);   // to get back to the fragment
                        ft.commit();*/

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater1=getMenuInflater();
        inflater1.inflate(R.menu.back_menu,menu);
        MenuItem menuItem1=menu.findItem(R.id.back);
        imageView= (ImageView) menuItem1.getActionView();
        // imageView.setImageResource(android.R.color.white);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.back)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
