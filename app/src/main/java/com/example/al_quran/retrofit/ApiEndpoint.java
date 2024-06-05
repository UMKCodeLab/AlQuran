package com.example.al_quran.retrofit;

import com.example.al_quran.Models.AudioModel.Audio;
import com.example.al_quran.Models.AyatModel.Verses;
import com.example.al_quran.Models.SurahModel.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

    @GET ("chapter_recitations/33?")
    Call<Audio> getAudio();


}
