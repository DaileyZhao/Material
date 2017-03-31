package com.zcm.support.picview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcm.support.R;

/**
 * Zoomable image viewer fragment
 */
public class PicDetailFragment extends Fragment {
    protected final String TAG=this.getClass().getSimpleName();
    private static final String KEY_URL = "KEY_URL";
    ImageView imageView;

    public static PicDetailFragment newInstance(String url) {
        PicDetailFragment approvalFragment = new PicDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        approvalFragment.setArguments(bundle);
        return approvalFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: "+getArguments().getString(KEY_URL));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        imageView = (ImageView) view.findViewById(R.id.iv_image);
        init();
        return view;
    }

    private void init() {
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        loadImage();

    }

    void loadImage() {
        Glide.with(this).load(getArguments().getString(KEY_URL)).placeholder(R.drawable.error_picture).into(imageView);
    }

}
