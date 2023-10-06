package com.example.warpopenvpnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;

public class StandardBottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_bottom_sheet);
        FrameLayout standardBottomSheet = findViewById(R.id.standard_bottom_sheet);
        BottomSheetBehavior        standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet);
        standardBottomSheetBehavior.setPeekHeight(300,true);
        standardBottomSheetBehavior.setSaveFlags(BottomSheetBehavior.SAVE_ALL);
        standardBottomSheetBehavior.setFitToContents(false);
        standardBottomSheetBehavior.setExpandedOffset(701);
        standardBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        standardBottomSheetBehavior.setHideable(false);
        BottomSheetDragHandleView bottomSheetDragHandleView = findViewById(R.id.drag_handle);
       
    }
}