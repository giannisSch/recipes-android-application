package com.foodes.recipeapp.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodes.recipeapp.R;


public class OnboardingTwo extends Fragment {

    TextView previous,next;
    ViewPager viewPager;

    public OnboardingTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_onboarding_two, container, false);

        //Viewpager from Onboarding Activity
        viewPager = getActivity().findViewById(R.id.viewPager);

        previous = view.findViewById(R.id.previous_btn);
        next = view.findViewById(R.id.next_btn_two);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        return view;
    }
}