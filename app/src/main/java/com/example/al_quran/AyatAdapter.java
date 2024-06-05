package com.example.al_quran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quran.Models.AyatModel.VersesItem;

import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.ViewHolder> {

    private static List<VersesItem> versesItemList;
    public AyatAdapter(List<VersesItem> versesItemList){
        this.versesItemList = versesItemList;
    }
    @NonNull
    @Override
    public AyatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AyatAdapter.ViewHolder holder, int position) {
        VersesItem versesItem = versesItemList.get(position);

        holder.textViewAyat.setText(versesItem.getTextUthmani());
    }

    @Override
    public int getItemCount() {
        return versesItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat, textViewNomorAyat;

        public ViewHolder(View itemView){
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
        }
    }
    public void setData(List<VersesItem> data) {
        versesItemList.clear();
        versesItemList.addAll(data);
        notifyDataSetChanged();
    }

}
