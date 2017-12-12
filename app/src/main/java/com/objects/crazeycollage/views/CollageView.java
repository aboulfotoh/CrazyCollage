package com.objects.crazeycollage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.objects.crazeycollage.R;
import com.objects.crazeycollage.utils.ImageLoaderUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * Collage View is the parent view of a collage items.
 * 
 * @author Juan Carlos Moreno (jcmore2@gmail.com)
 * 
 */
public class CollageView extends RelativeLayout {

	private Context mContext;

	private final String BG = "#FFD4B081";

	private int collageWidth;
	private int collageHeight;

	//private List<CardView> listCards = new ArrayList<>();
	private List<ItemView> listItems = new ArrayList<>();
	private boolean isViewRefresh = false;
	private boolean isCollageFixed = false;

	private final Random random = new Random();

	public CollageView(Context context) {
		this(context, null);
		init(context);
	}

	public CollageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init(context);
	}

	public CollageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		this.setMotionEventSplittingEnabled(true);
		this.setBackgroundResource(R.drawable.wooden_floor);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		collageWidth = MeasureSpec.getSize(widthMeasureSpec);

		collageHeight = MeasureSpec.getSize(heightMeasureSpec);

		refreshViews();

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	/**
	 * Function use to refresh Cards when Collage has measure
	 */
	private void refreshViews() {
		if (!listItems.isEmpty() && !isViewRefresh) {
			for (ItemView cardView : listItems) {
				LayoutParams params = new LayoutParams(
						800, 700);
				int left = random.nextInt(collageWidth) - collageWidth / 4;
				int top = random.nextInt(collageHeight) - collageHeight / 4;
				params.leftMargin = left;
				params.topMargin = top;
				params.rightMargin = -left;
				params.bottomMargin = -top;

				if (isCollageFixed)
					cardView.setFixedItem();

				this.addView(cardView,params);

			}
			isViewRefresh = true;
		}
	}

	/**
	 * Methos use to set Collage fixed (Cards cant move)
	 * 
	 * @param fixedCollage
	 */
	public void setFixedCollage(boolean fixedCollage) {
		isCollageFixed = fixedCollage;
	}

	/**
	 * Add Item
	 *
	 * @param url
	 */
	public void addItem(String url) {
		final ItemView card = new ItemView(mContext);
		ImageLoader.getInstance().displayImage(url, card.getImageView(), ImageLoaderUtility.getDisplayImageOptions(), new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {

			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				card.getTextView().setVisibility(VISIBLE);

			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {

			}
		});
		addViewToList(card);
	}

	private void addViewToList(ItemView card) {
		listItems.add(card);
	}


	/**
	 * Create a Collage
	 *
	 * @param stringList
	 *            List of URLs
	 */
	public void createCollageUrl(List<String> stringList) {
		for (String url : stringList) {
			addItem(url);
		}
	}
}