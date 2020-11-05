package com.app.mytask.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mytask.MainActivity
import com.app.mytask.R
import com.app.mytask.ui.AsteriskPasswordTransformationMethod
import com.app.mytask.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editText
import kotlinx.android.synthetic.main.activity_login.editText2
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern


const val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"


class LoginActivity : AppCompatActivity() {


    val userName = ("")

    var name: EditText? = null
    var email:EditText? = null
    var phone:EditText? = null
    var password:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editText2.transformationMethod = AsteriskPasswordTransformationMethod()
    }

    fun onClickSignup(view : View){
            startActivity(Intent(this , RegisterActivity::class.java))
    }

    fun onClickLogin(view : View){
        if (validateCredentials()) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    private fun validateCredentials(): Boolean {
        when {
            editText.text!!.isEmpty() ->
                Toast.makeText(getApplicationContext(), "Please enter your email address", Toast.LENGTH_SHORT).show();
            !validateEmail(editText.text.toString()) -> {
                Toast.makeText(getApplicationContext(), "Please enter your Valid email address", Toast.LENGTH_SHORT).show();

            }
            editText2.text!!.isEmpty() ->
                Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();

            else -> return true
        }
        return false
    }

    fun validateEmail(email: String): Boolean {
        val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
}