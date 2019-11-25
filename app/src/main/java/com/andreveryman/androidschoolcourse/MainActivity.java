package com.andreveryman.androidschoolcourse;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private LecturesAdapter lecturesAdapter = new LecturesAdapter();
    private final LecturesProvider provider = new LecturesProvider();
    private DateHelper dateHelper;
    private final List<String> lectors = provider.getLectors();
    private Spinner lectorsSpinner;
    private RecyclerView recyclerView;
    private Spinner weeksSpinner;
    private int selectedLector = 0; //позиция спиннера с лекторами
    private boolean showWeeks = false; //показывать ли недели

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateHelper = new DateHelper(getResources().getString(R.string.date_pattern));
        initRecyclerView();
        initLectorsSpinner();

        initWeeksSpinner();


    }

    //Спиннер для показа недель
    private void initWeeksSpinner() {
        weeksSpinner = findViewById(R.id.weeks_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.weeks_options));
        weeksSpinner.setAdapter(adapter);
        weeksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showWeeks = position != 0;
                showItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void initLectorsSpinner() {
        lectorsSpinner = findViewById(R.id.lectors_spinner);


        final LectorSpinnerAdapter adapter = new LectorSpinnerAdapter();

        Collections.sort(lectors);
        lectors.add(0, getResources().getString(R.string.all));
        adapter.setmLectors(lectors);
        lectorsSpinner.setAdapter(adapter);
        lectorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLector = position;
                showItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.lecture_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(lecturesAdapter);
        lecturesAdapter.setLectures(provider.getLectures());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }


    //Обновление содержимого таблицы, вызывается при выборе одного из спиннеров
    private void showItems() {
        List resultLectures;
        if (selectedLector == 0) {
            resultLectures = provider.getLectures(); //Все лекторы
        } else {
            resultLectures = provider.filterBy(lectors.get(selectedLector)); //Отфильрованные
        }

        if (showWeeks) //Добавка недель в список
            resultLectures = provider.addWeeks(resultLectures);
        int position = dateHelper.findNextLecturePosition(resultLectures);
        //номер позиции следующей лекции
        lecturesAdapter.setPassedLectures(position); //передается чтобы подкрасить пройденные лекции
        lecturesAdapter.setLectures(resultLectures);

        recyclerView.scrollToPosition(position);

    }
}
