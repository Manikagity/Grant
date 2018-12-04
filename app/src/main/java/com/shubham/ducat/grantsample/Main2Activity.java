package com.shubham.ducat.grantsample;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.shubham.ducat.grantsample.CommonData.getInstance;

public class Main2Activity extends AppCompatActivity {

    AutoCompleteTextView e1;
    TextView t1;
    String location;
    ListView l1;
    String[] a;
    Context cxt=this;
    ImageView i1;
    LocationManager lm;
    double latitude,longitude;
    String locality,loc;
    TextWatcher tw;
    ArrayAdapter<String> obj;
    String city="Ambaji City Map,Ayodhya Map,Abids City Map,Agra City Map,Ahmedabad City Map,Ahmednagar City Map,Alappuzha City Map,Allahabad City Map,Alwar City Map,Akola City Map,Alibag City Map,Almora City Map,Amlapuram City Map,Amravati City Map,Amritsar City Map,Anand City Map,Anandpur Sahib City Map,Angul City Map,Anna Salai City Map,Arambagh City Map,Asansol City Map,Ajmer City map ,Amreli City Map,Aizawl City Map,Agartala City Map,Aligarh City map,Auli City Map,Aurangabad City Map,Azamgarh City Map,Aurangabad City Map,Baran City Map,Bettiah City Map,Badaun City Map,Badrinath Map,Balasore City Map,Banaswara City Map,Bankura City Map,Ballia City ap,Bardhaman City Map,Baripada City Map,Barrackpore City Map,Barnala City Map,Barwani City Map,Beed City Map,Beawar City Map,Bellary City Map,Bhadohi City Map,Bhadrak City Map,Bharuch City Map,Bhilai City Map,Bhilwara City Map,Bhiwani City Map,Bidar City Map,Bilaspur City Map,Bangalore City Map,Bhind City Map,Bhagalpur City Map,Bharatpur City Map,Bhavnagar City Map,Bhopal City Map,Bhubaneshwar City Map,Bhuj City Map,Bilimora City Map,Bijapur City Map,Bikaner City Map,Bodh Gaya City Map,Bokaro City Map,Bundi City Map,Barasat City Map,Bareilly City Map,Basti City Map,Bijnor City Map,Burhanpur City Map,Buxur City Map,Calangute City Map,Chandigarh City Map,Chamba Map,Chandausi City Map,Chandauli City Map,Chandrapur City Map,Chhapra City Map,Chidambaram City Map,Chiplun City Map,Chhindwara City Map,Chitradurga City Map,Chittoor City Map,Cooch Behar City Map,Chennai City Map,Chittaurgarh City Map,Churu City Map,Coimbatore City Map,Cuddapah City Map,Cuttack City Map,Dahod City Map,Dalhousie City Map,Davangere City Map,Dehri City Map,Dewas City Map,Dwarka City Map,Dehradun City Map,Delhi City Map,Deoria City Map,Dhanbad City Map,Dharamshala Map,Dispur City Map,Dholpur City Map,Diu Island Map,Durgapur City Map,Didwana City Map,Ernakulam City Map,Etah City Map,Etawah City Map,Erode City Map,Faridabad City Map,Ferozpur City Map,Faizabad City Map,Fatehabad City Map,Firozabad City Map,Firozpur City Map ,Gandhinagar City Map,Gangapur City Map,Garia,Gaya City Map,Ghaziabad City Map,Godhra,Gokul City Map,Gonda City Map ,Gorakhpur City Map,Greater Mumbai City Map,Gulbarga City Map,Guna City Map,Guntur City Map ,Gurgaon City Map,Greater Noida City Map,Gulmarg City Map,Hanumangarh City Map,Haflong Map,Haldia City Map,Haridwar City Map,Hajipur City Map,Haldwani City Map,Hampi City Map,Hapur City Map,Hubli City Map,Hardoi City Map ,Hyderabad City Map,Guwahati City Map,Gangtok City Map,Gwalior City Map,Imphal City Map,Indore City Map,Itanagar City Map,Itarsi City Map,Jabalpur City Map,Jagadhri City Map,Jalna City Map,Jamalpur City Map,Jhajjar City Map,Jhalawar City Map,Jaipur City Map,Jaisalmer City Map,Jalandhar City Map,Jammu City Map,Jamshedpur City Map,Jhansi City Map ,Jaunpur City Map,Jodhpur City Map ,Junagadh City Map,Jalore City Map,Kishanganj City Map,Katihar City Map,Kanpur City Map,Kangra Map,Kasauli City Map,Kapurthala City Map,Kanchipuram City Map,Karnal City Map,Karaikudi City Map,Kanyakumari City Map,Katni City Map,Khajuraho City Map,Khandala City Map,Khandwa City Map,khargone City Map,Kishangarh,Kochi City Map,Kodaikanal City Map,Kohima City Map,Kolhapur City Map,Kolkata City Map,Kollam City Map,Kota City Map,Kottayam City Map,Kovalam City Map,Kozhikode City Map,Kumbakonam City Map,Kumarakom City Map,Kurukshetra City Map,Lalitpur City Map,Latur City Map,Lucknow City Map,Ludhiana City Map,Lavasa City Map,Leh City Map,Laxmangarh City Map  ,Madikeri City Map,Madurai City Map,Mahabaleshwar Map,Mahabalipuram City Map,Mahbubnagar City Map,Manali City Map,Mandu Fort Map,Mangalore City Map,Malegaon City Map,Manipal City Map,Margoa City Map,Mathura City Map,Meerut City Map,Mirzapur City Map,Mohali City Map,Morena City Map,Motihari City Map,Moradabad City Map,Mount Abu City Map,Mumbai City Map,Munger City Map,Munnar City Map,Mussoorie City Map,Mysore City Map,Muzaffarnagar City Map,Mokokchung City Map,Muktsar City Map,Nagpur City Map,Nagaon City Map,Nagercoil City Map,Naharlagun City Map,Naihati City Map,Nainital City Map,Nalgonda City Map,Namakkal City Map,Nanded City Map,Narnaul City Map,Nasik City Map,Nathdwara City Map,Navsari City Map,Neemuch City Map,Noida City Map,Ooty City Map,Orchha City Map,Palakkad City Map,Palanpur City Map,Pali City Map,Palwal City Map,Panaji City Map,Panipat City Map,Panvel City Map,Pathanamthitta City Map,Pandharpur City Map,Patna Sahib City Map,Panchkula City Map,Patna City Map,Periyar City Map,Phagwara City Map,Pilibhit City Map,Pinjaur City Map,Pollachi City Map,Pondicherry City Map,Ponnani City Map,Porbandar City Map,Port Blair City Map,Porur City Map,Pudukkottai City Map,Punalur City Map,Pune City Map,Puri City Map,Purnia City Map,Pushkar City Map,Patiala City Map  ,Raxual City Map,Rajkot City Map,Rameswaram City Map,Rajahmundry City Map,Ranchi City Map,Ratlam City Map,Raipur City Map,Rewa City Map,Rewari City Map,Rishikesh City Map,Rourkela City Map,Sitamrahi City Map,Sagar City Map,Sangareddy City Map,Saharanpur City Map,Salem City Map,Salt Lake City Map,Samastipur City Map,Sambalpur City Map,Sambhal City Map,Sanchi City Map,Sangli City Map,Sarnath City Map,Sasaram City Map,Satara City Map,Satna City Map,Secunderabad City Map,Sehore City Map,Serampore City Map,Sangrur City Map,Sirhind City Map,Shillong City Map,Shimla City Map,Shirdi Map,ShivaGanga City Map,Shivpuri City Map,Silvassa City Map,Singrauli City Map,Sirsa City Map,Sikar City Map,Siwan City Map,Somnath City Map,Sonipat City Map,Sopore City Map,Srikakulam City Map,Srirangapattna City Map,Srinagar City Map,Sultanpur City Map,Surat City Map,Surendranagar City Map,Suri City Map,Tawang City Map,Tezpur City Map,Thrippunithura City Map,Thrissur City Map,Tiruchchirappalli City Map,Tirumala City Map,Tirunelveli City Map,Thiruvannamalai City Map,Tirur City Map,Thalassery City Map,Thanjavur City Map,Thekkady City Map,Theni City Map,Thiruvananthpuram Map,Thiruvannamalai Map,Tirupati City Map,Trichy City Map,Trippur City Map,Tumkur City Map,Tuni City Map,Udaipur City Map,Udhampur City Map,Udupi City Map,Ujjain City Map,Unnao City Map,Ujjain Fort Map,Vidisha City Map,Vadodra City Map,Valsad City Map,Vapi City Map,Varanasi City Map,Varkala City Map,Vasco da Gama City Map,Vellore City Map,Vishakhapatnam City Map,Vijayawada City Map,Vizianagaram City Map,Vrindavan City Map,Warangal City Map,Washim City Map,Yamunanagar City Map,Yelahanka City Map";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Select Location");
        e1=findViewById(R.id.autoCompleteTextView);
        t1=findViewById(R.id.textView3);
       // i1=findViewById(R.id.imageView2);
        l1=findViewById(R.id.listView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        loc= CommonData.getInstance().getData();
        city=city.replace("City","");
        city=city.replace("Map","");

         a=city.split(",");
         /*i1.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onClick(View v) {
                 i1.setTooltipText("Please Register Yourself to avoid giving location again and again..");
             }
         });*/

         obj=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
         l1.setAdapter(obj);
         e1.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                 Main2Activity.this.obj.getFilter().filter(s);
             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });


      l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              e1.setText(l1.getItemAtPosition(position).toString());
              CommonData.getInstance().setData(e1.getText().toString());
              location =e1.getText().toString();
              try {

                  Thread.sleep(1200);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              DatabaseOperation db =new DatabaseOperation(cxt);
              db.putInformation(db,location);
              Intent i=new Intent(Main2Activity.this,Main3Activity.class);
              //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
              startActivity(i);
          }
      });
      t1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(ActivityCompat.checkSelfPermission(Main2Activity.this, ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                      &&
                      ActivityCompat.checkSelfPermission(Main2Activity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
              {
                  ActivityCompat.requestPermissions(Main2Activity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
                  return;
              }

              lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
              lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                  @Override
                  public void onLocationChanged(Location location) {
                      //  location=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                      longitude=location.getLongitude();
                      latitude=location.getLatitude();

                      Geocoder geocoder=new Geocoder(Main2Activity.this);
                      try
                      {
                          List<Address> adr=geocoder.getFromLocation(latitude,longitude,1);
                          String country=adr.get(0).getCountryName();
                          locality=adr.get(0).getLocality();
                          Intent i2=new Intent(Main2Activity.this,Main3Activity.class);
                          startActivity(i2);
                          //line=adr.get(0).getAddressLine(0);

                      }
                      catch (Exception e)
                      {
                          Toast.makeText(Main2Activity.this, "Not Working", Toast.LENGTH_LONG).show();
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


          }
      });


    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
