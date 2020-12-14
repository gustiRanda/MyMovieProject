package com.gmind.mymovieproject.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.gmind.mymovieproject.R
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*

class MyWalletTopUpActivity : AppCompatActivity() {

    private var status : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        tv_10k.setOnClickListener {
            if (status){
                deselect(tv_10k)
            } else{
                select(tv_10k)
            }
        }

        tv_25k.setOnClickListener {
            if (status){
                deselect(tv_25k)
            } else{
                select(tv_25k)
            }
        }

        tv_50k.setOnClickListener {
            if (status){
                deselect(tv_50k)
            } else{
                select(tv_50k)
            }
        }

        tv_100k.setOnClickListener {
            if (status){
                deselect(tv_100k)
            } else{
                select(tv_100k)
            }
        }

        tv_200k.setOnClickListener {
            if (status){
                deselect(tv_200k)
            } else{
                select(tv_200k)
            }
        }

        tv_500k.setOnClickListener {
            if (status){
                deselect(tv_500k)
            } else{
                select(tv_500k)
            }
        }
    }

    private fun select(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorPink))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status = true

        btn_top_up.visibility = View.VISIBLE
    }
    private fun deselect(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status = false

        btn_top_up.visibility = View.INVISIBLE
    }
}