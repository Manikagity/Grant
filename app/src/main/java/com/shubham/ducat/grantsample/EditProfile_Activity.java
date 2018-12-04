package com.shubham.ducat.grantsample;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class EditProfile_Activity extends AppCompatActivity {
    ImageView imageView,i1;
    EditText e1,e2,e3,e4,e5;
    MultiAutoCompleteTextView e6;
    Button b1,b2,b4;
    String url="https://grantindiaall.000webhostapp.com/setUpadate.php";
    CheckBox c1;
    Bitmap bmp;
    String sub[];
    LocationManager lm;
    Location l;
    double latitude,longitude;
    TextView t1,t2,t3,tlat;
    String id,name,phone,city,address,subject,email,lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);
            Bundle b=getIntent().getExtras();
            id=b.getString("id_key3");
            name=b.getString("name_key3");
            phone=b.getString("phone_key3");
            email=b.getString("email_key3");
            city=b.getString("city_key3");
            address=b.getString("address_key3");
            subject=b.getString("subjects_key3");
            lat=b.getString("latitude_key3");
            lon=b.getString("longitude_key3");


        e1=findViewById(R.id.editText_name);
        e2=findViewById(R.id.editText_num);
        e3=findViewById(R.id.editText_email);
        e4=findViewById(R.id.editText_address);
        e5=findViewById(R.id.editText_city);
        e6=findViewById(R.id.editText_subjects);
        b1=findViewById(R.id.button_photo);
        b2=findViewById(R.id.button_save);
        i1=findViewById(R.id.image_photo);
        c1=findViewById(R.id.checkbox);
        tlat=findViewById(R.id.lat_long);
        b4=findViewById(R.id.button_location);
        t1=findViewById(R.id.textView7);



        if(ActivityCompat.checkSelfPermission(EditProfile_Activity.this, ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(EditProfile_Activity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
           /* AlertDialog.Builder ab;
            ab.setCancelable(false);
            ab=new AlertDialog.Builder(this);
            ab.setTitle("PERMISSION");
            ab.setMessage("This App wants to use the location services of your device \n please allow it");
            ab.setPositiveButton("Allow",//Listener)*/

            ActivityCompat.requestPermissions(EditProfile_Activity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
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

                Geocoder geocoder = new Geocoder(EditProfile_Activity.this);
                try {
                    List<Address> adr = geocoder.getFromLocation(latitude, longitude, 1);
                    String country = adr.get(0).getCountryName();
                    String locality = adr.get(0).getLocality();
                    //  line=adr.get(0).getAddressLine(0);

                } catch (Exception e) {
                    Toast.makeText(EditProfile_Activity.this, "Not Working", Toast.LENGTH_LONG).show();
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

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tlat.setText("lat "+ latitude+ " long "+ longitude);
            }
        });
        e6.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        sub=getResources().getStringArray(R.array.sub);
        ArrayAdapter<String> obj=new ArrayAdapter<String>(EditProfile_Activity.this,android.R.layout.simple_list_item_1,sub);
        e6.setAdapter(obj);
        e6.setThreshold(1);

        e1.setText(name);
        e2.setText(phone);
        e3.setText(email);
        e4.setText(address);
        e5.setText(city);
        e6.setText(subject);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d=new Dialog(EditProfile_Activity.this);
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

                b2.setEnabled(isChecked);
                if(isChecked) {
                    name = e1.getText().toString();
                    phone = e2.getText().toString();
                    email = e3.getText().toString();
                    address = e4.getText().toString();
                    city = e5.getText().toString();
                    subject = e6.getText().toString();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             new DataProcess2().execute();

                Intent i=new Intent(EditProfile_Activity.this,Main3Activity.class);
                startActivity(i);

                //Toast.makeText(Register_Activity.this, "data saved"+phone, Toast.LENGTH_LONG).show();
            }
        });
    }

    class DataProcess2 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            pd=new ProgressDialog(EditProfile_Activity.this);
            pd.setMessage("Editing...");
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
            params.add(new BasicNameValuePair("id",id));
            params.add(new BasicNameValuePair("name",name));
            params.add(new BasicNameValuePair("phone",phone));  //key ("jo isme likhna h") should be same as the php file variables name
            params.add(new BasicNameValuePair("email",email));  //key ("jo isme likhna h") should be same as the php file variables name
            params.add(new BasicNameValuePair("address",address));
            params.add(new BasicNameValuePair("subjects",subject));
            params.add(new BasicNameValuePair("city",city));
            params.add(new BasicNameValuePair("latitude",String.valueOf(latitude)));
            params.add(new BasicNameValuePair("longitude",String.valueOf(longitude)));
            //params.add(new BasicNameValuePair("password",password));
            //params.add(new BasicNameValuePair("password",password));


            DefaultHttpClient httpClient=new DefaultHttpClient();  //httpclient se connect
            HttpPost httpPost=new HttpPost(url);   //to open the url for posting on server
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
                httpClient.execute(httpPost);  //to execute
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            // new DataProcess1().execute();
            Toast.makeText(EditProfile_Activity.this, "Data Edited successfully :)", Toast.LENGTH_LONG).show();
               e1.setText("");
             e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            // e7.setText("");
            //e8.setText("");

            super.onPostExecute(s);
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
            Uri u=data.getData();        //path of the image selected from gallery

            try {
                //InputStream input = getContentResolver().openInputStream(u);
                // bmp= BitmapFactory.decodeStream(input);                        {standard method}

                //OR
                bmp=MediaStore.Images.Media.getBitmap(getContentResolver(),u);   // it does not retrieve data
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


}

