package com.teguhrmdhn.movapp.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.teguhrmdhn.movapp.home.HomeActivity
import com.teguhrmdhn.movapp.R
import com.teguhrmdhn.movapp.sign.signup.SignUpActivity
import com.teguhrmdhn.movapp.util.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var eDatabase: DatabaseReference
    lateinit var preference: Preferences

    lateinit var iUsername:String
    lateinit var iPassword:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        eDatabase = FirebaseDatabase.getInstance().getReference( "User")
        preference = Preferences(this)

        preference.setValue("onboarding", "1")
        if(preference.getValues("status").equals("1")) {
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btn_login.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if(iUsername.equals("")) {
                et_username.error = "silahkan isi username"
                et_username.requestFocus()
            } else if(iPassword.equals("")) {
                et_password.error = "silahkan isi password"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        btn_signup.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        eDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "username Not Found", Toast.LENGTH_LONG).show()
                } else {
                    if(user.password.equals(iPassword)) {

                        preference.setValue("nama", user.nama.toString())
                        preference.setValue("user", user.username.toString())
                        preference.setValue("url", user.url.toString())
                        preference.setValue("email", user.email.toString())
                        preference.setValue("saldo", user.saldo.toString())
                        preference.setValue("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Wrong Password", Toast.LENGTH_LONG).show()
                    }
                }
            }


        })
    }
}