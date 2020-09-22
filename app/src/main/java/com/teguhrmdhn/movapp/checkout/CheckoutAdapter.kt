package com.teguhrmdhn.movapp.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teguhrmdhn.movapp.R
import com.teguhrmdhn.movapp.model.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.LeagueViewHolder>() {

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)

        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, ContextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_kursi)
        private val tvHarga: TextView = view.findViewById(R.id.tv_harga)

        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context : Context, position : Int) {

            val localID = Locale("id", "ID")
            var formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")) {
                tvTitle.setText(data.kursi)
                tvTitle.setCompoundDrawables(null, null, null, null)
            } else {
                tvTitle.setText("Seat No."+data.kursi)
            }



            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

}
