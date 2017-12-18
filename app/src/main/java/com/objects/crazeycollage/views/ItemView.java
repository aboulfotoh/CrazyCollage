package com.objects.crazeycollage.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.objects.crazeycollage.R;
import com.objects.crazeycollage.utils.MultiTouchListener;

/**
 * Created by Ahmed Aboulfotoh on 12/10/2017.
 */

public class ItemView extends RelativeLayout {
    private MultiTouchListener mtl;
    /*private static final int PADDING = 10;
    private static final float STROKE_WIDTH = 20.0f;
    private Paint mBorderPaint;*/
    private ImageView imageView;
    private TextView textView;
    private LayoutInflater layoutInflater;
    private Context context;

    public ItemView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_view, this, true);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        mtl = new MultiTouchListener(context);
        this.setOnTouchListener(mtl);
        mtl.setRandomPosition(this);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setFixedItem() {
        mtl.isRotateEnabled = false;
        mtl.isScaleEnabled = false;
        mtl.isTranslateEnabled = false;
    }
}
