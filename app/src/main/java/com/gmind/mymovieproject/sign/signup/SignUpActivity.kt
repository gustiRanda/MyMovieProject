package com.gmind.mymovieproject.sign.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmind.mymovieproject.R
import com.gmind.mymovieproject.sign.signin.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername : String
    lateinit var sPassword : String
    lateinit var sNama : String
    lateinit var sEmail : String

    private  lateinit var mFirebaseDatabase : DatabaseReference
    private  lateinit var mFirebaseInstance : FirebaseDatabase
    private  lateinit var mDatabase : DatabaseReference

    private lateinit var preferences : com.gmind.mymovieproject.utils.Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = com.gmind.mymovieproject.utils.Preferences(this)

        btn_buatAkun.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error = "Silahkan Isi Username Anda"
                et_username.requestFocus()
            } else if (sPassword.equals("")){
                et_password.error = "Silahkan Isi Password Anda"
                et_password.requestFocus()
            }else if (sNama.equals("")){
                et_nama.error = "Silahkan Isi Nama Anda"
                et_nama.requestFocus()
            }else if (sEmail.equals("")){
                et_email.error = "Silahkan Isi Email Anda"
                et_email.requestFocus()
            } else {
                val usernameStatus = sUsername.indexOf(".")
                if (usernameStatus >= 0){
                    et_username.error = "Username Tidak Bisa Menggunakan .(Titik)"
                    et_username.requestFocus()
                } else{
                    saveUser (sUsername, sPassword, sNama, sEmail)
                }
                
            }

        }
    }

    private fun saveUser(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        val user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername !=null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mFirebaseDatabase.child(iUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", "")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("data", data)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUpActivity, "User Sudah Digunakan", Toast.LENGTH_LONG).show()
                }
           }

        })
    }
}
