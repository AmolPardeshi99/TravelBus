package com.example.travelbus.views.adapter.fragments

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_otp.*
import kotlinx.coroutines.*

class OtpFragment : Fragment(R.layout.fragment_otp) {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // code for timer
        setTimer()
        // Code for underline and coloured text
        setClouredText()
    }

    private fun setClouredText() {
        // resend otp
        val resendText = "<u>Resend OTP</u>"
        val resendotp: String = getColoredSpanned(resendText, "#D74E55")
        tvresendOtp.setText(Html.fromHtml(resendotp))

        // privacy policy
        val signingIn = "By signing in, your agree to our "
        val termsCondn = "<u>Terms and Conditions</u>"
        val and = " and "
        val privacyPolicy = "<u>Privacy Policy</u>"
        var term1: String = getColoredSpanned(signingIn, "#BFC0C2")
        var term2: String = getColoredSpanned(termsCondn, "#1133DB")
        var term3: String = getColoredSpanned(and, "#BFC0C2")
        var term4: String = getColoredSpanned(privacyPolicy, "#1133DB")
        tvTermCondtnOtp.setText(Html.fromHtml(term1 + term2 + term3 + term4))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setTimer() {
        view_timer.isCountDown = true
        view_timer.base = SystemClock.elapsedRealtime() + 20000
        view_timer.start()
        CoroutineScope(Dispatchers.Main).launch {
            delay(20000)
            view_timer.stop()
        }
    }

    //function for getting colour text
    private fun getColoredSpanned(text: String, color: String): String {
        return "<font color=$color>$text</font>"
    }
}