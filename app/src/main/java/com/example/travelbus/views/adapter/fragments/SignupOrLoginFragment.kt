package com.example.travelbus.views.adapter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.travelbus.R
import kotlinx.android.synthetic.main.fragment_signup_or_login.*


class SignupOrLoginFragment :  Fragment(R.layout.fragment_signup_or_login) {

    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnSignupFrag.setOnClickListener {
            navController.navigate(R.id.action_signupOrLoginFragment_to_signUpFragment)
        }

        btnLoginfrag.setOnClickListener{
            navController.navigate(R.id.action_signupOrLoginFragment_to_loginFragment)
        }
    }

}