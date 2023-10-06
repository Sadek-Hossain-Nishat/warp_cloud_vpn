package com.example.warpopenvpnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    Button button;

    ModalBottomSheet modalBottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        modalBottomSheet = new ModalBottomSheet();







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    modalBottomSheet.show(getSupportFragmentManager(), ModalBottomSheet.TAG);





            }
        });
    }
}