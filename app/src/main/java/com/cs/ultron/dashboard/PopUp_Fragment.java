package com.cs.ultron.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.cs.ultron.R;

public class PopUp_Fragment extends Fragment {

    Button getStarted, dismiss;

    public static PopUp_Fragment newInstance() {
        PopUp_Fragment popUpFragment = new PopUp_Fragment();
        return popUpFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_layout,container,false);

        getStarted = view.findViewById(R.id.start_button);
        dismiss = view.findViewById(R.id.button2);
        CardView startCard = view.findViewById(R.id.startcard);

         Animation fromBottom = AnimationUtils.loadAnimation(getContext(),R.anim.frombottom);

        startCard.setAnimation(fromBottom);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dashboard.class);
                startActivity(intent);
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });

        return view;
    }
}
