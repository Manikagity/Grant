package com.shubham.ducat.grantsample;

import android.content.pm.PackageInstaller;
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

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FeedBack extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;

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
        setContentView(R.layout.activity_feedback);

        e1=findViewById(R.id.message);
        b1=findViewById(R.id.button_feed);
        e2=findViewById(R.id.editText_email_feed);
        e3=findViewById(R.id.editText_pas);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t=new Thread()
                {

                    public void run() {
                        String to ="grant.IndiaAll@gmail.com";
                                //e1.getText().toString();
                        String sub = "Feedback from grant user";
                                //e2.getText().toString();
                        String mgs = e1.getText().toString();
                        String host = "smtp.gmail.com";
                        String from = e2.getText().toString();
                        String pass = e3.getText().toString();

                        try {
                            Properties p=new Properties();
                            Session s=Session.getInstance(p);
                            MimeMessage message=new MimeMessage(s);
                            InternetAddress toId=new InternetAddress(to);
                            InternetAddress fromId=new InternetAddress(from);
                            message.setRecipient(Message.RecipientType.TO,toId);
                            message.setFrom(fromId);
                            message.setSubject(sub);
                            message.setText(mgs);
                            Transport tpt=s.getTransport("smtps");
                            tpt.connect(host,from,pass);
                            tpt.sendMessage(message,message.getAllRecipients());
                            // Toast.makeText(MainActivity.this, "Mail Sent..", Toast.LENGTH_LONG).show();
                            tpt.close();

                        } catch (Exception e)
                        {

                        }
                    }

                };
                t.start();
                Toast.makeText(FeedBack.this, "Feedback sent", Toast.LENGTH_SHORT).show();
            }

        });


    }
}
