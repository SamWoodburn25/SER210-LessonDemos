package edu.quinnipiac.ser210.lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var input1: EditText? = null
    var input2: EditText? = null
    var res: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input1 = findViewById<EditText>(R.id.input1)
        input2 = findViewById<EditText>(R.id.input2)
        res = findViewById<EditText>(R.id.result)

        findViewById<Button>(R.id.addition).setOnClickListener {
            performOp('+')
        }
        findViewById<Spinner>(R.id.spinner).onItemSelectedListener = this

    }

    private fun performOp(op: Char){
        //read from input 1 and 2
        val num1: Double = input1?.getText().toString().toDouble()
        val num2: Double = input2?.getText().toString().toDouble()

        Log.v("Debug", "num1 $num1 num2 $num2" )

        var result = 0.0
        when(op) {
            '+' -> result = num1 + num2
            '-' -> result = num1 - num2
            '*' -> result = num1 * num2
            '/' -> result = num1 / num2
        }
        //update the value of result
        res?.setText(java.lang.Double.toString(result))

    }

    fun subtraction(view:View?){
        this.performOp('-')
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val op = parent?.getItemAtPosition(position).toString()
        if(!op.equals("none"))
            this.performOp(op[0])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

