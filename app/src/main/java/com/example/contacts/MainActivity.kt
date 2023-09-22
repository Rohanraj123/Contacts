package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.DATABASE.DBHelper
import com.example.contacts.DATABASE.Model
import com.example.contacts.RecyclerView.CustomAdapter

class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var number: EditText
    lateinit var btn_ADD: Button
    lateinit var btn_DELETE: Button
    lateinit var btn_SHOW: Button


    var contactArrayList: ArrayList<Model> = ArrayList()
    lateinit var dbHandler: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        name = findViewById(R.id.et_name)
        number = findViewById(R.id.et_phoneNumber)
        btn_ADD = findViewById(R.id.btn_add)
        btn_DELETE = findViewById(R.id.btn_delete)
        btn_SHOW = findViewById(R.id.btn_show)


        dbHandler = DBHelper(this)

        btn_ADD.setOnClickListener {
            val txt_name = name.text.toString()
            val txt_number = number.text.toString()

            if (TextUtils.isEmpty(txt_number) || TextUtils.isEmpty(txt_name)) {
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_LONG).show()
            } else {
                addContacts(txt_name, txt_number)
                name.text.clear()
                number.text.clear()
                Toast.makeText(this, "Contact has been added", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun addContacts(name: String, number: String) {
        dbHandler.addNewContact(name, number)
    }

}
