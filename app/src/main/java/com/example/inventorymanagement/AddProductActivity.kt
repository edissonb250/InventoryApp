package com.example.inventorymanagement

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dynatrace.android.agent.Dynatrace
import kotlinx.android.synthetic.main.add_product.*
import org.w3c.dom.Text

class AddProductActivity: AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)

        val localpreferences = getSharedPreferences("Shr_preferences", Context.MODE_PRIVATE)


        buttonSubmit.setOnClickListener {
            val productname = textProductName.text.toString().trim()
            val productowner = textOwner.text.toString().trim()
            val productprice = Integer.parseInt(textPrice.text.toString().trim())

            val editor= localpreferences.edit()

            Dynatrace.identifyUser("john.doe@example.com")

            editor.putString("ProductName",productname)
            editor.putString("ProductOwner",productowner)
            editor.putInt("ProductPrice", productprice)

            editor.apply()
        }

        buttonShowResults.setOnClickListener {
            val productname = localpreferences.getString("ProductName", "")
            val productowner = localpreferences.getString("ProductOwner", "")
            val productprice = localpreferences.getInt("ProductPrice", 0)

            textResults.text= "Product Name: $productname\nOwner: $productowner\nPrice: $productprice"
        }
    }
}