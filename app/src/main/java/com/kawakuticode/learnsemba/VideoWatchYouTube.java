package com.kawakuticode.learnsemba;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoWatchYouTube extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	private static final String APP_KEY = "AIzaSyBxSE61XG4570uPVo5YfQkf_13z88RKMIk";


	private YouTubePlayerView youtubeplayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview_main);

		youtubeplayer = (YouTubePlayerView) findViewById(R.id.youtube);
		youtubeplayer.initialize(APP_KEY, this);

	}

	@Override
	public void onInitializationFailure(Provider provider,
			YouTubeInitializationResult error) {

		if (error.isUserRecoverableError()) {
			error.getErrorDialog(this, error.ordinal()).show();
		} else {
			String errorMessage = String.format(
					"There was an error initializing the YouTubePlayer",
					error.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean loadAgain) {

		if (!loadAgain) {

			Intent intent = getIntent();
			intent.getStringExtra("ID_VIDEO");
			player.cueVideo(intent.getStringExtra("ID_VIDEO"));

		}

	}


}
