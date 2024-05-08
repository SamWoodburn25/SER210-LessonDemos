package edu.quinnipiac.ser210.lesson3

/*
 * Lesson 3- SER210- navigation practice
 * Samantha Woodburn
 * 1/30/24
 * confirmation fragment- confirm screen to show what you sent
 */

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ConfirmationFragment : Fragment() {

    lateinit var recipient: String
    var money :Money? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle == null){
            Log.e("ConfirmationFragment", "ConfirmationFragment did not receive recipient and money information")
            return
        }
        recipient = ConfirmationFragmentArgs.fromBundle(bundle).recipient.toString()
        money = ConfirmationFragmentArgs.fromBundle(bundle).money
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount  = money!!.amount
        val confirmMsg = "You have sent $amount to $recipient"
        view.findViewById<TextView>(R.id.confirmation_message).text = confirmMsg
    }
}