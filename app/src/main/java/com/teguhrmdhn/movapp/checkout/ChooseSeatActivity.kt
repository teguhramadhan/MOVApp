package com.teguhrmdhn.movapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.teguhrmdhn.movapp.R
import com.teguhrmdhn.movapp.model.Checkout
import com.teguhrmdhn.movapp.model.Film
import kotlinx.android.synthetic.main.activity_choose_seat.*

class ChooseSeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seat)

        var statusA1:Boolean = false
        var statusA2:Boolean = false
        var statusA3:Boolean = false
        var statusA4:Boolean = false
        var total:Int = 0

        var dataList = ArrayList<Checkout>()

        val data = intent.getParcelableExtra<Film>("data")
        tv_kursi.text = data!!.judul

        iv_back.setOnClickListener {
            finish()
        }

        a1.setOnClickListener {
            if (statusA1) {
                a1.setImageResource(R.drawable.shape_rectangle_outline_secondary)
                statusA1 = false
                total -= 1
                beliTiket(total)
            } else {
                a1.setImageResource(R.drawable.shape_rectangle_primary)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A1", 70000.toString())
                dataList.add(data)
            }
        }


        a2.setOnClickListener {
            if (statusA2) {
                a2.setImageResource(R.drawable.shape_rectangle_outline_secondary)
                statusA2 = false
                total -= 1
                beliTiket(total)
            } else {
                a2.setImageResource(R.drawable.shape_rectangle_primary)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A2", 70000.toString())
                dataList.add(data)
            }
        }

        btn_buy.setOnClickListener {
            var intent = Intent(this, CheckoutActivity::class.java).putExtra("data", dataList)
            startActivity(intent)
        }
    }


    private fun beliTiket(total: Int) {
        if(total == 0) {
            btn_buy.setText("Buy Ticket")
            btn_buy.visibility = View.INVISIBLE
        } else {
            btn_buy.setText("Buy Ticket ("+total+")")
            btn_buy.visibility = View.VISIBLE
        }
    }
}