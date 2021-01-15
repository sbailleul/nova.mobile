package com.esgi.nova

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.esgi.nova.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dashboard.*
@AndroidEntryPoint
class Dashboard : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        spn_difficulty.adapter =
            ArrayAdapter(this, R.layout.spinner_item, listOf("Facile", "Moyen", "Difficile"))
        btn_to_leaderboard.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == btn_to_leaderboard) {
            val intent = Intent(this, LeaderBoard::class.java)
            startActivity(intent);
        }
    }
}