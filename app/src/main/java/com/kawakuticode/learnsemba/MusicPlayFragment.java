package com.kawakuticode.learnsemba;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

@SuppressLint("NewApi")
public class MusicPlayFragment extends ListFragment {


    Context context;
    private String[] contents = new String[2];
    ArrayList<MusicItem> items;

    private ProgressDialog progressDialog;
    MediaPlayer mp;

    //
    // private ProgressDialog progressDialog;
    // private String[] contents = new String[2];


    public MusicPlayFragment() {

        this.items = new ArrayList<MusicItem>();

        generateData(items);




    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_music_play);

        // setContentView(R.id.lists);
        // 1. pass getActivity() and data to the custom adapter
        MusicAdapter adapter = new MusicAdapter(getActivity(), items);

        // 2. Get ListView from activity_main.xml
        // ListView listView = (ListView) findViewById(R.id.listviewer);

        // 3. setListAdapter
        // listView.setAdapter(adapter);
        setListAdapter(adapter);

    }

    private ArrayList<MusicItem> generateData(ArrayList<MusicItem> items) {


        items.add(new MusicItem(R.drawable.konde2, "Konde Martins - negra",
                "cd dança comigo", R.drawable.arrow));
        items.add(new MusicItem(R.drawable.konde2, "Konde Martins - dj ",
                "cd picante vol.5", R.drawable.arrow));
        items.add(new MusicItem(R.drawable.konde2, "Konde Martins - katia",
                "cd sina", R.drawable.arrow));

        items.add(new MusicItem(R.drawable.knegra, "Konde Martins - Negra",
                "video", R.drawable.arrow));
        items.add(new MusicItem(R.drawable.kvaidoer,
                "Konde Martins - vai doer ", "video", R.drawable.arrow));
        items.add(new MusicItem(R.drawable.kesconde,
                "Konde Martins - Esconde Esconde", "video", R.drawable.arrow));

        return items;
    }

    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {

        String aux = "";
        String s = list.getAdapter().getItemId(position) + "";

        String url = "";
        String folder = "/semba/";
        File musicFile;
        int musicFileSizep;
        String imagePath = "";

        if (isExternalStorageAvailable() == true) {

            imagePath = Environment.getExternalStorageDirectory()
                    .toString() + folder;
        }

        switch (s) {

            case "0":

                aux = "Konde Martins - Negra.mp3";

                musicFile = new File(imagePath, aux);

                musicFileSizep = 3714339;

                if (musicFile.exists() && musicFile.length() == musicFileSizep) {

                    Intent myIntent = new Intent(getActivity(),
                            MusicViewActivity.class);

                    myIntent.putExtra("pathMusic", musicFile.getPath());
                    MusicPlayFragment.this.startActivity(myIntent);

                } else if ((musicFile.exists() || musicFile.length() < musicFileSizep)
                        && isOnline() == true) {

                    if (checkSpaceOnCard(musicFileSizep) == true) {
                        showAlertDialog(getActivity(), "No Enough Space ",
                                "Delete some file on your sdCard and try again.",
                                false);
                        break;

                    } else {

                        url = "https://dl.dropboxusercontent.com/s/du9pkrxz12iujkh/negra.mp3?dl=1";

                        contents[0] = url;

                        contents[1] = aux;

                        new DownloadAsyncFile(getActivity(),
                                progressDialog).execute(contents);

                    }

                } else if ((musicFile.exists() && musicFile.length() < musicFileSizep)
                        && isOnline() == false) {

                    showAlertDialog(getActivity(), "No Internet Connection",
                            "Turn On your Connection to Download the file.", false);
                    break;

                }

                break;

            case "1":

                aux = "Konde Martins - dj.mp3";
                musicFile = new File(imagePath, aux);

                musicFileSizep = 6885993;

                if (musicFile.exists() && musicFile.length() == musicFileSizep) {

                    Intent myIntent = new Intent(getActivity(),
                            MusicViewActivity.class);

                    myIntent.putExtra("pathMusic", musicFile.getPath());
                    MusicPlayFragment.this.startActivity(myIntent);

                } else if ((musicFile.exists() || musicFile.length() < musicFileSizep)
                        && isOnline() == false) {

                    showAlertDialog(getActivity(), "No Internet Connection",
                            "Turn On your Connection to Download the file.", false);


                } else if ((musicFile.exists() || musicFile.length() < musicFileSizep)
                        && isOnline() == true) {

                    if (checkSpaceOnCard(musicFileSizep) == true) {
                        showAlertDialog(getActivity(), "No Enough Space ",
                                "Delete some file on your sdCard and try again.",
                                false);
                    } else {

                        url = "https://dl.dropboxusercontent.com/s/t82xauezm52ivsm/dj.mp3?dl=1";

                        contents[0] = url;
                        contents[1] = aux;

                        new DownloadAsyncFile(getActivity(),
                                progressDialog).execute(contents);

                    }
                }

                break;

            case "2":

                aux = "Konde Martins - katia.mp3";
                musicFile = new File(imagePath, aux);

                musicFileSizep = 6227220;

                if (musicFile.exists() && musicFile.length() == musicFileSizep) {

                    Intent myIntent = new Intent(getActivity(),
                            MusicViewActivity.class);

                    myIntent.putExtra("pathMusic", musicFile.getPath());
                    MusicPlayFragment.this.startActivity(myIntent);

                } else if ((musicFile.exists() || musicFile.length() < musicFileSizep)
                        && isOnline() == false) {

                    showAlertDialog(getActivity(), "No Internet Connection",
                            "Turn On your Connection to Download the file.", false);
                    break;

                } else if ((musicFile.exists() || musicFile.length() < musicFileSizep)
                        && isOnline() == true) {

                    if (checkSpaceOnCard(musicFileSizep) == true) {
                        showAlertDialog(getActivity(), "No Enough Space ",
                                "Delete some file on your sdCard and try again.",
                                false);
                    } else {

                        url = "https://dl.dropboxusercontent.com/s/rrrkgdpjsj97hez/katia.mp3?dl=1";

                        contents[0] = url;
                        contents[1] = aux;

                        new DownloadAsyncFile(getActivity(),
                                progressDialog).execute(contents);
                    }

                }

                break;

            case "3":

                if (isOnline() == true) {
                    url = "Le8vNkhPeuc";
                    launchVideoPlayerYouTube(url);

                } else {
                    showAlertDialog(getActivity(),
                            "No internet conection ",
                            "connect to internet and try again.", false);

                }

                break;
            case "4":

                if (isOnline() == true) {
                    url = "lwZ4kA4msVs";
                    launchVideoPlayerYouTube(url);

                } else {
                    showAlertDialog(getActivity(),
                            "No internet conection ",
                            "connect to internet and try again.", false);

                }

                break;
            case "5":
                if (isOnline() == true) {
                    url = "5GSfxsxSP8w";
                    launchVideoPlayerYouTube(url);

                } else {
                    showAlertDialog(getActivity(),
                            "No internet conection ",
                            "connect to internet and try again.", false);

                }

                break;

            default:
                break;
        }

    }

    private boolean checkSpaceOnCard(int sizeOfMusicFile) {
        // TODO Auto-generated method stub
        return Environment.getExternalStorageDirectory().getTotalSpace() < sizeOfMusicFile ? true
                : false;
    }

    public void MusicPlayer(File file) {

        System.out.println(" log ficheiro --> " + file.exists());
        System.out.println(" path do ficheiro --> " + file.getAbsolutePath());
        System.out.println(" tamanho do ficheiro  --> " + file.length());
        System.out.println(" contex  --> "
                + getActivity().getApplicationInfo());

        mp = new MediaPlayer();


        try {

            mp.setDataSource(file.getAbsolutePath());
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.prepare();
            // isPrepared = true;
            mp.start();

        } catch (Exception ex) {
            throw new RuntimeException("Couldn't load music, uh oh!");
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

    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) ? true : false;
    }

    /**
     * Function to display simple Alert Dialog
     *
     * @param context - application context
     * @param title   - alert dialog title
     * @param message - alert message
     * @param status  - success/failure (used to set icon)
     */
    @SuppressWarnings("deprecation")
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

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

    public void launchVideoPlayerYouTube(String Url) {

        Intent myIntent = new Intent(getActivity(),
                VideoWatchYouTube.class);

        myIntent.putExtra("ID_VIDEO", Url);
        this.startActivity(myIntent);

    }

    @Override
    public void onDestroy() {
        // Exemplo de código que dispensa o diálogo. Adapte às suas necessidades.
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
