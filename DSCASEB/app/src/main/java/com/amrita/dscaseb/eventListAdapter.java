package com.amrita.dscaseb;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class eventListAdapter extends RecyclerView.Adapter<eventListAdapter.eventViewHolder> {
    private Context ctx;
    private List<event> eventList2;
    public eventListAdapter(Context ctx,List<event> events)
    {   this.ctx=ctx;
        this.eventList2=events;
    }
    @NonNull
    @Override
    public eventListAdapter.eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(ctx);
        View view=layoutInflater.inflate(R.layout.eventitem,null);
        return  new eventViewHolder(view,ctx,eventList2);
    }

    @Override
    public void onBindViewHolder(@NonNull eventListAdapter.eventViewHolder holder, int position) {
                holder.tv.setText(eventList2.get(position).getName());
                Uri uri=eventList2.get(position).getUrl();
                Picasso.get().load(uri).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return eventList2.size();
    }

    public class eventViewHolder extends RecyclerView.ViewHolder
    {
            View mview;
            ImageView iv;
            TextView tv;
            Context c;
            List<event> e;
        public eventViewHolder(View itemView,Context c,List<event> e) {
            super(itemView);
            mview=itemView;
            this.c=c;
            this.e=e;
            tv=(TextView)mview.findViewById(R.id.name_evt);
            iv=(ImageView)mview.findViewById(R.id.img_evt);
        }
    }

}
