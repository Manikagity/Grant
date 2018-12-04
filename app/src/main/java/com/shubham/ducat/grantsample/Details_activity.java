package com.shubham.ducat.grantsample;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ImageReader;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Details_activity extends AppCompatActivity {
    String id=CommonData.getInstance().getUser_id();
    String url="https://grantindiaall.000webhostapp.com/getfullDetails.php";
    String url_plus="https://grantindiaall.000webhostapp.com/followersPlus.php";
    String follower;
    String img_url;
    String url_minus="https://grantindiaall.000webhostapp.com/followersMinus.php";
    String url_rating="https://grantindiaall.000webhostapp.com/rating.php";
    String url_rating_minus="https://grantindiaall.000webhostapp.com/rating_minus.php";
    float ratings;
    float ratefinal;
    String latitude,longitude;
    double lat,lon;
    float ratefloat;

    ImageView profileImg;
    Button b1;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String name1;
    float rate;
float getRate;
    CheckBox c1;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    RatingBar r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        c1=findViewById(R.id.checkBox);
        profileImg=findViewById(R.id.image_profile_1);
        t1=findViewById(R.id.textView18);
        b1=findViewById(R.id.button_directions);
        t2=findViewById(R.id.textView20);
        t3=findViewById(R.id.email_profile);
        r1=findViewById(R.id.ratingBar);
        t4=findViewById(R.id.phone_profile);
        t5=findViewById(R.id.address_profile);
        t6=findViewById(R.id.subject_profile);
        t7=findViewById(R.id.average_rating);
        t8=findViewById(R.id.five_rating);
        t9=findViewById(R.id.four_rating);
        t10=findViewById(R.id.three_rating);
        t11=findViewById(R.id.two_rating);
        t12=findViewById(R.id.one_rating);
        //com.google.android.apps.maps
        pref=getApplicationContext().getSharedPreferences("mypref",0);
        editor=pref.edit();

/*        if(pref.contains("value_key")) {
            String val = pref.getString("value_key", null);
            rate = Float.parseFloat(val);
         //   r1.setRating(rate);
            new DataProcess10().execute();
        }
        else
        {
            rate=0;
           // r1.setRating(0);
        }*/
     //   Toast.makeText(this, "helloo "+id, Toast.LENGTH_SHORT).show();
        DataOperations3 db2=new DataOperations3(Details_activity.this);
        Cursor cr2=db2.getInformation2(db2);
        cr2.moveToFirst();
        if(cr2.moveToFirst()) {
            do {
               // Toast.makeText(Details_activity.this, "" + cr.getString(0), Toast.LENGTH_SHORT).show();
                String id2 = cr2.getString(0);
                if (id.equals(id2)) {
                     //Toast.makeText(Details_activity.this, "jkhjhhphggfpppppp", Toast.LENGTH_SHORT).show();
try {
    //c1.setEnabled(true);
    String ratee = cr2.getString(1);
    getRate = Float.parseFloat(ratee);
    r1.setRating(getRate);
}
catch (Exception e)
{

}
                } else {
                    getRate= (float) 0.00;
                    r1.setRating((float) 0.00);
                    //c1.setEnabled(false);
                }

            } while (cr2.moveToNext());
        }
        else
        {
            Toast.makeText(Details_activity.this, "No data found", Toast.LENGTH_SHORT).show();
        }


        DataOperations2 db1=new DataOperations2(Details_activity.this);
        Cursor cr=db1.getInformation2(db1);
        cr.moveToFirst();
        if(cr.moveToFirst()) {
            do {
               // Toast.makeText(Details_activity.this, "" + cr.getString(0), Toast.LENGTH_SHORT).show();
                String id2 = cr.getString(0);
                if (id.equals(id2)) {
                   // Toast.makeText(Details_activity.this, "pppppppppppppppppppppppppppppp", Toast.LENGTH_SHORT).show();

                    //c1.setEnabled(true);
                    c1.setChecked(true);
                } else {
                    c1.setChecked(false);
                    //c1.setEnabled(false);
                }

            } while (cr.moveToNext());
        }
        else
        {
            Toast.makeText(Details_activity.this, "No data found", Toast.LENGTH_SHORT).show();
        }




        new DataProcess7().execute();

        r1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(getRate!=0.00)
                {
                //    Toast.makeText(Details_activity.this, "changing rate", Toast.LENGTH_SHORT).show();
                  //ratefinal=pref.getString("value_key",null);
                  ratefinal =getRate;
                  ratings=rating;
                  DataOperations3 db2=new DataOperations3(Details_activity.this);
                  db2.updateInformation2(db2,id,String.valueOf(rating));

                    new DataProcess11().execute();
                    new DataProcess10().execute();
                }
else {

              //      Toast.makeText(Details_activity.this, "are mujhe dalna h", Toast.LENGTH_SHORT).show();
                    DataOperations3 db = new DataOperations3(Details_activity.this);
                    db.putInformation2(db,id,String.valueOf(rating));
               /* editor.putString("rating_key","y");
                ratings=rating;
                String value=String.valueOf(rating);
                editor.putString("value_key",value);
                editor.commit();*/
               ratings=rating;
                    Toast.makeText(Details_activity.this, "" + rating, Toast.LENGTH_SHORT).show();
                    new DataProcess10().execute();
                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phn=t4.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phn));
                if (ActivityCompat.checkSelfPermission(Details_activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Details_activity.this,new String[]{Manifest.permission.CALL_PHONE},0);
                    return;
                }
                startActivity(i);
            }
        });

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    DataOperations2 db=new DataOperations2(Details_activity.this);
                    db.putInformation2(db,id,"y");
                    //editor.putString("follow_key","y");
                   follower="y";
                   //editor.commit();


                    new DataProcess8().execute();

                }
                else
                {
                    follower="n";
                    DataOperations2 db=new DataOperations2(Details_activity.this);
                    db.deleteRecord(db,id);
                   // editor.clear();
                //editor.commit();
                new DataProcess9().execute();

                }

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ApplicationInfo> packages;
                PackageManager pm;

                pm = getPackageManager();
                packages = pm.getInstalledApplications(0);
                for (ApplicationInfo packageInfo : packages) {
                    if (packageInfo.packageName.equals("com.google.android.apps.maps")) {

                        //Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                        String label=name1+" coaching";  //jo name tum destiation mei dikhana chahte ho wo name label mei daal do
                        String uribegin="geo:"+latitude+","+longitude;
                        String query=latitude+","+longitude+"("+label+")";
                        String encodedquery= Uri.encode(query);
                        String uri=uribegin+"?q="+encodedquery+"&z=16";
                        Uri u=Uri.parse(uri);
                        Intent i=new Intent(Intent.ACTION_VIEW,u);
                        startActivity(i);
                        //startActivity(i);

                    } else {
                        try {
                            Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));

                            startActivity(i);
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(Details_activity.this, "Get Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }


    class DataProcess10 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Details_activity.this);
            pd1.setMessage("Wait...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("ratecurrent",String.valueOf(ratings)));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_rating);
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
                    //  String id1=obj.getString("id");
                    //String name1 = obj.getString("name");
                    int rate5=obj.getInt("rate5");
                    int rate1=obj.getInt("rate1");
                    int rate4=obj.getInt("rate4");
                    int rate3=obj.getInt("rate3");
                    int rate2=obj.getInt("rate2");
                    double avg=obj.getDouble("avgrate");
                    t7.setText(String.valueOf(avg));
                    t8.setText(String.valueOf("5 stars -" + rate5+ " users "));
                    t9.setText(String.valueOf("4 stars -" +rate4+ " users"));
                    t10.setText(String.valueOf("3 stars -"+ rate3+ " users"));
                    t11.setText(String.valueOf("2 stars -"+ rate2+ " users"));
                    t12.setText(String.valueOf("1 star -" +rate1+ " users"));
                    //String follow1=String.valueOf(follow);
                    //t1.setText(name1);


                }

                pd1.dismiss();

                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }

    class DataProcess11 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Details_activity.this);
            pd1.setMessage("Wait...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("ratecurrent",String.valueOf(ratefinal)));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_rating_minus);
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
                    //  String id1=obj.getString("id");
                    //String name1 = obj.getString("name");
                    int rate5=obj.getInt("rate5");
                    int rate1=obj.getInt("rate1");
                    int rate4=obj.getInt("rate4");
                    int rate3=obj.getInt("rate3");
                    int rate2=obj.getInt("rate2");
                    double avg=obj.getDouble("avgrate");
                    t7.setText(String.valueOf(avg));
                    t8.setText(String.valueOf("5 stars -" + rate5 + " users"));
                    t9.setText(String.valueOf("4 stars -" +rate4 + " users"));
                    t10.setText(String.valueOf("3 stars -"+ rate3+ " users"));
                    t11.setText(String.valueOf("2 stars -"+ rate2+ " users"));
                    t12.setText(String.valueOf("1 star -" +rate1+" users "));
                    //String follow1=String.valueOf(follow);
                    //t1.setText(name1);


                }

                pd1.dismiss();

                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }
    class LoadImage extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            //1 url
            Bitmap bitmap = null;
            Bitmap bitmap1=null;
            if(args.length == 1){
                Log.i("doInBack 1","length = 1 ");
                try {
                     bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

                     bitmap1=Bitmap.createScaledBitmap(bitmap,150,150,true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return bitmap1;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                profileImg.setImageBitmap(image);

            }
        }
    }

    class DataProcess9 extends AsyncTask<String,String,String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Details_activity.this);
            pd1.setMessage("Wait...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_minus);
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
                    //  String id1=obj.getString("id");
                    //String name1 = obj.getString("name");
                    int follow=obj.getInt("followers");
                    String follow1=String.valueOf(follow);
                    //t1.setText(name1);
                    t2.setText(follow1);

                }

                pd1.dismiss();

                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }


    class DataProcess8 extends AsyncTask<String,String,String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Details_activity.this);
            pd1.setMessage("Wait...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_plus);
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
                    //  String id1=obj.getString("id");
                    //String name1 = obj.getString("name");
                    int follow=obj.getInt("followers");
                    String follow1=String.valueOf(follow);
                    //t1.setText(name1);
                    t2.setText(follow1);

                }

                pd1.dismiss();

                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }

    class DataProcess7 extends AsyncTask<String,String,String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Details_activity.this);
            pd1.setMessage("Fetching Data...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));

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
                  //  String id1=obj.getString("id");
                    name1 = obj.getString("name");
                    int follow=obj.getInt("followers");
                    String follow1=String.valueOf(follow);
                    String email=obj.getString("email");
                    String phone=obj.getString("phone");
                    String add=obj.getString("address");
                    String cit=obj.getString("city");
                    String sub=obj.getString("subjects");
                    latitude=obj.getString("latitude");
                    longitude=obj.getString("longitude");
                    img_url=obj.getString("url");
                    int rate5=obj.getInt("rate5");
                    int rate1=obj.getInt("rate1");
                    int rate4=obj.getInt("rate4");
                    int rate3=obj.getInt("rate3");
                    int rate2=obj.getInt("rate2");
                    double avg=obj.getDouble("avgrate");
                    t7.setText(String.valueOf(avg));
                    t8.setText(String.valueOf("5 stars -" + rate5 + " users"));
                    t9.setText(String.valueOf("4 stars -" +rate4 + " users"));
                    t10.setText(String.valueOf("3 stars -"+ rate3+ " users"));
                    t11.setText(String.valueOf("2 stars -"+ rate2+ " users"));
                    t12.setText(String.valueOf("1 star -" +rate1+" users "));
                    t5.setText(add+" "+cit);
                    t6.setText(sub);
                    t4.setText(phone);
                    t1.setText(name1);
                    t2.setText(follow1);
                    t3.setText(email);

                }
                //Toast.makeText(Details_activity.this, "Following details updated", Toast.LENGTH_LONG).show();

                pd1.dismiss();
                new LoadImage().execute(img_url);
                /*
                 imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(MainActivity.this));

        image = (ImageView) findViewById(R.id.image);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrl, image);

                 */

                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }
}
