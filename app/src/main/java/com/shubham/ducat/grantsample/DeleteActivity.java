package com.shubham.ducat.grantsample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
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

public class DeleteActivity extends AppCompatActivity {
ImageView imageView;
EditText e1,e2;
Button b1;
String url="https://grantindiaall.000webhostapp.com/delete.php";
String userid,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        e1=findViewById(R.id.editText4);
        e2=findViewById(R.id.editText5);
        b1=findViewById(R.id.button7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid=e1.getText().toString();
                pass=e2.getText().toString();

                new DataProcess6().execute();
            }
        });

    }
    class DataProcess6 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd2;

        @Override
        protected void onPreExecute() {
            pd2=new ProgressDialog(DeleteActivity.this);
            pd2.setMessage("Deleting Account..");
            pd2.setCanceledOnTouchOutside(false);
            pd2.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", userid));
            params.add(new BasicNameValuePair("password",pass));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
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
                        Toast.makeText(DeleteActivity.this, "Cannot be Deleted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DeleteActivity.this, "Account Deleted", Toast.LENGTH_LONG).show();
                        // Toast.makeText(EditProfile_Activity0.this, "" + name1 + phone1 + email1, Toast.LENGTH_LONG).show();




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
