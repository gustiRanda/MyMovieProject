package com.gmind.mymovieproject.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.mymovieproject.R
import com.gmind.mymovieproject.wallet.adapter.WalletAdapter
import com.gmind.mymovieproject.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        dataList.add(
            Wallet(
                "Avenger",
                "12 Nov",
                77000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "12 Nov",
                177000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Movv",
                "12 Nov",
                77000.0,
                "0"
            )
        )

        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }
    }
}