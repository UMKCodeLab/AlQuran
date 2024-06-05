package com.example.al_quran;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.al_quran.Models.AudioModel.Audio;
import com.example.al_quran.Models.AudioModel.AudioFilesItem;
import com.example.al_quran.Models.SurahModel.Chapters;
import com.example.al_quran.Models.SurahModel.ChaptersItem;
import com.example.al_quran.retrofit.ApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private TextView textDate;
    private MainAdapter mainAdapter;

    private RecyclerView.LayoutManager layoutManager1;
    private List<ChaptersItem> results = new ArrayList<>();

    private List<AudioFilesItem> list = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpRecyclerView();
        getDataFromApi();
        //tanggal today
        textDate = findViewById(R.id.dateDisplay);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                @SuppressLint("SimpleDateFormat")

                DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyy");

                textDate.setText(dateFormat.format(new Date()));

                //interval
                handler.postDelayed(this, 1000);

            }
        });
        //end tanggal


    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);

    }

    private void getDataFromApi() {
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ChaptersItem> allChapters = response.body().getChapters();
                    List<ChaptersItem> filteredChapters = new ArrayList<>();
                    for (ChaptersItem chapter : allChapters) {
                        if (chapter.getId() >= 78 && chapter.getId() <= 114) {
                            filteredChapters.add(chapter);
                        }
                    }
                    mainAdapter.setData(filteredChapters);
                    Log.d(TAG, filteredChapters.toString());
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.e(TAG, "Request failed", t);
            }
        });
    }

}