package com.shubham.ducat.grantsample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
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
import java.util.ArrayList;

import static com.shubham.ducat.grantsample.R.id.action0;
import static com.shubham.ducat.grantsample.R.id.start;

public class Main3Activity extends AppCompatActivity implements TabHost.OnTabChangeListener{
    String loc = CommonData.getInstance().getData();
    Spinner s1;
    Context cxt=this;
    FragmentManager fm;
    FragmentTransaction ft;
    String login_detail=CommonData.getInstance().getLogin();
    String id2=CommonData.getInstance().getUser_id();
    TabHost tabHost;
    ListView l1;
    Button edit,delete,register,login;
    EditText  e2;
    String subject;
    AutoCompleteTextView e1;
    WebView w1;
    TextView t8;
    String url_get_teacher="https://grantindiaall.000webhostapp.com/getdata.php";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ImageView i1, i2;
    String flag="";
    android.app.ActionBar actionBar;
    ImageView imageView;
   // CustomAdapter onj;
    String location_set,key;
   ArrayAdapter<String> onj;
    ArrayList<String> a1=new ArrayList<String>();
    ArrayList<String> loc2 = CommonData.getInstance().getData2();
    ArrayList<String> idr=new ArrayList<String>();
    ArrayList<String> name=new ArrayList<String>();
    ArrayList<String> phone=new ArrayList<String>();
    ArrayList<String> email=new ArrayList<String>();

    public static final String TAG_RESULT="result";
    public static final String TAG_NAME="name";
    public static final String TAG_PHONE="phone";
    public static final String TAG_EMAIL="email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle(".grant.");
        pref=getApplicationContext().getSharedPreferences("mypref",Context.MODE_PRIVATE);
        editor = pref.edit();
     //   editor.putString("login_key",login_detail);
        Toast.makeText(cxt, ""+pref.getString("login_key","")+"open", Toast.LENGTH_SHORT).show();
        editor.commit();

        tabHost = findViewById(R.id.tabs);
        l1 = findViewById(R.id.list);
        t8=findViewById(R.id.textView8);
        register=findViewById(R.id.button);
        e1 = findViewById(R.id.editText);
        edit=findViewById(R.id.button2);
        delete=findViewById(R.id.button3);
        login=findViewById(R.id.button5);
        //  e2=findViewById(R.id.editText2);
        i1 = findViewById(R.id.search_button);
        //  i2=findViewById(R.id.search_button2);
        w1 = findViewById(R.id.webview);
        tabHost.setup();


        if(!((pref.getString("login_key","")).equals("")))
        {
            Toast.makeText(cxt, "dobara open"+login_detail, Toast.LENGTH_SHORT).show();
            FragmentManager fm =getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.add(R.id.tab3,new FirstFragment());
            ft.addToBackStack("");
            t8.setText("");
            register.setVisibility(View.INVISIBLE);
            login.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
            ft.commit();

            //  new DataProcess4().execute();
            //activity call karp
        }
        else {
            getSupportFragmentManager().popBackStackImmediate(R.id.fragment1,0);
        }


        //tab1
        TabHost.TabSpec tabspec = tabHost.newTabSpec("");
        tabspec.setIndicator("Home");//, getResources().getDrawable(drawable.home));
        tabspec.setContent(R.id.tab1);
        tabHost.addTab(tabspec);

        //for tab2

        tabspec = tabHost.newTabSpec("");
        tabspec.setIndicator("Ask");
        tabspec.setContent(R.id.tab2);
        tabHost.addTab(tabspec);
        //For tab3


        tabspec = tabHost.newTabSpec("");
        tabspec.setIndicator("Profile");
        tabspec.setContent(R.id.tab3);
        tabHost.addTab(tabspec);


        String search="";
        String url = "https://www.google.com/search?q=" + search;
        w1.getSettings().setJavaScriptEnabled(true);
        w1.setWebViewClient(new WebViewClient());
        w1.loadUrl(url);

