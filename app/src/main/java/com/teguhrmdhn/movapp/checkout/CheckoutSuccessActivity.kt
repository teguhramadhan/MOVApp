package com.teguhrmdhn.movapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teguhrmdhn.movapp.R
import com.teguhrmdhn.movapp.home.HomeActivity
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }
    }
}

