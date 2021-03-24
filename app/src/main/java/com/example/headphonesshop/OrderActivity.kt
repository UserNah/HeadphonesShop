package com.example.headphonesshop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val intent = intent
        val userName: String? = intent.getStringExtra("userName")
        val itemName: String? = intent.getStringExtra("itemName")
        val quantity: Int? = intent.getIntExtra("quantity", 0)
        val orderPrice: Double? = intent.getDoubleExtra("orderPrice", 0.0)

        val orderTextView:TextView = findViewById(R.id.orderTextView)
        orderTextView.text= "User name: " +userName +" \n" + "Item name: " + itemName +" \n" + "Quantity: " + quantity + " \n" +"Order price: " + orderPrice
    }

    fun SendToMail(view: View) {
        val addresses = "test@test"
        val subject = "subject"
        val body = "body"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}