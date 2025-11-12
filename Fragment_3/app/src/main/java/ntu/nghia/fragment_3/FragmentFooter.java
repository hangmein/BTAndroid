package ntu.nghia.fragment_3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class FragmentFooter extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_footer, container, false);
        //find view
        MaterialButton b1 = v.findViewById(R.id.button1);
        MaterialButton b2 = v.findViewById(R.id.button2);
        MaterialButton b3 = v.findViewById(R.id.button3);
        //
        FragmentManager fragmentManager = getParentFragmentManager();
        //event
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content, new FragmentOne())
                        .commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content, new FragmentTwo())
                        .commit();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content, new FragmentThree())
                        .commit();
            }
        });
        return v;
    }
}