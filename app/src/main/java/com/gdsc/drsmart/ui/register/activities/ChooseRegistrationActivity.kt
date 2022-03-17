package com.gdsc.drsmart.ui.register.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdsc.drsmart.MainActivity
import com.gdsc.drsmart.R
import com.gdsc.drsmart.tools.storage.AppReferences
import kotlinx.android.synthetic.main.activity_choose_registeration.*

class ChooseRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_registeration)
        initView()
    }

    private fun initView() {

        val loginState = intent.getBooleanExtra("login_state", false)
        if (loginState) {
            AppReferences.setLoginState(this, false)
            AppReferences.setDocLoginState(this, false)
        }
        if (AppReferences.getDocLoginState(this)) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            //TODO("ADD FINISH & CHANGE ACTIVITY")
        }
        if (AppReferences.getLoginState(this)) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            //TODO("ADD FINISH & CHANGE ACTIVITY")

        }
        doctorBtn.setOnClickListener {
            val i = Intent(this, RegistrationHomeActivity::class.java)
            i.putExtra("isDoctor", true)
            startActivity(i)
            //TODO("add finish here ")
        }
        patientBtn.setOnClickListener {
            val i = Intent(this, RegistrationHomeActivity::class.java)
            i.putExtra("isDoctor", false)
            startActivity(i)
            //TODO("add finish here ")
        }
    }
}