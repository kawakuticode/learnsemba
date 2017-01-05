package com.kawakuticode.learnsemba;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ConfirmedLevel extends ListFragment {

	private String confirmeds[];
	private Integer[] imageId;

	private ProgressDialog progressDialog;
	private String[] contents = new String[3];
	private Integer[] imageButton;
	private Utilities utilities;

	public ConfirmedLevel() {
		confirmeds = new String[] { "saida jajao", "saida invertida", "sangazuza", "sangazuza com pico", "tips",

		};

		imageId = new Integer[] { R.drawable.icones5, R.drawable.icones6,
				R.drawable.icones7, R.drawable.ghomem, R.drawable.icones1,};

		imageButton = new Integer[] { R.drawable.arrow, R.drawable.arrow,
				R.drawable.arrow, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow };
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ListAdapter listAdapter = new CustomList(getActivity(), confirmeds,
				imageId, imageButton);
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

		String url = "";
		int temp_file_size;
		String folder = "/semba/";
		utilities = new Utilities(getActivity());


		File toPlay;

		if (utilities.isExternalStorageAvailable() == true) {

			switch (item) {

				case "saida jajao":

					toPlay = new File(getActivity().getFilesDir(), item);
					temp_file_size = new Integer(14021182);

					if (toPlay.exists() && toPlay.length() == temp_file_size) {

						utilities.launchVideoPlayer(toPlay);

					} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
							&& utilities.isOnline() == false) {

						utilities.showAlertDialog(getActivity(), "No Internet Connection",
								"Turn On your Connection to Download the file.",
								false);
						break;

					} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
							&& utilities.isOnline() == true) {

						if (utilities.checkSpaceOnCard(temp_file_size) == true) {
							utilities.showAlertDialog(
									getActivity(),
									"No Enough Space ",
									"Delete some file on your sdCard and try again.",
									false);
							break;
						} else {

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGTE1BVUlWYndJTU0";
							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

							new DownloadAsyncFile(getActivity(), progressDialog)
									.execute(contents);

						}
					}
					break;
				case "saida invertida":

					toPlay = new File(getActivity().getFilesDir(), item);
					temp_file_size = new Integer(12584649);

					if (toPlay.exists() && toPlay.length() == temp_file_size) {
						utilities.launchVideoPlayer(toPlay);

					} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
							&& utilities.isOnline() == false) {

						utilities.showAlertDialog(getActivity(), "No Internet Connection",
								"Turn On your Connection to Download the file.",
								false);
						break;

					} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
							&& utilities.isOnline() == true) {

						if (utilities.checkSpaceOnCard(temp_file_size) == true) {
							utilities.showAlertDialog(
									getActivity(),
									"No Enough Space ",
									"Delete some file on your sdCard and try again.",
									false);
							break;
						} else {

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGQWNZekFKYXJvdU0";
							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

							new DownloadAsyncFile(getActivity(), progressDialog)
									.execute(contents);
						}
					}
					break;


			case "sangazuza":

				toPlay = new File(getActivity().getFilesDir(), item);
				temp_file_size = new Integer(11492117);

				if (toPlay.exists() && toPlay.length() == temp_file_size) {

					utilities.launchVideoPlayer(toPlay);

				} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
						&& utilities.isOnline() == false) {

					utilities.showAlertDialog(getActivity(), "No Internet Connection",
							"Turn On your Connection to Download the file.",
							false);
					break;

				} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
						&& utilities.isOnline() == true) {

					if (utilities.checkSpaceOnCard(temp_file_size) == true) {
						utilities.showAlertDialog(
								getActivity(),
								"No Enough Space ",
								"Delete some file on your sdCard and try again.",
								false);
						break;
					} else {

						url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGWXlxQXEyTzBWdmc";

						contents[0] = url;
						contents[1] = toPlay.getName();
						contents[2] = String.valueOf(temp_file_size);

						new DownloadAsyncFile(getActivity(), progressDialog)
								.execute(contents);

					}
				}
				break;

			case "sangazuza com pico":

				toPlay = new File(getActivity().getFilesDir(), item);
				temp_file_size = new Integer(14764932);

				if (toPlay.exists() && toPlay.length() == temp_file_size) {

					utilities.launchVideoPlayer(toPlay);

				} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
						&& utilities.isOnline() == false) {

					utilities.showAlertDialog(getActivity(), "No Internet Connection",
							"Turn On your Connection to Download the file.",
							false);
					break;

				} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
						&& utilities.isOnline() == true) {

					if (utilities.checkSpaceOnCard(temp_file_size) == true) {
						utilities.showAlertDialog(
								getActivity(),
								"No Enough Space ",
								"Delete some file on your sdCard and try again.",
								false);
						break;
					} else {

						url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGd2lhRWU4cnFWTjg";
						contents[0] = url;
						contents[1] = toPlay.getName();

						new DownloadAsyncFile(getActivity(), progressDialog)
								.execute(contents);
					}
				}
				break;

			case "tips":

				toPlay = new File(getActivity().getFilesDir(), item);
				temp_file_size = new Integer(9033412);

				if (toPlay.exists() && toPlay.length() == temp_file_size) {

					utilities.launchVideoPlayer(toPlay);

				} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
						&& utilities.isOnline() == false) {

					utilities.showAlertDialog(getActivity(), "No Internet Connection",
							"Turn On your Connection to Download the file.",
							false);
					break;

				} else if ((toPlay.exists() || toPlay.length() < temp_file_size)
						&& utilities.isOnline() == true) {

					if (utilities.checkSpaceOnCard(temp_file_size) == true) {
						utilities.showAlertDialog(
								getActivity(),
								"No Enough Space ",
								"Delete some file on your sdCard and try again.",
								false);
						break;
					} else {

						url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGbFlEQnRsOXUxaTQ";
						contents[0] = url;
						contents[1] = toPlay.getName();

						new DownloadAsyncFile(getActivity(), progressDialog)
								.execute(contents);

					}
				}
				break;

			default:
				break;
			}
		} else {
			utilities.showAlertDialog(getActivity(), "External Card Not Available",
					"Insert Your SDCard to Read/Write Files", false);

		}

	}

	public void onDestroy() {
		super.onDestroy();
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}



}