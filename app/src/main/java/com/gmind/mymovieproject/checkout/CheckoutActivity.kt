package com.gmind.mymovieproject.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.mymovieproject.R
import com.gmind.mymovieproject.home.tiket.CheckoutAdapter
import com.gmind.mymovieproject.home.tiket.TiketActivity
import com.gmind.mymovieproject.model.Checkout
import com.gmind.mymovieproject.model.Film
import com.gmind.mymovieproject.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total : Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter =
            CheckoutAdapter(dataList) {
            }

        if (preferences.getValues("saldo")!!.isNotEmpty()){
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))
            btn_tiket.visibility = View.VISIBLE
            tv_saldo_kurang.visibility = View.INVISIBLE
        } else{
            tv_saldo.setText("Rp0,00")
            btn_tiket.visibility = View.INVISIBLE
            tv_saldo_kurang.visibility = View.VISIBLE
        }

        btn_tiket.setOnClickListener {
            val intent = Intent(this, CheckoutSuccessActivity::class.java)
                .putExtra("data", dataList)
                .putExtra("datas", data)
            startActivity(intent)

            showNotif(data)
        }

        btn_home.setOnClickListener {
            finish()
        }
    }

    private fun showNotif(datas: Film) {
        val NOTIFICATION_CHANNEL_ID = "Notif Channel"
        val contex = this.applicationContext
        var notificationManager = contex.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channelName = "Mov Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val nChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(nChannel)
        }
        val notifIntent = Intent(this, TiketActivity::class.java)
        val bundle =Bundle()
        bundle.putParcelable("data", datas)
        notifIntent.putExtras(bundle)

        val pendingIntent = PendingIntent.getActivity(this, 0, notifIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo_mov)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources, R.drawable.ic_notification
                )
            )
            .setTicker("Notification")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setLights(Color.GREEN, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Pembayaran Sukses")
            .setContentText("Tiket " +datas.judul+ " Berhasil Dibeli")

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(155, builder.build())

    }
}