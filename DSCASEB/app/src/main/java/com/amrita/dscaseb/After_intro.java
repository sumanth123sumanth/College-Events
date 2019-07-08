package com.amrita.dscaseb;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class After_intro extends AppCompatActivity implements View.OnClickListener{
private ResideMenu resideMenu;
private ResideMenuItem itemHome,itemResources,itemContact,itemAbout;
private Context context;
    ResideMenuItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_intro);
        context=this;
        setUpMenu();
        if(savedInstanceState==null)
        {
           changeFragment(new Home());
        }
    }
    private void setUpMenu()
    {
        resideMenu=new ResideMenu(this);
        resideMenu.setBackground(R.drawable.meback2);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menulistener);
        resideMenu.setScaleValue(0.5f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        itemHome=new ResideMenuItem(this,R.drawable.ic_home_black_24dp,"Home");
        itemResources=new ResideMenuItem(this,R.drawable.ic_insert_drive_file_black_24dp,"Resource");
        itemContact=new ResideMenuItem(this,R.drawable.ic_phone_black_24dp,"Contact");
        itemAbout=new ResideMenuItem(this,R.drawable.ic_info_black_24dp,"About");

        itemHome.setOnClickListener(this);
        itemResources.setOnClickListener(this);
        itemContact.setOnClickListener(this);
        itemAbout.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemResources,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemContact,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAbout,ResideMenu.DIRECTION_LEFT);


        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
    private ResideMenu.OnMenuListener menulistener=new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };

    @Override
    public void onClick(View view) {
       if(view==itemHome)
       {
           Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_SHORT).show();
          changeFragment(new Home());

       }
       else if(view==itemResources)
       {
           Toast.makeText(getApplicationContext(),"res",Toast.LENGTH_SHORT).show(); changeFragment(new Home());
       }
       else  if(view==itemContact)
       {
           Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_SHORT).show(); changeFragment(new Home());
       }
       else if (view==itemAbout)
       {
           Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_SHORT).show(); changeFragment(new Home());
       }
        resideMenu.closeMenu();
    }
    private void changeFragment(Fragment f)
    {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,f)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    public ResideMenu getResideMenu()
    {
        return resideMenu;
    }
}
