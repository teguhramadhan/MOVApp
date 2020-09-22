package com.teguhrmdhn.movapp.home.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.teguhrmdhn.movapp.R
import com.teguhrmdhn.movapp.model.Checkout
import com.teguhrmdhn.movapp.model.Film
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_ticket.*
import kotlinx.android.synthetic.main.activity_ticket.tv_genre
import kotlinx.android.synthetic.main.activity_ticket.tv_rate

class TicketActivity : AppCompatActivity() {

    private var  dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_tittle.text = data!!.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster_ava)

        rv_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        rv_checkout.adapter = TicketAdapter(dataList) {

        }

    }
}