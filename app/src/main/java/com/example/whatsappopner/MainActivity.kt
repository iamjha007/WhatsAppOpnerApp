package com.example.whatsappopner

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number:String = "9899506447"

        if(intent.action == Intent.ACTION_PROCESS_TEXT){
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

            if(number.isDigitsOnly()){
                startWhatsapp(number)
            }else{
                Toast.makeText(this,"Please Check the Number", Toast.LENGTH_SHORT).show()
            }
        }

        btn_chat.setOnClickListener {
            number = et_number.text.toString()

            if(number.isEmpty()){
                startWhatsapp("9899506447")
            }else if(number.isDigitsOnly()){
                startWhatsapp(number)
            }else{
                Toast.makeText(this,"Please Check the Number", Toast.LENGTH_SHORT).show()
            }
        }

//        if(number.isDigitsOnly()){
//            startWhatsapp(number)
//        }else{
//            Toast.makeText(this,"Please Check the Number", Toast.LENGTH_SHORT).show()
//        }

//            if(number!!.isDigitsOnly()) {
//                  startWhatsapp(number)
//            }else{
//                Toast.makeText(this,"Invalid NUmber",Toast.LENGTH_SHORT).show()
//            }
    }


    private fun startWhatsapp(num: String) {
      var number=num
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        if(number[0]=='+'){
            number=number.substring(1)
        }else if(number.length==10){
            number="91"+number
        }
        intent.data= Uri.parse("https://wa.me/$number")
        if(packageManager.resolveActivity(intent,0)!=null){
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"whatsapp not installed",Toast.LENGTH_SHORT).show()
        }
        finish()


    }
}
