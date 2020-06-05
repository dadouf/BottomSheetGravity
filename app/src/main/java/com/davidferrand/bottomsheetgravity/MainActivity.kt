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
            peekHeight = 600 // TODO do it for auto = 16:9 keyline

            // TODO onLayout

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset <= 0f) {
                        // BottomSheet is between HIDDEN (visibleHeight=0) and COLLAPSED (visibleHeight=peekHeight)
                        pinned_center.translationY =
                            (slideOffset + 1) * (peekHeight - pinned_center.height) / 2
                    } else {
                        // BottomSheet is between COLLAPSED (visibleHeight=peekHeight) and EXPANDED (visibleHeight=parent.height)
                        pinned_center.translationY =
                            (1 - slideOffset) * (peekHeight - pinned_center.height) / 2 +
                                    slideOffset * (bottomSheet.height - pinned_center.height) / 2
                    }
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