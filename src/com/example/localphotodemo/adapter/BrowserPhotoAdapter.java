package com.example.localphotodemo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.localphotodemo.R;
import com.example.localphotodemo.bean.PhotoInfo;
import com.example.localphotodemo.imageaware.RotateImageViewAware;
import com.example.localphotodemo.photoview.PhotoView;
import com.example.localphotodemo.util.ThumbnailsUtil;
import com.example.localphotodemo.util.UniversalImageLoadTool;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;


public class BrowserPhotoAdapter extends PagerAdapter {

	private List<PhotoInfo> mImages = new ArrayList<PhotoInfo>();

	public BrowserPhotoAdapter(List<PhotoInfo> images) {
		if (images != null) {
			mImages = images;
		}
	}

	@Override
	public int getCount() {
		if (mImages.size() > 1) {
			return Integer.MAX_VALUE;
		}
		return mImages.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		PhotoView photoView = new PhotoView(container.getContext());

		//photoView.setBitmap(mImages.get(position % mImages.size()).getAbsolutePath());
		container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		final PhotoInfo photoInfo = mImages.get(position % mImages.size());
		if (photoInfo != null) {
			UniversalImageLoadTool.disPlay(ThumbnailsUtil.MapgetHashValue(photoInfo.getImageId(), photoInfo.getFilePath()), new RotateImageViewAware(photoView, photoInfo.getAbsolutePath()),
					R.drawable.common_defalt_bg);
		}
		return photoView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
