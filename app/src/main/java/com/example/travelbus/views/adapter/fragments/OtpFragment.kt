package com.example.travelbus.views.adapter.fragments

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.travelbus.R
import com.example.travelbus.models.local.Users
import com.example.travelbus.views.adapter.activities.HomeActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_otp.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class OtpFragment : Fragment(R.layout.fragment_otp) {

    private var phoneNumber: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var options: PhoneAuthOptions
    private var verificationCode: String? = null
    private var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // code for timer
        setClouredText()
        setTimer()
        // Code for underline and coloured text

        auth = Firebase.auth
        getIntentData()
        sendVerificationCode()
        btnConfirmOtp.setOnClickListener {
            var otp=""
                otp += etOtp1.text
                otp += etOtp2.text
                otp += etOtp3.text
                otp += etOtp4.text
                otp += etOtp5.text
                otp += etOtp6.text

            if (otp.equals("")){
                etOtp1.error = "Invalid OTP"
            }else{
                verifyCode(otp)
            }
        }
    }

    private fun getIntentData() {
        phoneNumber = arguments?.get("phonenumber") as String?
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



        btnConfirmOtp.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, HomeActivity::class.java))
        }
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



    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0

            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                val code: String? = p0.smsCode
                if (code != null) {
                    verifyCode(code)
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(
                    activity?.applicationContext,
                    "verification failed - ${p0.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    private fun verifyCode(code: String) {
        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(verificationCode.toString(), code)
        signTheUserByCredentials(credential)
    }

    private fun signTheUserByCredentials(credential: PhoneAuthCredential) {

        val firebaseAuth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener<AuthResult>() {
                if (it.isSuccessful) {
                    Toast.makeText(activity?.applicationContext, "Otp verification Successful", Toast.LENGTH_LONG).show()

                    auth.currentUser?.let { it1 ->
                        addUserDetailsToDatabase(it1.uid)
                    }
                    val intent = Intent(activity?.applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity?.applicationContext, "FAIL", Toast.LENGTH_LONG).show()
                }
            })

    }

    private fun addUserDetailsToDatabase(uid: String) {
        val db = Firebase.firestore
        val user = Users(phoneNumber.toString(), uid)
        db.collection("users").document(uid).set(user)
    }

    private fun sendVerificationCode() {
        if (phoneNumber != null) {
            options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91$phoneNumber")       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(context as Activity)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }
}