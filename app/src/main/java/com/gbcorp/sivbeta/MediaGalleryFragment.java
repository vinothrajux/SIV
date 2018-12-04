package com.gbcorp.sivbeta;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.FixedPreloadSizeProvider;

import java.util.Collections;
import java.util.List;

/**
 * Created by Seetha on 23-Nov-18.
 */

public class MediaGalleryFragment extends Fragment {
    // These are totally arbitrary, pick sizes that are right for your UI.
//    private final int imageWidthPixels = 1024;
//    private final int imageHeightPixels = 768;
//    // You will need to populate these urls somewhere...
//    private List<String> myUrls = ...;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view =  inflater.inflate(R.layout.admissioncounselling, container, false);
//        ListPreloader.PreloadSizeProvider sizeProvider =
//                new FixedPreloadSizeProvider(imageWidthPixels, imageHeightPixels);
//        ListPreloader.PreloadModelProvider modelProvider = new MyPreloadModelProvider();
//        RecyclerViewPreloader<ContactsContract.CommonDataKinds.Photo> preloader =
//                new RecyclerViewPreloader<>(
//                        Glide.with(this), modelProvider, sizeProvider, 10 /*maxPreload*/);
//
//        RecyclerView myRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        myRecyclerView.addOnScrollListener(preloader);
//
//        // Finish setting up your RecyclerView etc.
//        myRecyclerView.setLayoutManager(...);
//        myRecyclerView.setAdapter(...);
//        return view;
//    }
//    private abstract class MyPreloadModelProvider implements ListPreloader.PreloadModelProvider {
//        @Override
//        @NonNull
//        public List<U> getPreloadItems(int position) {
//            String url = myUrls.get(position);
//            if (TextUtils.isEmpty(url)) {
//                return Collections.emptyList();
//            }
//            return Collections.singletonList(url);
//        }
//
//        @Override
//        @Nullable
//        public RequestBuilder getPreloadRequestBuilder(String url) {
//            return
//                    GlideApp.with(fragment)
//                            .load(url)
//                            .override(imageWidthPixels, imageHeightPixels);
//        }
//    }
}
