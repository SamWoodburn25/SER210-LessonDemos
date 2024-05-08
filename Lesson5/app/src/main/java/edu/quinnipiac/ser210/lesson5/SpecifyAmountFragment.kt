package edu.quinnipiac.ser210.lesson5

/*
 * Lesson 3- SER210- navigation practice
 * Samantha Woodburn
 * 1/30/24
 * specify amount fragment- allows user to input an integer amount to send
 */

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import java.math.BigDecimal

class SpecifyAmountFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle == null){
            Log.e("SpecifyingAmountFragment", "SpecifyingAmountFragment did not receive recipient information")
            return
        }
        recipient = SpecifyAmountFragmentArgs.fromBundle(bundle).recipient.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        val message = "Sending money to $recipient"
        view.findViewById<TextView>(R.id.recipient).text = message
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> {
                val input_amount: EditText = requireView().findViewById(R.id.input_amount)
                if(!TextUtils.isEmpty(input_amount.text.toString())){
                    val amount = Money(BigDecimal(input_amount.text.toString()))
                    val action = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment()
                    action.setRecipient(recipient)
                    action.setMoney(amount)
                    v.findNavController().navigate(action)
                } else {
                    Toast.makeText(activity, "enter name", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}