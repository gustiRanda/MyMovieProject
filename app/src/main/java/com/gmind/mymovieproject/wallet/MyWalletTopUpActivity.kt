package com.gmind.mymovieproject.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.gmind.mymovieproject.R
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*

class MyWalletTopUpActivity : AppCompatActivity() {

    private var status10k : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        tv_10k.setOnClickListener {
            if (status10k){
                deselectMoney(tv_10k)
            } else{
                selectMoney(tv_10k)
            }
        }

        tv_25k.setOnClickListener {
            if (status10k){
                deselectMoney(tv_25k)
            } else{
                selectMoney(tv_25k)
            }
        }
    }

    private fun selectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorPink))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status10k = true

        btn_top_up.visibility = View.VISIBLE
    }
    private fun deselectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status10k = false

        btn_top_up.visibility = View.INVISIBLE
    }
}