package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        // Code for underline and coloured text
        getColouredText()
        //
        btnLogIn.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_otpFragment)
        }
    }

    private fun getColouredText() {
        val signingIn = "By signing in, your agree to our "
        val termsCondn = "<u>Terms and Conditions</u>"
        val and = " and "
        val privacyPolicy = "<u>Privacy Policy</u>"
        var term1: String = getColoredSpanned(signingIn, "#BFC0C2")
        var term2: String = getColoredSpanned(termsCondn, "#1133DB")
        var term3: String = getColoredSpanned(and, "#BFC0C2")
        var term4: String = getColoredSpanned(privacyPolicy, "#1133DB")
        tvTermCondtn.setText(Html.fromHtml(term1 + term2 + term3 + term4))
    }

    //function for getting colour text
    private fun getColoredSpanned(text: String, color: String): String {
        return "<font color=$color>$text</font>"
    }

}