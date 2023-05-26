package com.example.emailcheck

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var email: TextInputLayout
    private lateinit var pass:TextInputLayout
    private lateinit var sec_pass:TextInputLayout
    private lateinit var emailText:AutoCompleteTextView
    private lateinit var loginButton:Button
    private var domains= arrayOf("gmail.com","mail.ru","yandex.ru","rambler.ru","yahoo.com","outlook.com", "gmail.uk.com", "hotmail.com","bk.ru")

    private var possibleEmails= arrayOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email=findViewById(R.id.LinearLogin)
        pass=findViewById(R.id.LinearPassword)
        sec_pass=findViewById(R.id.LinearPassword_second)
        emailText=findViewById(R.id.email_EditText)
        loginButton=findViewById(R.id.button_signin)

        pass.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if(pass.editText?.text.toString().length <6){
                    pass.error="Пароль слишком короткий"
                }
                else if(pass.editText?.text.toString().length >14){
                    pass.error="Пароль слишком длинный"
                }else pass.error=null
            }
            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {

            }
        })
        sec_pass.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if(sec_pass.editText?.text.toString()!=pass.editText?.text.toString()){
                    sec_pass.error="Пароли не совпадают"
                }else sec_pass.error=null
            }

            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {

            }
        })

        email.editText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                var domain=""
                if(email.editText?.text.toString().contains("@")){
                    val curEmail=email.editText?.text.toString().substring(0,email.editText?.text.toString().indexOf("@"))
                    possibleEmails= arrayOf()
                            for (i in domains){
                               possibleEmails+=curEmail+"@"+i
                    }
                    var adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, possibleEmails)
                    emailText.setAdapter(adapter)
                    domain=email.editText?.text.toString().substring(email.editText?.text.toString().indexOf("@")+1)
                    if(domain in domains)
                        email.error=null
                    else
                        email.error="Некорректный адрес электронной почты"

                }
                if(email.editText?.text.toString()==""){
                    email.error="Введите адрес электронной почты"
                }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.editText?.text.toString()).matches()){
                    email.error="Некорректный адрес электронной почты"
                }else email.error=null

                if(domain in domains)
                    email.error=null
                else
                    email.error="Некорректный адрес электронной почты"
            }

            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {

            }
        })

        loginButton.setOnClickListener {
            if(email.error==null && pass.error==null && sec_pass.error==null){
                Toast.makeText(this,"Регистрация успешно завершена",Toast.LENGTH_LONG).show()
            }else  Toast.makeText(this,"Заполните все поля корректно",Toast.LENGTH_LONG).show()
        }


    }
}