package com.teguhrmdhn.movapp.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.teguhrmdhn.movapp.R
import com.teguhrmdhn.movapp.sign.signin.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sName:String
    lateinit var sEmail:String

    private lateinit var mDatabaseReference : DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_signup.setOnClickListener {

            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sName = et_name.text.toString()
            sEmail = et_email.text.toString()

            if(sUsername.equals("")) {
                et_username.error = "please fill in the Username field"
                et_username.requestFocus()
            } else if(sPassword.equals("")) {
                et_password.error = "please fill in the Password field"
                et_password.requestFocus()
            } else if(sName.equals("")) {
                et_name.error = "please fill in the Name field"
                et_name.requestFocus()
            } else if(sEmail.equals("")) {
                et_email.error = "please fill in the Email field"
                et_email.requestFocus()
            } else {
                saveUsername (sUsername, sPassword, sName, sEmail)
            }

        }
    }

    private fun saveUsername(sUsername:String, sPassword:String, sName:String, sEmail:String) {
        val user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sName
        user.email = sEmail

        if(sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null ) {
                    mDatabaseReference.child(sUsername).setValue(data)
                    var goSignUpPhotoScreen = Intent (this@SignUpActivity, SignUpPhotoscreenActivity::class.java).putExtra("name", data.nama)
                    startActivity(goSignUpPhotoScreen)
                } else {
                    Toast.makeText(this@SignUpActivity, "User Exist", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }


}