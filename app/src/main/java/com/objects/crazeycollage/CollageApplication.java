package com.objects.crazeycollage;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Ahmed Aboulfotoh on 12/10/2017.
 */

public class CollageApplication extends Application {
    private final int MAX_SIZE_DISK_CACHE = 30;
    private static CollageApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        mInstance = this;

    }

    private void initImageLoader(Context context) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width_screen = display.getWidth();
        int height_screen = display.getHeight();
        int memoryCacheSize;
        int minMemory = 2097152; // 2 MB
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        memoryCacheSize = Math.min(minMemory, (memClass / 8) * 1048576); // 1/8 of app memory limit - 1048576 = 1024*1024
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheExtraOptions(width_screen, height_screen)
                .memoryCacheSize(memoryCacheSize).diskCacheExtraOptions(width_screen, height_screen,null)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(MAX_SIZE_DISK_CACHE * 1048576)
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);

    }
}
