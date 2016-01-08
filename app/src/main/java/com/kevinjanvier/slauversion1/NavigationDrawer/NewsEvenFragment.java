package com.kevinjanvier.slauversion1.NavigationDrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinjanvier.slauversion1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsEvenFragment extends Fragment {


    public NewsEvenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_even, container, false);
    }

}
