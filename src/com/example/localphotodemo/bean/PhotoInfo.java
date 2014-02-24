package com.example.localphotodemo.bean;

import java.io.Serializable;

/**
 * 
 * 本地相册图片bean<br>
 *  {@link #imageId}图片id<br>
 *  {@link #absolutePath} 绝对路径<br>
 *  {@link #file_path} 用于显示的路径<br>
 *  {@link #choose} 是否被选择<br>
 */
public class PhotoInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int imageId;
	private String filePath;
	private String absolutePath;
	private boolean choose = false;
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int id) {
		this.imageId = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public boolean isChoose() {
		return choose;
	}
	public void setChoose(boolean choose) {
		this.choose = choose;
	}
}
