package com.ibnu.footballclubs

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Horizontal Linear Layout
        verticalLayout {
            lparams(width = matchParent, height = matchParent) {
                padding = dip(16)
                horizontalGravity = 1
            }

            // ImageView for the Club Logo
            imageView(intent.getIntExtra("image", R.drawable.img_mu)).lparams(width = dip(96), height = dip(96))

            // TextView for the Club Name
            textView(intent.getStringExtra("name")) {
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams{ margin = dip(16) }

            // TextView for the Club Description
            textView(intent.getStringExtra("details")) {
                textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            }
        }
    }
}