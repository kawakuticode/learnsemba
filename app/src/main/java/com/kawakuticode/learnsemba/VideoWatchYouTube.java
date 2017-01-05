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

	private static final String APP_KEY = "AIzaSyAfrT65G_GT1fJtpUc6CAJmBvsxvxYSahY";
	// private static final int RECOVERY_DIALOG_REQUEST = 1;

	private YouTubePlayerView youtubeplayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the layout from video_main.xml
		setContentView(R.layout.videoview_main);
		// Find your VideoView in your video_main.xml layout
		youtubeplayer = (YouTubePlayerView) findViewById(R.id.youtube);
		youtubeplayer.initialize(APP_KEY, this);
		// Execute StreamVideo AsyncTask
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
			System.out.println("ID VIDEO ====> "
					+ intent.getStringExtra("ID_VIDEO"));
			player.cueVideo(intent.getStringExtra("ID_VIDEO"));

		}
		// TODO Auto-generated method stub

	}

	// protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();
	// // Declare variables
	// ProgressDialog pDialog;
	// VideoView videoview;
	//
	// // Insert your Video URL
	// String VideoURL;

	// = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// // Get the layout from video_main.xml
	// setContentView(R.layout.videoview_main);
	// // Find your VideoView in your video_main.xml layout
	// videoview = (VideoView) findViewById(R.id.VideoView);
	// // Execute StreamVideo AsyncTask
	//
	// // Create a progressbar
	// pDialog = new ProgressDialog(VideoWatchYouTube.this);
	// // // Set progressbar title
	// pDialog.setTitle("App semba videos");
	// // // Set progressbar message
	// pDialog.setMessage("Buffering...");
	// pDialog.setIndeterminate(false);
	// pDialog.setCancelable(false);
	// // // Show progressbar
	// pDialog.show();
	//
	// try {
	// // Start the MediaController
	// MediaController mediacontroller = new MediaController(
	// VideoWatchYouTube.this);
	// mediacontroller.setAnchorView(videoview);
	// Intent intent = getIntent();
	//
	// VideoURL = intent.getStringExtra("webUrlVideo");
	// // Get the URL from String VideoURL
	//
	// Uri video = Uri.parse(VideoURL);
	// videoview.setMediaController(mediacontroller);
	// videoview.setVideoURI(video);
	//
	// } catch (Exception e) {
	// Log.e("Error", e.getMessage());
	// e.printStackTrace();
	// }
	//
	// videoview.requestFocus();
	// videoview.setOnPreparedListener(new OnPreparedListener() {
	// // Close the progress bar and play the video
	// public void onPrepared(MediaPlayer mp) {
	// // pDialog.dismiss();
	// videoview.start();
	// }
	// });
	//
	// }

}
