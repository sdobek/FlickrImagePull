package com.example.everalbumlight;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class PhotoArrayAdapter extends ArrayAdapter<ImageResult> {
	

	public PhotoArrayAdapter(Context context, List<ImageResult> photos) {
		super(context, R.layout.photo_result, photos);
	}
	
	@Override
	/*
	 * Chose to use the SmartImage library as a quick way to inflate my GridView
	 * using the url 
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageResult = this.getItem(position);
		SmartImageView ivImage;
		if (convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflator.inflate(R.layout.photo_result, parent, false);
		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageResult.getImageUrl());
		return ivImage;
	}

}