      /*  if(savedInstanceState==null)
        {
            fm=getSupportFragmentManager();
            ft=fm.beginTransaction();
            ft.replace(R.id.tab3,new FirstFragment());
            ft.commit();
        }*/


       // ViewPager viewPager=new ViewPager(this);




        actionBar = getActionBar();
       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(Main3Activity.this,Register_Activity.class);
               startActivity(i);
           }
       });
       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(Main3Activity.this,EditProfile_Activity0.class);
               startActivity(i);
           }
       });

       l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String idd=idr.get(position);
               CommonData.getInstance().setUser_id(idd);
               Intent i=new Intent(Main3Activity.this,Details_activity.class);
               startActivity(i);
               Toast.makeText(Main3Activity.this, ""+idd, Toast.LENGTH_SHORT).show();
           }
       });
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(Main3Activity.this,DeleteActivity.class);
               startActivity(i);
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              key= pref.getString("login_key","");

               t8.setText("");
               register.setVisibility(View.INVISIBLE);
               login.setVisibility(View.INVISIBLE);
               edit.setVisibility(View.INVISIBLE);
               delete.setVisibility(View.INVISIBLE);

              fm =getSupportFragmentManager();
             ft=fm.beginTransaction();
               ft.add(R.id.tab3,new Login_Activity());
               ft.addToBackStack("");
               ft.commit();


           }
       });


     String sub[]=getResources().getStringArray(R.array.sub);
     ArrayAdapter<String> obj_sub=new ArrayAdapter<String>(Main3Activity.this,android.R.layout.simple_list_item_1,sub);
     e1.setAdapter(obj_sub);
     e1.setThreshold(1);


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.clear();
                phone.clear();
                idr.clear();
                email.clear();
                a1.clear();
                 subject = e1.getText().toString();
           //     onj=new CustomAdapter(Main3Activity.this,name,phone,email);
               onj=new ArrayAdapter<String>(Main3Activity.this,android.R.layout.simple_list_item_1,a1);
                l1.setAdapter(onj);
                new DataProcess3().execute();

            }
        });

    }

    
    @Override
    public void onTabChanged(String tabId) {
        Toast.makeText(Main3Activity.this, "tab selected "+tabId, Toast.LENGTH_LONG).show();
        Log.d(tabId,"selected"+tabId);
    }


    class DataProcess3 extends AsyncTask<String,String,String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Main3Activity.this);
            pd1.setMessage("Fetching Data...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("subjects", subject));
            params.add(new BasicNameValuePair("city",location_set));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_get_teacher);
            InputStream inputStream = null;
            String result = null;
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity entity = httpResponse.getEntity();
              //  e1.setText("heelo");

            //    httpPost.setHeader("Content-Type", "application/json");

                inputStream = entity.getContent();

                //jsonis utf-8 by default

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();  //mutable(string change karsakta h)
                // stringbuilder ke methods synchronised means objects ke formn mei check kata h
                //string buffer ke non synchronised means ek ek charachter check hota h
                String line = null;
                //e1.setText("before");
                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");
                }
                result = sb.toString().trim();
                 //e1.setText("2"+result+"errorrrrrr");

  //              Toast.makeText(Main3Activity.this, "Done", Toast.LENGTH_LONG).show();

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
                //CustomAdapter onj = null;
                // name.add("hello");
                // phone.add("bye");
                //email.add("hfth");

//                e1.setText("3");
             /*   if(array.length()==0)
                {
                    Toast.makeText(cxt, "No data Found", Toast.LENGTH_LONG).show();
                }*/
