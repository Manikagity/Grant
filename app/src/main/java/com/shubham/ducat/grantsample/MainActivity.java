package com.shubham.ducat.grantsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    ProgressBar p;
    Context cxt=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p=findViewById(R.id.progressBar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        p.setProgress(1);
        final DatabaseOperation db=new DatabaseOperation(cxt);
     //   db.deleteAll();
        final Cursor cr=db.getInformation(db);
        cr.moveToFirst();
       // final int count=cr.getInt(0);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();

                if(!(db.isEmptyTable()))//!(cr.getString(0).equals("")))
                {
                    Intent i=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(i);
                }
            }
        },2000);
    }
}
