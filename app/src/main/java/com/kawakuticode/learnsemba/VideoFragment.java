package com.kawakuticode.learnsemba;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class VideoFragment extends ListFragment {

	private String videoTitle[];
	private Integer[] imageVideo;
	private Integer[] imageButton;

	public VideoFragment() {

		videoTitle = new String[] { " Mr.Tecas & Jo - Karipande ",
				" Mr.Tecas & Jo - paris festival ",
				" Mr.Tecas & Jo - festival emocao ",
				" Mr.Tecas & Jo - kizomba.Fr ", " atelier kizomba.fr ",

		};
		imageVideo = new Integer[] { R.drawable.tecasjokari,
				R.drawable.tecasfr3, R.drawable.tecasfr2, R.drawable.tecasfr,
				R.drawable.atelierkizombafr, };

		imageButton = new Integer[] { R.drawable.arrow, R.drawable.arrow,
				R.drawable.arrow, R.drawable.arrow, R.drawable.arrow };
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ListAdapter listAdapter = new CustomList(getActivity(), videoTitle,
				imageVideo, imageButton);
		setListAdapter(listAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);

	}

	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
		String item = (String) list.getAdapter().getItem(position);

		// long x = list.getAdapter().getItemId(position);

		String url = "";

		switch (item) {
		case " Mr.Tecas & Ms.Jo - Karipande ":
			if (isOnline() == true) {
				url = "QuAWn1OL6cU";
				launchVideoPlayer(url);

			} else {
				showAlertDialog(getActivity(), "No internet conection ",
						"connect to internet and try again.", false);

			}

			break;
		case " Mr.Tecas & Ms.Jo - paris k.festival ":
			if (isOnline() == true) {
				url = "PHCBnXZN4hU";
				launchVideoPlayer(url);

			} else {
				showAlertDialog(getActivity(), "No internet conection ",
						"connect to internet and try again.", false);

			}
			break;
		case " Mr.Tecas & Ms.Jo - festival emocao ":
			if (isOnline() == true) {
				url = "yvfPBFiODvk";
				launchVideoPlayer(url);

			} else {
				showAlertDialog(getActivity(), "No internet conection ",
						"connect to internet and try again.", false);

			}
			break;
		case " Mr.Tecas & Ms.Jo - kizomba.Fr ":
			if (isOnline() == true) {
				url = "aFjm2K4sjFY";
				launchVideoPlayer(url);
			} else {
				showAlertDialog(getActivity(), "No internet conection ",
						"connect to internet and try again.", false);

			}
			break;
		case " atelier kizomba.fr ":
			if (isOnline() == true) {
				url = "krIdQWhyWSk";
				launchVideoPlayer(url);

			} else {
				showAlertDialog(getActivity(), "No internet conection ",
						"connect to internet and try again.", false);

			}
			break;
		default:
			break;
		}

	}



	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/**
	 * Function to display simple Alert Dialog
	 * 
	 * @param context
	 *            - application context
	 * @param title
	 *            - alert dialog title
	 * @param message
	 *            - alert message
	 * @param status
	 *            - success/failure (used to set icon)
	 * */
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	public void launchVideoPlayer(String Url) {

		Intent myIntent = new Intent(getActivity(), VideoWatchYouTube.class);

		myIntent.putExtra("ID_VIDEO", Url);
		getActivity().startActivity(myIntent);

	}

}