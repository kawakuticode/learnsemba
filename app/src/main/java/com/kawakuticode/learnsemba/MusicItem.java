/**
 * 
 */
package com.kawakuticode.learnsemba;

/**
 * @author Russelius
 * 
 */
public class MusicItem {

	private String title;
	private String description;
	private int imageSinger;
	private int playButton;

	public MusicItem(int imageKonde, String title, String description,
			int playButton) {
		super();
		this.imageSinger = imageKonde;
		this.title = title;
		this.description = description;
		this.playButton = playButton;
	}

	// getters and setters...

	/**
	 * @return the imageKonde
	 */
	public int getImageSinger() {
		return imageSinger;
	}

	/**
	 * @param imageKonde
	 *            the imageKonde to set
	 */
	public void setImageKonde(int imageKonde) {
		this.imageSinger = imageKonde;
	}

	/**
	 * @return the playButton
	 */
	public int getPlayButton() {
		return playButton;
	}

	/**
	 * @param playButton
	 *            the playButton to set
	 */
	public void setPlayButton(int playButton) {
		this.playButton = playButton;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public CharSequence getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public CharSequence getTitle() {
		// TODO Auto-generated method stub
		return this.title = title;
	}
}
