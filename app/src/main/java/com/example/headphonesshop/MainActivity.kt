package com.example.headphonesshop

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.util.ArrayList

class MainActivity : AppCompatActivity()
{
    val map = mapOf("WH-1000XM4" to 278.0 , "WH-1000XM3" to 179.97,"WH-1000XM2" to  348.0)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner:Spinner = findViewById(R.id.spinner_sony)
        var spinnerArrayList:List<String> = ArrayList()
        spinnerArrayList = listOf(
            "WH-1000XM4",
            "WH-1000XM3",
            "WH-1000XM2"
        )
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, spinnerArrayList)


        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?)
            {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                val valueConverted:Int = Integer.parseInt(findViewById<TextView>(R.id.quantityTextView).text.toString())
                val priceTextView:TextView = findViewById(R.id.priceTextView)
                val itemName:String = spinner.selectedItem.toString()
                var price = 0.0
                val priceByMap:Double? = this@MainActivity.map[itemName]
                if(priceByMap !=null)
                    price = priceByMap
                priceTextView.text = """${price * valueConverted}"""

                val itemImageView:ImageView = findViewById(R.id.imageView)
                when (itemName)
                {
                    "WH-1000XM4" -> itemImageView.setImageResource(R.drawable.wh_1000xm4)
                    "WH-1000XM3" -> itemImageView.setImageResource(R.drawable.wh1000xm3)
                    "WH-1000XM2" -> itemImageView.setImageResource(R.drawable.wh1000xm2)
                    else ->
                    {
                        itemImageView.setImageResource(R.drawable.wh_1000xm4)
                    }
                }
            }
        }
    }

    fun addOne(view: View)
    {
        val value:TextView = findViewById(R.id.quantityTextView)
        var valueConverted:Int = Integer.parseInt(value.text.toString())

        valueConverted++
        value.text = """$valueConverted"""

        valueConverted.setPrice()


    }
    fun minOne(view: View)
    {
        val value:TextView = findViewById(R.id.quantityTextView)
        var valueConverted:Int = Integer.parseInt(value.text.toString())
        if(valueConverted==0)
        {
            value.isEnabled = false
        }
        else{
            valueConverted--
            value.text = """$valueConverted"""
        }

        valueConverted.setPrice()
    }
    private fun Int.setPrice()
    {
        val priceTextView:TextView = findViewById(R.id.priceTextView)
        val spinner: Spinner = findViewById(R.id.spinner_sony)
        val itemName:String = spinner.selectedItem.toString()
        var price = 0.0
        val priceByMap:Double? = map[itemName]
        if(priceByMap !=null) {
            price = priceByMap
        }
        priceTextView.text = """${price * this}"""
    }

    fun AddToCart(view: View)
    {

        val nameEditText = findViewById<EditText>(R.id.editTextTextPersonName).text
        val order = Order()
        order.userName = nameEditText.toString()


        val spinner: Spinner = findViewById(R.id.spinner_sony)
        val itemName:String = spinner.selectedItem.toString()
        order.itemName = itemName


        val value:TextView = findViewById(R.id.quantityTextView)
        val valueConverted:Int = Integer.parseInt(value.text.toString())
        order.quantity = valueConverted


        val priceTextView:TextView = findViewById(R.id.priceTextView)
        order.orderPrice = priceTextView.text.toString().toDouble()

        Log.d("testObject1", order.userName + " " + order.itemName + " " + order.quantity + " " + order.orderPrice + " ")
        val orderIntent = Intent(this, OrderActivity::class.java)
        orderIntent.putExtra("userName", order.userName)
        orderIntent.putExtra("itemName", order.itemName)
        orderIntent.putExtra("quantity", order.quantity)
        orderIntent.putExtra("orderPrice", order.orderPrice)
        startActivity(orderIntent)
    }
}