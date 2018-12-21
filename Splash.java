package com.mitsol.project.teachercr;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_MS = 3000;
    public static ProgressDialog dialog=null;//Add contact Dialog
    public static int flag_add_contacts=-1;//This is use for knowing which add contact clicked mean Add to the App ot to the group.
    public static String group_name_clicked="";//This is use for knowing which Group is clicked.
    private Handler mHandler;
    private Runnable mRunnable;
    DatabaseHelperStudent db;
    public static int flag=-1;//this flag is use for one recycylye view diffrent behaviour when clicked from diffrent activities
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        db=new DatabaseHelperStudent(this);
        ImageView im= (ImageView) findViewById(R.id.image_view);
        im.setImageResource(R.drawable.bg);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                sp = getSharedPreferences("ShaPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                boolean  firstTime=sp.getBoolean("second", true);
                if(firstTime) {
                    editor.putBoolean("second",false);
                    editor.apply();
                   // Intent intent = new Intent(Splash.this, AddCr.class);
                    //startActivity(intent);
                    finish();
                }
                else
                {
                    SharedPreferences s=getSharedPreferences("myprefs",MODE_PRIVATE);
                    String cr =s.getString("MY data","");
                    if(cr.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Kindly Must Enter CM Name...",Toast.LENGTH_LONG).show();
                      //  Intent intent = new Intent(Splash.this, AddCr.class);
                       // startActivity(intent);
                        finish();
                    }
                    else
                    {
                        //Intent intent = new Intent(Splash.this, mainmain.class);
                        //startActivity(intent);
                        finish();
                    }
                }
                //********************************

            }
        };
        mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
    }

}






