package com.app.uas_quran;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.uas_quran.Model.AudioModel.AudioFilesItem;
import com.app.uas_quran.Model.SurahModel.Chapters;
import com.app.uas_quran.Model.SurahModel.ChaptersItem;

import java.io.IOException;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<ChaptersItem> surah;
    private List<AudioFilesItem> audio;
    public MainAdapter(List<ChaptersItem> results, List<AudioFilesItem> result) {
        this.surah = results;
        this.audio = result;
    }
    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MainViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        ChaptersItem result = surah.get(position);
        AudioFilesItem aud = audio.get(position);

        holder.textViewSurahLatin.setText(result.getNameSimple());
        holder.textViewSurahArab.setText(result.getNameArabic());
        holder.textViewTerjemahanSurah.setText(result.getTranslatedName().getName());
        holder.textViewSurahNomor.setText(String.valueOf(result.getId()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);

            intent.putExtra("id", result.getId());
            intent.putExtra("name", result.getNameComplex());
            intent.putExtra("verses", result.getVersesCount());
            intent.putExtra("tempat", result.getRevelationPlace());
            intent.putExtra("arabic", result.getNameArabic());
            intent.putExtra("audio_url", aud.getAudioUrl());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return surah.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewSurahArab, textViewTerjemahanSurah , textViewSurahNomor;
        public MainViewHolder(@NonNull View itemView){
            super(itemView);

            textViewSurahNomor = itemView.findViewById(R.id.tvSurahNomor);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
        }
    }
    public void setData(List<ChaptersItem> data, List<AudioFilesItem> data1){
        // Surah
        surah.clear();
        surah.addAll(data);
        // Terjemahan
        audio.clear();
        audio.addAll(data1);
        notifyDataSetChanged();
    }
}
