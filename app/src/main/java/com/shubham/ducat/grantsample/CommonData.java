package com.shubham.ducat.grantsample;

import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by user on 04-07-2018.
 */

public class CommonData {
    private static CommonData obj;
    private String location;
    private String login;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private int count_id;
    private String user_id;
    private ArrayList<String> arr;




    private CommonData()
    {
        user_id="";
        location="";
        userName="";
        password="";
        login="";
        name="";
        phone="";
        email="";
        count_id=0;
        arr=new ArrayList<String>();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public static CommonData getInstance()
    {
        if(obj==null)
        {
            obj=new CommonData();

        }
        return  obj;
    }

    public String getData()
    {
        return  location;
    }
    public void setData(String x)
    {
        this.location=x;
    // return this.location;
    }
    public String getLogin()
    {
        return login;
    }
    public void setLogin(String x1)
    {
        this.login=x1;
   //     return this.login;
    }
    public void setName1(String name)
    {
        this.name=name;
    }
    public void setPhone1(String phone)
    {
        this.phone=phone;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getName1()
    {
        return this.name;
    }
    public String getPhone1()
    {
        return this.phone;
    }
    public String getEmail1()
    {
        return this.email;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String x2)
    {
        this.userName=x2;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String x3)
    {
        this.password=x3;
    }
    public ArrayList<String> getData2()
    {
        return this.arr;
    }

    public int getCount_id()
    {
        return count_id;
    }

}
