package com.example.al_quran.Models.AudioModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Audio{

	@SerializedName("audio_files")
	private List<AudioFilesItem> audioFiles;

	public List<AudioFilesItem> getAudioFiles(){
		return audioFiles;
	}

	@Override
 	public String toString(){
		return 
			"Audio{" + 
			"audio_files = '" + audioFiles + '\'' + 
			"}";
		}
}