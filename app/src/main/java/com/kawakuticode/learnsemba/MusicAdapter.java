/**
 * 
 */
package com.kawakuticode.learnsemba;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kawakuticode.learnsemba.MusicItem;

/**
 * @author Russelius
 * 
 */
public class MusicAdapter extends ArrayAdapter<MusicItem> {
	private final Context context;
	private final ArrayList<MusicItem> itemsArrayList;

	public MusicAdapter(Context context, ArrayList<MusicItem> itemsArrayList) {

		super(context, R.layout.music_list, itemsArrayList);

		this.context = context;
		this.itemsArrayList = itemsArrayList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 1. Create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// 2. Get rowView from inflater
		View rowView = inflater.inflate(R.layout.music_list, parent, false);

		// 3. Get the two text view from the rowView
		ImageView singerImage = (ImageView) rowView.findViewById(R.id.singer);
		TextView labelView = (TextView) rowView.findViewById(R.id.label);
		
		TextView valueView = (TextView) rowView.findViewById(R.id.value);
		
		ImageView playIcon = (ImageView) rowView
				.findViewById(R.id.playmusicbutt);

		// 4. Set the text for textView
		singerImage.setImageResource(itemsArrayList.get(position)
				.getImageSinger());
		labelView.setText(itemsArrayList.get(position).getTitle());
		valueView.setText(itemsArrayList.get(position).getDescription());
		//playIcon.setImageResource(itemsArrayList.get(position).getPlayButton());

		// 5. retrn rowView
		return rowView;
	}
}
