package com.andreveryman.androidschoolcourse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrej Russkikh on 25.11.2019.
 */
public class DateHelper {




    private SimpleDateFormat sdf;

    public DateHelper(String pattern){
        sdf = new SimpleDateFormat(pattern);
    }

    //Поиск позиции на которой у лекции дата дальше текущей
    public int findNextLecturePosition(List items){
        for(int i=0;i<items.size();i++){
            if(!(items.get(i) instanceof Lecture))
                continue;
            Lecture lecture = (Lecture) items.get(i);
            String date = lecture.getDate();
            try {

                Date lectureDate = sdf.parse(date);
                if(lectureDate.getTime()> Calendar.getInstance().getTimeInMillis())
                    return i;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return items.size()-1;
    }

}
