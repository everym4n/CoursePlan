package com.andreveryman.androidschoolcourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrej Russkikh on 21.11.2019.
 */
public class LecturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_LECTURE = 0;
    private final int TYPE_WEEK = 1;
    private final int TYPE_UNKNOWN = 2;


    private List mElements; //список объектов Lecture и Week

    //Число позиций которые будут подсвечены зеленым, указывая на то что лекция уже прошла
    private int passedLectures = 0;



    public void setPassedLectures(int passedLectures) {
        this.passedLectures = passedLectures;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case TYPE_LECTURE:
                return new LectureViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_lecture,parent,false));
            case TYPE_WEEK:
                return new WeekViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_week,parent,false));
            default:
                return new UnknownItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_unknown,parent,false));
        }

    }





    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position    ) {

        switch (getItemViewType(position)){
            case TYPE_LECTURE:
                Lecture lecture = (Lecture) mElements.get(position);
                ((LectureViewHolder) holder).bind(lecture);
                if(position<passedLectures)
                    holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.color_lecture_passed));
                else
                    holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.white));
                break;
            case TYPE_WEEK:
                Week week = (Week) mElements.get(position);
                ((WeekViewHolder) holder).bind(week);
                break;
            default:

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mElements.get(position) instanceof  Lecture)
            return TYPE_LECTURE;
        if(mElements.get(position) instanceof  Week)
            return TYPE_WEEK;
        return TYPE_UNKNOWN;

    }

    @Override
    public int getItemCount() {
        return mElements == null ? 0 : mElements.size();
    }


    public void setLectures(List<Lecture> lectures) {
        this.mElements = new ArrayList<>(lectures);
        notifyDataSetChanged();
    }

    public class LectureViewHolder extends RecyclerView.ViewHolder {
        private TextView mNumber;
        private TextView mDate;
        private TextView mTheme;
        private TextView mLector;

    public void bind(Lecture lecture){
        mTheme.setText(lecture.getTheme());
        mLector.setText(lecture.getLector());
        mDate.setText(lecture.getDate());
        mNumber.setText(String.valueOf(lecture.getNumber()));
    }




    public LectureViewHolder(@NonNull View itemView) {
        super(itemView);
        mNumber = itemView.findViewById(R.id.number);
        mDate = itemView.findViewById(R.id.date);
        mLector = itemView.findViewById(R.id.lector);
        mTheme = itemView.findViewById(R.id.theme);
    }
    }

    public class WeekViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;
    public void bind(Week week){

        mTitle.setText( mTitle.getContext().getResources().getString(R.string.week,week.getNumber()));
    }


    public WeekViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.title);
    }
}

    public class UnknownItemViewHolder extends RecyclerView.ViewHolder {

        public UnknownItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
