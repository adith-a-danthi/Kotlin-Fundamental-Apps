package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()

    }

    private fun setListeners() {

        val boxOneText = findViewById<TextView>(R.id.box_one)
        val boxTwoText = findViewById<TextView>(R.id.box_two)
        val boxThreeText = findViewById<TextView>(R.id.box_three)
        val boxFourText = findViewById<TextView>(R.id.box_four)
        val boxFiveText: TextView = findViewById(R.id.box_five)

        val rootConstraintLayout: ConstraintLayout = findViewById(R.id.constraint_layout)

        val redBtn = findViewById<Button>(R.id.red_btn)
        val yellowBtn = findViewById<Button>(R.id.yellow_btn)
        val greenBtn = findViewById<Button>(R.id.green_btn)

        val clickableViews: List<View> = listOf(boxOneText,boxTwoText,boxThreeText,
            boxFourText,boxFiveText,rootConstraintLayout,redBtn,yellowBtn,greenBtn)

        val colorViews: List<View> = listOf(boxOneText,boxTwoText,boxThreeText,
            boxFourText,boxFiveText)

        val resetBtn = findViewById<Button>(R.id.reset_btn)
        resetBtn.setOnClickListener {
            for (item in colorViews){
                item.setBackgroundColor(Color.WHITE)
            }
            rootConstraintLayout.setBackgroundResource(R.color.default_background)
        }

        for (item in clickableViews){
            item.setOnClickListener{ makeItColoured(it) }
        }
    }



    private fun makeItColoured(view: View) {
        when(view.id){
            R.id.box_one -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two -> view.setBackgroundColor(Color.GRAY)
            R.id.box_three -> view.setBackgroundColor(Color.BLUE)
            R.id.box_four -> view.setBackgroundColor(Color.MAGENTA)
            R.id.box_five -> view.setBackgroundColor(Color.CYAN)

            R.id.red_btn -> box_three.setBackgroundResource(R.color.myRed)
            R.id.yellow_btn -> box_four.setBackgroundResource(R.color.myYellow)
            R.id.green_btn -> box_five.setBackgroundResource(R.color.myGreen)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
