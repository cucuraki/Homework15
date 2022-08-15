package com.example.homework15.pagefragments.welcomepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.homework15.databinding.WelcomePageFragmentBinding

class WelcomePage : Fragment() {

    private var binding: WelcomePageFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WelcomePageFragmentBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBtnListeners()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setOnRegisterBtnListener(){
        binding!!.register.setOnClickListener{
            findNavController().navigate(WelcomePageDirections.actionWelcomePageToRegisterFragment())
        }
    }

    private fun setOnLoginBtnListener(){
        binding!!.login.setOnClickListener{
            findNavController().navigate(WelcomePageDirections.actionWelcomePageToLogInFragment2())
        }
    }

    private fun setOnBtnListeners(){
        setOnRegisterBtnListener()
        setOnLoginBtnListener()
    }
}