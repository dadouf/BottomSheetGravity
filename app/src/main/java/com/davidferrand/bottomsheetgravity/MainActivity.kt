package com.davidferrand.bottomsheetgravity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BottomSheetBehavior.from(bottom_sheet).apply {
            isHideable = true
            state = BottomSheetBehavior.STATE_HIDDEN

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    val bottomSheetVisibleHeight = bottomSheet.height - bottomSheet.top

                    pinned_center.translationY =
                        (bottomSheetVisibleHeight - pinned_center.height) / 2f

                    pinned_bottom.translationY =
                        (bottomSheetVisibleHeight - pinned_bottom.height).toFloat()
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }
            })
        }

        btn_open.setOnClickListener {
            BottomSheetBehavior.from(bottom_sheet).state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}