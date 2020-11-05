package com.app.mytask.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mytask.MainActivity
import com.app.mytask.R
import com.app.mytask.ui.AsteriskPasswordTransformationMethod
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern


const val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editText2.transformationMethod = AsteriskPasswordTransformationMethod()
        editText3.transformationMethod = AsteriskPasswordTransformationMethod()
    }

    fun onClickLogin(view : View){
         finish()
    }

    fun onClickSignup(view : View){
        if (validateCredentials()){
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

            editText3.text!!.isEmpty() ->
                Toast.makeText(getApplicationContext(), "Please enter Confirm Password", Toast.LENGTH_SHORT).show();


            editText2.text!! != editText3.text!! -> {
                Toast.makeText(getApplicationContext(), "Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();

            }
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


}