package com.amrita.dscaseb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
Context context;
LayoutInflater layoutInflater;
FragmentManager fm;
public SliderAdapter(Context context){
    this.context=context;

}

public String[] descriptions={
  " Google collaborates with university students who are passionate about growing developer communities",
        "Grow their knowledge on developer technologies and more through peer to peer workshops and events.",
        "Gain relevant industry experience by solving problems for local organizations with technology based solutions.",
        "  Showcase their prototypes and solutions  to their local community and industry leaders."
};

    @Override
    public int getCount() {
        return descriptions.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        TextView tvw=(TextView)view.findViewById(R.id.slide_text);
        tvw.setText(descriptions[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
