package com.gmind.mymovieproject.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmind.mymovieproject.R
import com.gmind.mymovieproject.model.Checkout
import java.text.NumberFormat
import java.util.*

class TiketAdapter(private var data : List<Checkout>,
                   private val listener : (Checkout) -> Unit)
    : RecyclerView.Adapter<TiketAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TiketAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout_white, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TiketAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvKursi : TextView = view.findViewById(R.id.tv_kursi)

        fun bindItem(data : Checkout, listener: (Checkout) -> Unit, context: Context){

            tvKursi.setText("Kursi No. "+data.kursi)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

}
