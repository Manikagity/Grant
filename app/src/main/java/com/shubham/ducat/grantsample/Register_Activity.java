package com.shubham.ducat.grantsample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.*;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Register_Activity extends AppCompatActivity {
    ImageView imageView,i1;
    EditText e1,e2,e3,e4,e5,e7,e8;
    MultiAutoCompleteTextView e6;
    Button b1,b2,b3,b4;
    CheckBox c1;
    TextView tlat;
    LocationManager lm;
    String view;
    String image_name;
    double latitude,longitude;
    Location l;
    Uri u;
    DevicePolicyManager dpm;
    String sub[];
    ProgressDialog progressDialog;
    String JSONData;
    Bitmap bmp;
    boolean check=true;
    String image_codes;
  //  int id=CommonData.getInstance().getCount_id();
    TextView t1,t2,t3;
    String name,phone,city,address,subject,email,password,subject_array[],photo;
    String url="https://grantindiaall.000webhostapp.com/setdata.php";
    String url_get_id="https://grantindiaall.000webhostapp.com/getid.php";
    String url_upload="https://grantindiaall.000webhostapp.com/uploadPic1.php";
    String id;
    String name1,phone1,email1;

    public static final String TAG_RESULT="result";   //same tag name as in php file
    public static final String TAG_ID="id";
    public static final String TAG_NAME="name";  //same tag name as in php file
    public static final String TAG_EMAIL="email";   //same tag name as in php file
    public static final String TAG_PHONE="phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=findViewById(R.id.editText_name);
        e2=findViewById(R.id.editText_num);
        e3=findViewById(R.id.editText_email);
        e4=findViewById(R.id.editText_address);
        e5=findViewById(R.id.editText_city);
        e6=findViewById(R.id.editText_subjects);
        e7=findViewById(R.id.editText_password);
        e8=findViewById(R.id.editText_confirm_password);
        b1=findViewById(R.id.button_photo);
        b2=findViewById(R.id.button_save);
        i1=findViewById(R.id.image_photo);
        c1=findViewById(R.id.checkbox);
        b4=findViewById(R.id.button_location);
        tlat=findViewById(R.id.lat_long);
        t1=findViewById(R.id.textView7);
        b3=findViewById(R.id.button_verify);
        requestStoragePermission();

        if(ActivityCompat.checkSelfPermission(Register_Activity.this, ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(Register_Activity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
           /* AlertDialog.Builder ab;
            ab.setCancelable(false);
            ab=new AlertDialog.Builder(this);
            ab.setTitle("PERMISSION");
            ab.setMessage("This App wants to use the location services of your device \n please allow it");
            ab.setPositiveButton("Allow",//Listener)*/

            ActivityCompat.requestPermissions(Register_Activity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
            return;

        }



        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //  location=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                longitude = location.getLongitude();
                latitude = location.getLatitude();

                if (latitude == 0.00 || longitude == 0.00) {
                    tlat.setText("Try again from outside of building.");
                } else {
                    tlat.setText("latitude " + latitude + " \nlongitude " + longitude);
                }

                Geocoder geocoder = new Geocoder(Register_Activity.this);
                try {
                    List<Address> adr = geocoder.getFromLocation(latitude, longitude, 1);
                    String country = adr.get(0).getCountryName();
                    String locality = adr.get(0).getLocality();
                    //  line=adr.get(0).getAddressLine(0);

                } catch (Exception e) {
                    Toast.makeText(Register_Activity.this, "Not Working", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
      /* e6.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View v) {*/
      e6.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                    sub=getResources().getStringArray(R.array.sub);
                    ArrayAdapter<String> obj=new ArrayAdapter<String>(Register_Activity.this,android.R.layout.simple_list_item_1,sub);
                    e6.setAdapter(obj);
                    e6.setThreshold(1);
          //                     }
        //        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              tlat.setText(latitude+""+longitude);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d=new Dialog(Register_Activity.this);
                d.setContentView(R.layout.activity_custom_dialog1);
                t2=d.findViewById(R.id.textView15);
                t3=d.findViewById(R.id.textView17);
                d.show();
                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i, 101);
                        d.dismiss();
                    }
                });
                t3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(Intent.ACTION_PICK);
                        i.setType("image/*");
                        //i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i/*.createChooser(i,"Select Picture")*/,102);
                        d.dismiss();
                    }
                });
            }
        });

        b2.setEnabled(false);
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                name=e1.getText().toString();
                phone=e2.getText().toString();
                email=e3.getText().toString();
                address=e4.getText().toString();
                city=e5.getText().toString();
                subject=e6.getText().toString();
                if(name.equals("")||phone.equals("")||email.equals("")||address.equals("")||tlat.getText().toString().equals("0.000.00")||city.equals("")||subject.equals("")||i1.getDrawable()==null) {
                    if (name.isEmpty()) {
                        e1.setError("enter your name");
                    }

                    if(i1.getDrawable()==null){
                        Toast.makeText(Register_Activity.this, "Please select image", Toast.LENGTH_SHORT).show();
                    }
                    if (phone.isEmpty()) {
                        e2.setError("enter phone number");
                    }
                    if (email.isEmpty()) {
                        e3.setError("enter your email");
                    }
                    if(tlat.getText().toString().isEmpty())
                    {
                        tlat.setError("if latitude and longitude are zero or null please get out of your building and take the desired location");
                    }
                    if(tlat.getText().toString().equals("0.000.00"))
                    {
                        tlat.setError("if latitude and longitude are zero or null please get out of your building and take the desired location");
                    }
                    if(latitude==0.00)
                    {
                        tlat.setError("if latitude and longitude are zero or null please get out of your building and take the desired location");
                    }
                    if(longitude==0.00)
                    {
                        tlat.setError("if latitude and longitude are zero or null please get out of your building and take the desired location");
                    }
                    if (address.isEmpty()) {
                        e4.setError("enter tution address");
                    }
                    if (city.isEmpty()) {
                        e5.setError("enter city of your tution");
                    }
                    if (subject.isEmpty()) {
                        e6.setError("atleast add one subject");
                    }
                }
                else
                {
                    b2.setEnabled(true);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=e1.getText().toString();
                phone=e2.getText().toString();
                email=e3.getText().toString();
                address=e4.getText().toString();
                city=e5.getText().toString();
                subject=e6.getText().toString();
                subject_array=subject.split(",");
                phone=phone.substring(4);
                String confirm;
                password=e7.getText().toString();
                confirm=e8.getText().toString();
                if(password.equals(confirm))
                {   //id++;
                    String user_id=String.valueOf(id);   //mail par send karna
                    user_id="grant_user"+user_id;
                    new DataProcess().execute();
                  //  new UploadImage().execute(bmp);

                  //  Toast.makeText(Register_Activity.this, "Data Saved..", Toast.LENGTH_LONG).show();


                }

                else {
                    Toast.makeText(Register_Activity.this, "password not matched", Toast.LENGTH_LONG).show();
                }
//
               // finish();

                //Toast.makeText(Register_Activity.this, "data saved"+phone, Toast.LENGTH_LONG).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = e1.getText().toString();
                phone1 = e2.getText().toString();
                phone1 = phone1.substring(4);
                email1 = e3.getText().toString();
                if (ActivityCompat.checkSelfPermission(Register_Activity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Register_Activity.this, new String[]{Manifest.permission.SEND_SMS}, 0);
                    return;
                }
 //                   if(ActivityCompat.checkSelfPermission(Register_Activity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
             new DataProcess1().execute();





            }
        });
    //    new DataProcess1().execute();

    }



    class DataProcess extends AsyncTask<String,String,String>
    {   ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            pd=new ProgressDialog(Register_Activity.this);
            pd.setMessage("Uploading...");
            pd.setCanceledOnTouchOutside(false);
            pd.setButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name",name));
            params.add(new BasicNameValuePair("phone",phone));  //key ("jo isme likhna h") should be same as the php file variables name
            params.add(new BasicNameValuePair("email",email));  //key ("jo isme likhna h") should be same as the php file variables name
            params.add(new BasicNameValuePair("address",address));
            params.add(new BasicNameValuePair("subjects",subject));
            params.add(new BasicNameValuePair("city",city));
            params.add(new BasicNameValuePair("password",password));
            params.add(new BasicNameValuePair("latitude",String.valueOf(latitude)));
            params.add(new BasicNameValuePair("longitude",String.valueOf(longitude)));
            //params.add(new BasicNameValuePair("password",password));

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
                pd.dismiss();
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray(TAG_RESULT);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    String name1 = obj.getString(TAG_NAME);
                    if (name1.equals("0")) {
                        // login_always="";
                        pd.dismiss();
                        //editor.putString("login_key","");
                        //editor.commit();
                        //CommonData.getInstance().setLogin(login_always);
                        Toast.makeText(Register_Activity.this, "This Email and Phone has already registered", Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(Register_Activity.this, ""+name1, Toast.LENGTH_SHORT).show();

                        pd.dismiss();
                        // new DataProcess1().execute();
                        Toast.makeText(Register_Activity.this, "Data saved successfully :)", Toast.LENGTH_LONG).show();
                        //   e1.setText("");
                        // e2.setText("");
                        //e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        e6.setText("");
                        // e7.setText("");
                        e8.setText("");
                    }
                }


                super.onPostExecute(s);
            }
            catch (Exception e)
            {

            }
        }
    }

    class RequestHandler {

        public String sendGetRequest(String uri) {
            try {
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String result;

                StringBuilder sb = new StringBuilder();

                while((result = bufferedReader.readLine())!=null){
                    sb.append(result);
                }

                return sb.toString();
            } catch (Exception e) {
                return null;
            }
        }

        public String sendPostRequest(String requestURL,
                                      HashMap<String, String> postDataParams) {

            URL url;
            String response = "";
            try {
                url = new URL(requestURL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);


                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    response = br.readLine();
                } else {
                    response = "Error Registering";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return result.toString();
        }
    }




    class UploadImage extends AsyncTask<Bitmap,Void,String>{

        ProgressDialog loading;
        RequestHandler rh = new RequestHandler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(Register_Activity.this, "Uploading Image", "Please wait...",true,true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading.dismiss();
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Bitmap... params) {
            Bitmap bitmap = params[0];
           // String uploadImage =;

            HashMap<String,String> data = new HashMap<>();
            data.put("image", image_codes);

            String result = rh.sendPostRequest(url_upload,data);

            return result;
        }
    }





//    @SuppressLint("StaticFieldLeak")
    class DataProcess1 extends AsyncTask<String,String,String> {
    ProgressDialog pd1;

    @Override
    protected void onPreExecute() {

        pd1 = new ProgressDialog(Register_Activity.this);
        pd1.setMessage("Fetching Data...");
        pd1.setCanceledOnTouchOutside(false);
        pd1.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        //          Toast.makeText(Register_Activity.this, "Bye preExece", Toast.LENGTH_SHORT).show();
        //      e4.setText("start");
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name1));
        params.add(new BasicNameValuePair("phone", phone1));  //key ("jo isme likhna h") should be same as the php file variables name
        params.add(new BasicNameValuePair("email", email1));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url_get_id);
        InputStream inputStream = null;
        String result = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
            HttpResponse httpResponse = httpClient.execute(httpPost);


            //result = EntityUtils.toString(httpResponse.getEntity());
            HttpEntity entity = httpResponse.getEntity();


