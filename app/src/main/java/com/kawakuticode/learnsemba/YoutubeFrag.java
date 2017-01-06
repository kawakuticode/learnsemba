package com.kawakuticode.learnsemba;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import cz.msebera.android.httpclient.util.EntityUtils;

public class YoutubeFrag extends ListFragment {


    ArrayList<YoutubeBean> tmp_videos = new ArrayList<YoutubeBean>();
    ProgressDialog pd;

    public YoutubeFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Loading....... ");

        new YouTubeAsyncSearch().execute();

        return inflater.inflate(R.layout.list_fragment, container, false);

    }

    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {
        YoutubeBean item = (YoutubeBean) list.getAdapter().getItem(position);
        launchVideoPlayerYoutube(item.getId());

    }


    public void getData() {

        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/search?part=snippet&q=Mr.Tecas&maxResults=50&type=video&order=date&key=AIzaSyBxSE61XG4570uPVo5YfQkf_13z88RKMIk");

        try {
            HttpResponse response = httpclient.execute(request);
            HttpEntity resEntity = response.getEntity();
            String _response = EntityUtils.toString(resEntity); // content will be consume only once

            JSONObject json = new JSONObject(_response);

            JSONArray jsonArray = json.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                YoutubeBean ybean = new YoutubeBean();


                JSONObject jsonObject = jsonArray.getJSONObject(i);

                ybean.setTitle(jsonObject.getJSONObject("snippet").getString("title"));
                ybean.setId(jsonObject.getJSONObject("id").getString("videoId"));
                String thumbUrl = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");

                URL url1 = new URL(thumbUrl);
                Bitmap bmp = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                ybean.setThumbnails(bmp);

                tmp_videos.add(ybean);

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        httpclient.getConnectionManager().shutdown();
    }




    public void launchVideoPlayerYoutube(String video_id) {
        Intent myIntent = new Intent(getActivity(), VideoWatchYouTube.class);
        myIntent.putExtra("ID_VIDEO", video_id);
        getActivity().startActivity(myIntent);

    }

    class YouTubeAsyncSearch extends AsyncTask<Void, Void, Void> {



        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            getData();
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pd.dismiss();
            Collections.shuffle(tmp_videos);
            ListAdapter listAdapter = new YouTubeAdapter(getActivity(), tmp_videos);
            setListAdapter(listAdapter);

        }
    }

}