//                else {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String id1=obj.getString("id");
                        String name1 = obj.getString(TAG_NAME);
                        String phone1 = obj.getString(TAG_PHONE);
                        String email1 = obj.getString(TAG_EMAIL);
                        //Toast.makeText(Main3Activity.this, "" + name1 + phone1 + email1, Toast.LENGTH_SHORT).show();
                        a1.add((i+1)+")\nName : "+name1+"\nPhone : "+phone1+"\nEmail : "+email1+"\n");
                        name.add(name1);
                        idr.add(id1);
                        phone.add(phone1);
                        email.add(email1);
                        //  onj=new CustomAdapter(Main3Activity.this,name,phone,email);
                        onj.notifyDataSetChanged();
                    }
            //    }
             //   e1.setText("4");


                //l1.setAdapter(onj);

                pd1.dismiss();
               // Toast.makeText(Main3Activity.this, "got done successfully", Toast.LENGTH_LONG).show();
                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"WrongConstant", "ResourceType"})
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       /* actionBar.setDisplayOptions(actionBar.getDisplayOptions()|ActionBar.DISPLAY_SHOW_CUSTOM);
        imageView =new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.settings);
        ActionBar.LayoutParams layoutParams=new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin=40;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        MenuItem menuItem1 = menu.findItem(R.id.setting);
        imageView = (ImageView) menuItem1.getActionView();
        imageView.setImageResource(R.drawable.settings);
        imageView.setBackgroundResource(android.R.color.transparent);
        //setUpimage(imageView);
        MenuItem menuItem = menu.findItem(R.id.spinner2);
        s1 = (Spinner) menuItem.getActionView();
        setUpMenu(s1, imageView);
        return super.onCreateOptionsMenu(menu);
    }


    public void setUpMenu(Spinner s1, ImageView imageView) {
        DatabaseOperation db=new DatabaseOperation(cxt);
        Cursor cr=db.getInformation(db);
         cr.moveToFirst();
         do {
             loc=cr.getString(0);
             location_set=cr.getString(0);
         }while (cr.moveToNext());

        loc2.add(0,loc );
        ArrayAdapter<String> onj = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loc2);
        //onj.setDropDownViewResource(android.R.color.white);
        s1.setAdapter(onj);
        s1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                loc2.clear();
                Intent i = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(i);
                return false;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(Main3Activity.this, Settings.class);
                startActivity(ii);
            }
        });


    }

    @Override
    public void onBackPressed() {
        int backentry=getSupportFragmentManager().getBackStackEntryCount();
        android.support.v4.app.Fragment fm2=getSupportFragmentManager().findFragmentById(R.id.fragment1);
        FirstFragment ff=new FirstFragment();
        if(!(CommonData.getInstance().getData().equals("")))
        {
            System.exit(0);
            finish();
        }
else {
            if (backentry > 0) {
                for (int i = 0; i < backentry - 1; i++) {
                    if (i == 0 || fm2.isResumed() || fm2.isVisible() || ff != null) {
                        if ((pref.getString("login_key", "")).equals("")) {
                            Toast.makeText(cxt, "login2" + key, Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().popBackStackImmediate();
                            //  new DataProcess4().execute();
                            //activity call karp
                        } else {


                            Toast.makeText(cxt, "login 1" + key, Toast.LENGTH_SHORT).show();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.tab3, new FirstFragment());
                            ft.addToBackStack("");
                            t8.setText("");
                            register.setVisibility(View.INVISIBLE);
                            login.setVisibility(View.INVISIBLE);
                            edit.setVisibility(View.INVISIBLE);
                            delete.setVisibility(View.INVISIBLE);
                            ft.commit();

                        }
                        finish();


                    } else {
                        //  Toast.makeText(cxt, "" + i, Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().popBackStackImmediate();
                    }
                }
            }

            if ((getSupportFragmentManager().getFragments()) != null && getSupportFragmentManager().getFragments().size() > 0) {
                for (int i = 0; i < (getSupportFragmentManager().getFragments().size()) - 1; i++) {
                    android.support.v4.app.Fragment m = getSupportFragmentManager().getFragments().get(i);
                    if (m != null) {
                        getSupportFragmentManager().beginTransaction().remove(m).commit();
                    }
                }
                t8.setText("Only for Teachers..");
                register.setVisibility(View.VISIBLE);
                login.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
            }


            if (w1.canGoBack()) {
                w1.goBack();
            } else {
                finish();
                super.onBackPressed();
            }
        }
    }
}