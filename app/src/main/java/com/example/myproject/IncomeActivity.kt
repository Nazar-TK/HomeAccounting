package com.example.myproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_income.*

class IncomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
        button.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                textSum.text =
                    (textSum.text.toString().toInt() + editText.text.toString().toInt()).toString()
                Toast.makeText(this, "Суму введено", Toast.LENGTH_LONG).show()

                val data: Int
                data = editText.text.toString().toInt()
                editText.getText().clear()
                val ma = MechaChmo()
                ma.vel += data;
            } else
                Toast.makeText(this, "Спочатку введіть суму", Toast.LENGTH_LONG).show()
        }

    }
}