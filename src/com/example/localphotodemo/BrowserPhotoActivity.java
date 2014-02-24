package com.example.localphotodemo;

import java.util.ArrayList;
import java.util.List;

import com.example.localphotodemo.adapter.BrowserPhotoAdapter;
import com.example.localphotodemo.bean.PhotoInfo;
import com.example.localphotodemo.bean.PhotoSerializable;
import com.example.localphotodemo.widget.ImageCountTextView;
import com.example.localphotodemo.widget.ScrollViewPager;
import com.example.localphotodemo.widget.ZoomOutPageTransformer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class BrowserPhotoActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {
	private static final String TAG = BrowserPhotoActivity.class.getSimpleName();
	private List<PhotoInfo> mPhotoLists = new ArrayList<PhotoInfo>();
	
	private ScrollViewPager mScrollViewPager;
	private ImageCountTextView mImageTextView;
	private BrowserPhotoAdapter mAdapter;
	private String mPath;
	private int mCurrentPos;
	private int mTotalSize;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_browser_browser);
		initViews();
		initEvents();
		init();
	}

	protected void initViews() {
		mScrollViewPager = (ScrollViewPager) findViewById(R.id.image_browser_pager);
		mImageTextView = (ImageCountTextView) findViewById(R.id.image_browser_text);
	}

	protected void initEvents() {
		mScrollViewPager.setOnPageChangeListener(this);
	}

	private void init() {
		mCurrentPos = getIntent().getIntExtra("current_pos", 0);
		Bundle bundle = getIntent().getExtras(); 
		PhotoSerializable photoSerializable = (PhotoSerializable) bundle.getSerializable("photos");
		
		mPhotoLists.addAll(photoSerializable.getList());
		mTotalSize = mPhotoLists.size();
		
		// 当前图片的位置
		//mCurrentPos = mPhotoLists.indexOf(mPath);			
		if (mCurrentPos > mTotalSize) {
			mCurrentPos = mTotalSize - 1;
		}
		
		if (mTotalSize > 1) {
			mCurrentPos += 1000 * mTotalSize;
			mImageTextView.setText((mCurrentPos % mTotalSize) + 1 + "/" + mTotalSize);
			mAdapter = new BrowserPhotoAdapter(mPhotoLists);
			
		} else {
			mImageTextView.setText("1/1");
			mAdapter = new BrowserPhotoAdapter(mPhotoLists);
		}
		
		mScrollViewPager.setAdapter(mAdapter);
		mScrollViewPager.setCurrentItem(mCurrentPos, false);
		mScrollViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		mCurrentPos = arg0;
		mImageTextView.setText((mCurrentPos % mTotalSize) + 1 + "/" + mTotalSize);
	}

	@Override
	public void onClick(View arg0) {
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(0, R.anim.zoom_exit);
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPhotoLists.clear();
		mPhotoLists = null;
	}
}
