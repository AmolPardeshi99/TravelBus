package com.example.travelbus.views.adapter.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import com.example.travelbus.views.adapter.activities.HomeActivity
import kotlinx.android.synthetic.main.fragment_otp.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClouredText()
        navController = Navigation.findNavController(view)


    }


    private fun setClouredText() {
        // privacy policy
        val signingIn = "By signing in, your agree to our "
        val termsCondn = "<u>Terms and Conditions</u>"
        val and = " and "
        val privacyPolicy = "<u>Privacy Policy</u>"
        var term1: String = getColoredSpanned(signingIn, "#BFC0C2")
        var term2: String = getColoredSpanned(termsCondn, "#1133DB")
        var term3: String = getColoredSpanned(and, "#BFC0C2")
        var term4: String = getColoredSpanned(privacyPolicy, "#1133DB")

        tvTermCondtn123.setText(Html.fromHtml(term1 + term2 + term3 + term4))

        btnSignUp.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("phonenumber",etMobileNo.text.toString())
            navController.navigate(R.id.action_signUpFragment_to_otpFragment,bundle)
        }

    }


    //function for getting colour text
    private fun getColoredSpanned(text: String, color: String): String {
        return "<font color=$color>$text</font>"
    }

}