//            e4.setText("2");
            httpPost.setHeader("Content-Type", "application/json");


            //   HttpResponse httpResponse = httpClient.execute(httpPost);


            inputStream = entity.getContent();

            //jsonis utf-8 by default

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();  //mutable(string change karsakta h)
            // stringbuilder ke methods synchronised means objects ke formn mei check kata h
            //string buffer ke non synchronised means ek ek charachter check hota h
            String line = null;
            int j = 0;
            String answer = "";

            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");
            }
            result = sb.toString().trim();


            // e5.setText("result  " + result + "  " + j);
/*
         //   JSONObject object = new JSONObject(result);
          //  JSONArray array = object.getJSONArray(TAG_RESULT);
            // for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString(TAG_ID);
                Toast.makeText(Register_Activity.this, "bilkul sahi" + id, Toast.LENGTH_SHORT).show();
            }*/

        } catch (Exception e) {


        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (Exception e) {

            }

        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {

        pd1.dismiss();
        super.onPostExecute(s);
        String answer, password;
        password = e7.getText().toString();
        answer = s.substring(16);
        String[] s1 = answer.split(",");
        int len = s1[0].length();
        view = s1[0].substring(2, len - 1);
        String view1 = ("grant_user").concat(view);
        String data = "grant Welcomes You to our family.\nYour user id and password are\nUser ID = " + view1 + "\nPassword = " + password;

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone1, null, data, null, null);

        Toast.makeText(Register_Activity.this, "Sent an message for confirmation", Toast.LENGTH_SHORT).show();
        //e1.setText("");
        e2.setText("");
        e3.setText("");
        image_name =e1.getText().toString();
       // if(bmp!=null){
        //if(i1.getDrawable()!=null) {
            ImageUploadToServerFunction();

    }

}

        public void ImageUploadToServerFunction(){
            //final ProgressDialog[] progressDialog = new ProgressDialog[1];
            ByteArrayOutputStream byteArrayOutputStreamObject ;

            byteArrayOutputStreamObject = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

            byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

            final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

            class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

                @Override
                protected void onPreExecute() {

                    super.onPreExecute();

                    progressDialog = ProgressDialog.show(Register_Activity.this,"Image is Uploading","Please Wait",false,false);
                }

                @Override
                protected void onPostExecute(String string1) {

                    super.onPostExecute(string1);

                    // Dismiss the progress dialog after done uploading.
                    progressDialog.dismiss();
//e3.setText(string1);
                    // Printing uploading success message coming from server on android app.
                    Toast.makeText(Register_Activity.this,string1,Toast.LENGTH_LONG).show();

                    // Setting image as transparent after done uploading.
//                    imageView.setImageResource(android.R.color.transparent);


                }

                @Override
                protected String doInBackground(Void... params) {

                    ImageProcessClass imageProcessClass = new ImageProcessClass();

                    HashMap<String,String> HashMapParams = new HashMap<String,String>();

                  //  HashMapParams.put("id",view);
                    HashMapParams.put("url", ConvertImage);
                    HashMapParams.put("img_name",image_name);




                    String FinalData = imageProcessClass.ImageHttpRequest(url_upload, HashMapParams);

                    return FinalData;
                }
            }
            AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

            AsyncTaskUploadClassOBJ.execute();
        }

        public class ImageProcessClass{

            public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

                StringBuilder stringBuilder = new StringBuilder();

                try {

                    URL url;
                    HttpURLConnection httpURLConnectionObject ;
                    OutputStream OutPutStream;
                    BufferedWriter bufferedWriterObject ;
                    BufferedReader bufferedReaderObject ;
                    int RC ;

                    url = new URL(requestURL);

                    httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                    httpURLConnectionObject.setReadTimeout(19000);

                    httpURLConnectionObject.setConnectTimeout(19000);

                    httpURLConnectionObject.setRequestMethod("POST");

                    httpURLConnectionObject.setDoInput(true);

                    httpURLConnectionObject.setDoOutput(true);

                    OutPutStream = httpURLConnectionObject.getOutputStream();

                    bufferedWriterObject = new BufferedWriter(

                            new OutputStreamWriter(OutPutStream, "UTF-8"));

                    bufferedWriterObject.write(bufferedWriterDataFN(PData));

                    bufferedWriterObject.flush();

                    bufferedWriterObject.close();

                    OutPutStream.close();

                    RC = httpURLConnectionObject.getResponseCode();

                    if (RC == HttpsURLConnection.HTTP_OK) {

                        bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                        stringBuilder = new StringBuilder();

                        String RC2;

                        while ((RC2 = bufferedReaderObject.readLine()) != null){

                            stringBuilder.append(RC2);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return stringBuilder.toString();
            }

            private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

                StringBuilder stringBuilderObject;

                stringBuilderObject = new StringBuilder();

                for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                    if (check) {

                        check = false;
                    }else{
                        stringBuilderObject.append("&");}

                    stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                    stringBuilderObject.append("=");

                    stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
                }

                return stringBuilderObject.toString();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            //Bundle b = data.getExtras();
            //bmp = (Bitmap) b.get("data");
            u=data.getData();        //path of the image selected from gallery

            try {
                //InputStream input = getContentResolver().openInputStream(u);
                // bmp= BitmapFactory.decodeStream(input);                        {standard method}

                //OR
                bmp=MediaStore.Images.Media.getBitmap(getContentResolver(),u);


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                image_codes= Base64.encodeToString(imageBytes, Base64.DEFAULT);
                //return encodedImage;


                // it does not retrieve data
            } catch (IOException e) {
                e.printStackTrace();
            }
            i1.setImageBitmap(bmp);
        }
        else if(requestCode==101&&resultCode==RESULT_OK&&data!=null)
        {
            Bundle b=data.getExtras();
            bmp= (Bitmap) b.get("data");
            i1.setImageBitmap(bmp);
        }
        else {
            Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getPath(){
        Cursor cursor=getContentResolver().query(u,null,null,null,null);
        cursor.moveToFirst();
        String document_id=cursor.getString(0);
        document_id=document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();
        cursor=getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,MediaStore.Images.Media._ID+"=?",new String[]{document_id},null);
        cursor.moveToFirst();
        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
                return path;

    }


    private void uploadImagefinal(){
        String image_name=e1.getText().toString();
        String path=getPath();
        try{

            String upload_id= UUID.randomUUID().toString();
            new MultipartUploadRequest(Register_Activity.this,upload_id,url_upload)
                    .addFileToUpload(path,"image")
                    .addParameter("img_name",image_name)
                    .addParameter("id",view)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {

        }
    }
    private void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(Register_Activity.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ActivityCompat.requestPermissions(Register_Activity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==3 )
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
