package com.example.homework15.pagefragments.registerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework15.databinding.RegisterFragmentBinding
import com.example.homework15.pagefragments.loginpage.LogInFragmentDirections
import com.example.homework15.responsestate.ResponseState
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var binding: RegisterFragmentBinding? = null
    private val model: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collect()
        registerOnClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun registerOnClickListener() {
        binding!!.register.setOnClickListener {
            model.register(binding!!.email.text.toString(), binding!!.password.text.toString())
        }
    }

    private fun collect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.processState.collect {
                    when (it) {
                        is ResponseState.Success -> {
                            if (it.body.token != "" && it.body.token != null) {
                                findNavController().navigate(
                                    LogInFragmentDirections.actionLogInFragment2ToHomePageFragment(
                                        it.body.token
                                    )
                                )
                            }
                        }
                        is ResponseState.Error -> {
                            Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                        is ResponseState.Load -> {
                            //do something
                        }
                    }
                }
            }
        }
    }
}