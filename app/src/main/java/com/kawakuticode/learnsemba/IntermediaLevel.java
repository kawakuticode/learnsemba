package com.kawakuticode.learnsemba;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;

/**
 * @author Russelius Ernestius
 * 
 */

public class IntermediaLevel extends ListFragment {

	private String intermediats[];
	private Integer[] imageId;
	private ProgressDialog progressDialog;
	private String[] contents = new String[3];
	private Integer[] imageButton;
	private Utilities utilities;

	public IntermediaLevel() {

		intermediats = new String[]{"giro basico", "saida homem",
				"saida mulher", "corridinho", "corridinho pra tras",};

		imageId = new Integer[]{R.drawable.icones1, R.drawable.icones2,
				R.drawable.icones3, R.drawable.icone4s, R.drawable.corridinho};
		imageButton = new Integer[]{R.drawable.arrow, R.drawable.arrow,
				R.drawable.arrow, R.drawable.arrow, R.drawable.arrow};
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListAdapter listAdapter = new CustomList(getActivity(), intermediats,
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
		File toPlay;
		int temp_file_size ;
		String folder = "/semba/";
		utilities = new Utilities(getActivity());

		if (utilities.isExternalStorageAvailable() == true) {


			switch (item) {

				case "giro basico":

					toPlay =  new File(getActivity().getFilesDir(), item);
					temp_file_size = new Integer(13475060);

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

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGbjlXU2RGcElLdVk";

							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

							new DownloadAsyncFile(getActivity(), progressDialog)
									.execute(contents);

						}
					}
					break;

				case "saida homem":

					toPlay =  new File(getActivity().getFilesDir(), item);

					temp_file_size = new Integer(24040991);

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

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGLVUyblYwQjlxLUk";
							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

							new DownloadAsyncFile(getActivity(), progressDialog)
									.execute(contents);

						}
					}
					break;
				case "saida mulher":

					toPlay =  new File(getActivity().getFilesDir(), item);
					temp_file_size = new Integer(12852824);
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

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGdDVYVURWR2FLN0U";
							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

							new DownloadAsyncFile(getActivity(), progressDialog)
									.execute(contents);
						}
					}
					break;
				case "corridinho":

                    toPlay =  new File(getActivity().getFilesDir(), item);
                    temp_file_size = new Integer(13294197);

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

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGSVpranY2N2JldTA";
							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

							new DownloadAsyncFile(getActivity(), progressDialog)
									.execute(contents);
						}
					}
					break;

				case "corridinho pra tras":

                    toPlay =  new File(getActivity().getFilesDir(), item);
                    temp_file_size = new Integer(9137392);

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

							url = "https://drive.google.com/uc?export=download&id=0B2w_WoypwqQGQmlXQmk1QzVaeGc";
							contents[0] = url;
							contents[1] = toPlay.getName();
							contents[2] = String.valueOf(temp_file_size);

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