package com.amrita.dscaseb;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;

public class Getstarted extends AppCompatActivity {
private ViewPager viewPager;
private LinearLayout linearLayout;
private  SliderAdapter sliderAdapter;
private TextView[] Dots;
 HTextView hTextView;
boolean first;
private Handler handler;
private Button pre,nxt;
private int currentPage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
        first=false;
        viewPager=(ViewPager)findViewById(R.id.vp);
        linearLayout=(LinearLayout)findViewById(R.id.dots);
        pre=(Button)findViewById(R.id.bt_back);
        nxt=(Button)findViewById(R.id.bt_next);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nxt.getText().equals("FINISH"))
                {
                   openAfterIntro();
                }
                else{ viewPager.setCurrentItem(currentPage+1);}
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currentPage-1);
            }
        });
        hTextView=(HTextView)findViewById(R.id.tv_name);
        hTextView.animateText("Developer Student Clubs");
        hTextView.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(final HTextView hTextView) {
                first=!first;
                handler = new Handler();

// Create and start a new Thread
                new Thread(new Runnable() {
                    public void run() {
                        try{
                            Thread.sleep(4000);
                        }
                        catch (Exception e) { } // Just catch the InterruptedException

                        // Now we use the Handler to post back to the main thread
                        handler.post(new Runnable() {
                            public void run() {
                                // Set the View's visibility back on the main UI Thread
                                if(first){ hTextView.animateText("ASE-Bengaluru");}
                                else {hTextView.animateText("Developer Student Clubs");}
                            }
                        });
                        // handler.removeCallbacksAndMessages(null);
                    }
                }).start();


            }
        });
            sliderAdapter=new SliderAdapter(this);
            viewPager.setAdapter(sliderAdapter);
            viewPager.setCurrentItem(0);
            nxt.setEnabled(true);
            nxt.setText("NEXT");
            addDots(0);
        getSupportFragmentManager().beginTransaction().replace(R.id.getstart_frame,new family_frg()).commit();
            viewPager.addOnPageChangeListener(viewlistener);
    }
    public void addDots(int pos)
    {
            Dots=new TextView[4];
            linearLayout.removeAllViews();
            for(int i=0;i<Dots.length;i++)
            {
                Dots[i]=new TextView(this);
                Dots[i].setText(Html.fromHtml("&#8226;"));
                Dots[i].setTextSize(35);
                Dots[i].setTextColor(getResources().getColor(R.color.TransparentWhite));
                linearLayout.addView(Dots[i]);
            }
            if (Dots.length>0)
            {
                Dots[pos].setTextColor(getResources().getColor(R.color.White));
            }
    }
    ViewPager.OnPageChangeListener viewlistener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            switch(position)
            {
                case 0:getSupportFragmentManager().beginTransaction().replace(R.id.getstart_frame,new family_frg()).commit();
                    break;
                case 1:getSupportFragmentManager().beginTransaction().replace(R.id.getstart_frame,new connection_frg()).commit();
                    break;
                case 2:getSupportFragmentManager().beginTransaction().replace(R.id.getstart_frame,new industry_frg()).commit();
                    break;
                case 3:getSupportFragmentManager().beginTransaction().replace(R.id.getstart_frame,new proto_frg()).commit();
                    break;
            }
            currentPage=position;
            if(position==0)
            {
                nxt.setEnabled(true);
                pre.setEnabled(false);
                pre.setVisibility(View.INVISIBLE);
                nxt.setText("NEXT");
                pre.setText("");
            }
            else if(position==Dots.length-1)
            {
                nxt.setEnabled(true);
                pre.setEnabled(true);
                pre.setVisibility(View.VISIBLE);
                nxt.setText("FINISH");
                pre.setText("BACK");
            }else
            {
                nxt.setEnabled(true);
                pre.setEnabled(true);
                pre.setVisibility(View.VISIBLE);
                nxt.setText("NEXT");
                pre.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void openAfterIntro()
    {
        Intent v=new Intent(this,After_intro.class);
        v.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(v);
    }
}
