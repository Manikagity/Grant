package com.shubham.ducat.grantsample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

public class Settings extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
ImageView imageView;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView4);
        t4=findViewById(R.id.textView5);
       // t5=findViewById(R.id.textView6);
     //   t5.setPaintFlags(t5.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "done", Toast.LENGTH_SHORT).show();
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,Contact_Us.class);
                startActivity(i);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Settings.this,FeedBack.class);
                startActivity(i);

            }
        });
     /*   t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Settings.this, "You logged out ", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().popBackStackImmediate(R.id.fragment1,0);
                finish();
            }
        });*/

    }
}
