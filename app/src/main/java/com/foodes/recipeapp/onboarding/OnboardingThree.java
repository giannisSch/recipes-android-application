package com.foodes.recipeapp.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodes.recipeapp.LoginActivity;
import com.foodes.recipeapp.R;


public class OnboardingThree extends Fragment {

    TextView previous, startBtn;
    ViewPager viewPager;

    public OnboardingThree() {
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
        View view =  inflater.inflate(R.layout.fragment_onboarding_three, container, false);

        //Viewpager from Onboarding Activity
        viewPager = getActivity().findViewById(R.id.viewPager);

        previous = view.findViewById(R.id.previous_three_btn);
        startBtn = view.findViewById(R.id.get_started_btn);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginScreen();
            }
        });

        return view;
    }

    private void goToLoginScreen(){
        Intent login = new Intent(getActivity().getApplication(), LoginActivity.class);
        startActivity(login);
    }

}
