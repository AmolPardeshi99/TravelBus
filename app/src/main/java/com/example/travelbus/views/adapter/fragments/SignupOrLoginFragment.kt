package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_signup_or_login.*
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.util.Log
import com.example.travelbus.R
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.widget.Toast
import com.example.travelbus.views.adapter.activities.HomeActivity

import com.google.android.gms.common.api.ApiException

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider


class SignupOrLoginFragment : Fragment(R.layout.fragment_signup_or_login) {
    private val SIGN_IN_KEY = 1
    private lateinit var mAuth: FirebaseAuth

    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        mAuth = FirebaseAuth.getInstance()
        btnSignupFrag.setOnClickListener {
            navController.navigate(R.id.action_signupOrLoginFragment_to_signUpFragment)
        }

        btnLoginfrag.setOnClickListener {
            navController.navigate(R.id.action_signupOrLoginFragment_to_loginFragment)
        }

        btnGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {

        // Configure Google Sign In
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1088458200903-ivtaldcrvts9f93mqf1s3q2ruk9l7pmh.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        //val signInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)
        val signInIntent: Intent = googleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, SIGN_IN_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Fetching request code for sign in

        // Fetching request code for sign in
        if (requestCode === SIGN_IN_KEY) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account!!.idToken)
            } catch (e: ApiException) {
                //Toast.makeText(context, e.message + "", Toast.LENGTH_SHORT).show()
                Log.d("SighUpActivity", "onActivityResult: ")
                val intent = Intent(activity?.applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        // Generating credential and token
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("SighUpActivity", "success: ")
                val intent = Intent(activity?.applicationContext, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                //saveLoginState()
            } else {
                Toast.makeText(
                    context,it.exception.toString() + "",Toast.LENGTH_SHORT).show()
                Log.d("SighUpActivity", "error: ")
            }
        }
    }

//    private fun saveLoginState() {
//        val preferences: SharedPreferences.Editor =
//            getSharedPreferences("PREFS", MODE_PRIVATE).edit()
//        preferences.putBoolean("loggedIn", true)
//        preferences.apply()
//        finish()
//    }

}