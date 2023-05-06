package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Section_RecyclerView_Adapter extends RecyclerView.Adapter<Section_RecyclerView_Adapter.Recycler_View_ItemHolder> {
    private Context context;
    private ArrayList<Section> recyclerItemValues;

    private Course course;

    public Section_RecyclerView_Adapter(Context context, ArrayList<Section> recyclerItemValues, Course course) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;
        this.course = course;
    }

    @NonNull
    @Override
    public Recycler_View_ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(context);

        View itemView = inflator.inflate(R.layout.section_recyclerview_item, parent, false);

        Recycler_View_ItemHolder mViewHolder = new Recycler_View_ItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_View_ItemHolder holder, int position) {
        final Section curentItem = recyclerItemValues.get(position);

        holder.tv.setText("#" + curentItem.getSectionNo() + " - Professor: " + curentItem.getTeacherName().substring(0, 1).toUpperCase() + curentItem.getTeacherName().substring(1));
        holder.img.setImageResource(course.getImgId());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Clicked on item from layout " + curentItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    // Create that class according to the xml layout file used.
    class Recycler_View_ItemHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        LinearLayout parentLayout;

        public Recycler_View_ItemHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.section_tv);
            img = itemView.findViewById(R.id.section_iv);
            parentLayout = itemView.findViewById(R.id.section_ll);
        }
    }
}

