package com.shubham.ducat.grantsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.google.android.gms.maps.MapView;

public class Contact_Us extends AppCompatActivity {
    ImageView imageView;
    TextView t1, t2;
   // MapView m1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact__us);
        t1 = findViewById(R.id.textView12);
        t2 = findViewById(R.id.textView14);
   //     m1 = findViewById(R.id.mapView);

        t1.setPaintFlags(t1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        t2.setPaintFlags(t2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:9557054160"));
                if (ActivityCompat.checkSelfPermission(Contact_Us.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Contact_Us.this,new String[]{Manifest.permission.CALL_PHONE},0);
                    return;
                }
                startActivity(i);
            }
        });
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
