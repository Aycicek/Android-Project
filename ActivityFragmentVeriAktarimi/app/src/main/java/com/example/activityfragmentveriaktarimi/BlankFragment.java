package com.example.activityfragmentveriaktarimi;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BlankFragment extends Fragment {

    Button butoonhesapla;
    TextView goster;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_blank,container,false);

        butoonhesapla=view.findViewById(R.id.hesaplaButton);
        goster=view.findViewById(R.id.textView);

        Bundle bundle=getArguments();
        final int sayi1=bundle.getInt("sayi1");
        final int sayi2=bundle.getInt("sayi2");


        butoonhesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sonuc=sayi1+sayi2;
                goster.setText("Sonuç: "+sonuc);

            }
        });
        return view;

    }


}
