package com.example.emailcheck

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var email: TextInputLayout
    private lateinit var pass:TextInputLayout
    private lateinit var sec_pass:TextInputLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.LinearLogin)
        pass=findViewById(R.id.LinearPassword)
        sec_pass=findViewById(R.id.LinearPassword_second)


        pass.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if(pass.editText?.text.toString().length <6){
                    pass.editText?.error="пароль слишком короткий"
                }else pass.editText?.error=null
            }
            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {

            }
        })
        sec_pass.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if(sec_pass.editText?.text.toString()!=pass.editText?.text.toString()){
                    sec_pass.editText?.error="Пароли не совпадают"
                }else sec_pass.editText?.error=null
            }

            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {

            }
        })
    }
}