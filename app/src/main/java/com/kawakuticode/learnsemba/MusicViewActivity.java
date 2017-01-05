package com.kawakuticode.learnsemba;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicViewActivity extends Activity {

    private static final String[] EXTENSIONS = {".mp3", ".mid", ".wav",
            ".ogg", ".mp4"}; // Playable Extensions
    List<String> trackNames; // Playable Track Titles
    List<String> trackArtworks; // Track artwork names
    AssetManager assets; // Assets (Compiled with APK)
    String path;
    // File path; //directory where music is loaded from on SD Card
    File path2; // directory where album artwork is loaded from on SD Card
    MusicPlayer track; // currently loaded track
    ImageView bg; // Track artwork
    Button btnPlay; // The play button will need to change from 'play' to
    // 'pause', so we need an instance of it
    Random random; // used for shuffle
    boolean shuffle; // is shuffle mode on?
    boolean isTuning; // is user currently jammin out, if so automatically start
    // playing the next track
    int currentTrack; // index of current track selected
    private PowerManager.WakeLock wakeLock;

    // int type; // 0 for loading from assets, 1 for loading from SD card

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.music_view_activity);

        initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        //wakeLock.acquire();
    }

    @Override
    public void onPause() {
        super.onPause();

        // wakeLock.release();
        if (track != null) {
            if (track.isPlaying()) {
                track.pause();
                isTuning = false;
                btnPlay.setBackgroundResource(R.drawable.play);
            }
            if (isFinishing()) {
                track.dispose();
                finish();
            }
        } else {
            if (isFinishing()) {
                finish();
            }
        }
    }

    private void initialize() {

        bg = (ImageView) findViewById(R.id.backgrou);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setBackgroundResource(R.drawable.play);

        trackNames = new ArrayList<String>();
        trackArtworks = new ArrayList<String>();
        assets = getAssets();
        currentTrack = 0;
        shuffle = false;
        isTuning = false;
        random = new Random();
        // this.type = type;

        addTracks(getTracks());
        loadTrack();
    }

    // Generate a String Array that represents all of the files found
    private String getTracks() {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
                || Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED_READ_ONLY)) {

            // Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            //
            Intent intent = getIntent();
            path = intent.getStringExtra("pathMusic");

            path2 = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            return path;
        } else {
            Toast.makeText(getBaseContext(),
                    "SD Card is either mounted elsewhere or is unusable",
                    Toast.LENGTH_LONG).show();
        }

        return null;
    }

    // Adds the playable files to the trackNames List
    private void addTracks(String temp) {
        if (temp != null) {


            trackNames.add(temp);


            trackArtworks.add(temp.substring(0, temp.length() - 4));

        }
        Toast.makeText(getBaseContext(),
                "Loaded " + Integer.toString(trackNames.size()) + " Tracks",
                Toast.LENGTH_SHORT).show();
    }

    // }

    // Checks to make sure that the track to be loaded has a correct extenson
    private boolean trackChecker(String trackToTest) {
        for (int j = 0; j < EXTENSIONS.length; j++) {
            if (trackToTest.contains(EXTENSIONS[j])) {
                return true;
            }
        }
        return false;
    }

    // Loads the track by calling loadMusic
    private void loadTrack() {

        track = loadMusic();
        setImage();

    }

    // loads a MusicPlayer instance using either a built in asset or an external
    // resource
    private MusicPlayer loadMusic() {

        return new MusicPlayer(path);

    }

    // Sets the background image to match the track currently playing or a
    // default image
    private void setImage() {

        bg.setBackgroundResource(R.drawable.konde_player);
        //bg.setAdjustViewBounds(true);

		/*if (new File(path2.getAbsolutePath(), trackArtworks.get(currentTrack)
                + ".jpg").exists()) {
			bg.setImageDrawable(Drawable.createFromPath(path2.getAbsolutePath()
					+ "/" + trackArtworks.get(currentTrack) + ".jpg"));
		} else {
			int defaultImageResource = getResources().getIdentifier(
					"drawable/icon", null, getPackageName());
			if (defaultImageResource != 0) {
				Drawable image = getResources().getDrawable(
						defaultImageResource);
				bg.setImageDrawable(image);
			}
		}*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                // Set Looping
                synchronized (this) {
                    if (track.isLooping()) {
                        track.setLooping(false);
                        Toast.makeText(getBaseContext(),
                                "Playing Tracks Sequentially", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        track.setLooping(true);
                        Toast.makeText(getBaseContext(),
                                "Looping " + trackNames.get(currentTrack),
                                Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            case 1:
                // Set Shuffle
                synchronized (this) {
                    if (shuffle) {
                        setShuffle(false);
                    } else {
                        setShuffle(true);
                    }
                }
                return true;
            case 2:
                // Stop MusicPlayer
                synchronized (this) {
                    track.switchTracks();
                    btnPlay.setBackgroundResource(R.drawable.play);
                }
                return true;
            case 3:
                // Change Source from Assets to SD Card and vice versa
                synchronized (this) {

                    Toast.makeText(getBaseContext(), "Loading Tracks from SD Card",
                            Toast.LENGTH_SHORT).show();
                }
                initialize();
                return true;
            default:
                return false;
        }
    }

    private void createMenu(Menu menu) {

    }

    public void click(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btnPlay:

                synchronized (this) {
                    if (isTuning) {

                        isTuning = false;
                        btnPlay.setBackgroundResource(R.drawable.play);
                        track.pause();

                    } else {

                        isTuning = true;
                        btnPlay.setBackgroundResource(R.drawable.pause);
                        playTrack();
                    }
                }
                return;

            case R.id.btnStop:
                synchronized (this) {
                    if (track.isPlaying() == true) {

                        isTuning = false;
                        btnPlay.setBackgroundResource(R.drawable.play);
                        track.stop();
                        // track.pause();

                    }
                }
                loadTrack();
                return;

            default:
                return;
        }
    }

    private void setTrack(int direction) {

        currentTrack = trackNames.size() - 1;

        if (shuffle) {
            int temp = random.nextInt(trackNames.size());
            while (true) {
                if (temp != currentTrack) {
                    currentTrack = temp;
                    break;
                }
                temp++;
                if (temp > trackNames.size() - 1) {
                    temp = 0;
                }
            }
        }
    }

    // Plays the Track
    private void playTrack() {
        if (isTuning && track != null) {
            track.play();

            Toast.makeText(
                    getBaseContext(), "Playing " + musicTitle(trackNames.get(currentTrack)),
                    Toast.LENGTH_SHORT).show();

            //"Playing "+ trackNames.get(currentTrack).substring(0 ,trackNames.get(currentTrack).length() - 4)
        }
    }

    private String musicTitle(String path) {

        String[] title;

        title = path.split("/");
        return title[title.length - 1];

    }


    // Simply sets shuffle to isShuffle and then displays a message for
    // confirmation
    private void setShuffle(boolean isShuffle) {
        shuffle = isShuffle;
        if (shuffle) {
            Toast.makeText(getBaseContext(), "Shuffle On", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(getBaseContext(), "Shuffle Off", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}