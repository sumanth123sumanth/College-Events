package com.amrita.dscaseb;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanks.htextview.base.HTextView;

import org.w3c.dom.Text;

import static java.lang.Thread.sleep;


public class splash extends Fragment {

private HTextView t,p;
private ImageView i;
Animation ia,ia1,ia2;
TransitionDrawable transition;
Thread t2;
Handler handler;
private Intent intent1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        i=view.findViewById(R.id.iv);
        ia=AnimationUtils.loadAnimation(getContext(),R.anim.push_left);
        ia1=AnimationUtils.loadAnimation(getContext(),R.anim.push_down);
        ia2=AnimationUtils.loadAnimation(getContext(),R.anim.push_right);
        t=view.findViewById(R.id.tv);p=view.findViewById(R.id.tv2);
        t.setAnimation(ia);
        p.setAnimation(ia2);
        Resources res = getContext().getResources();
         transition = (TransitionDrawable) res.getDrawable(R.drawable.expand_collpase);
        i.setImageDrawable(transition);
        ia.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        i.setAnimation(ia1);
        showdialog1();

        Thread thread=new Thread()
                {
                    @Override
                    public void run() {
                        try {
                            sleep(2000);
                            t.animateText("DSC");
                            p.animateText("ASE-Bengaluru");
                            sleep(1000);
                            t.animateText("Devleoper Student Clubs");
                            sleep(800);
                             intent1=new Intent(getActivity(),Getstarted.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        super.run();
                    }
                };

                thread.start();

    }
    private  void  showdialog1()
    {
// Create a Handler instance on the main thread
        handler = new Handler();

// Create and start a new Thread
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(8500);
                }
                catch (Exception e) { } // Just catch the InterruptedException

                // Now we use the Handler to post back to the main thread
                handler.post(new Runnable() {
                    public void run() {
                        // Set the View's visibility back on the main UI Thread
                       transition.startTransition(1500);
                    }
                });
                // handler.removeCallbacksAndMessages(null);
            }
        }).start();
    }
}
