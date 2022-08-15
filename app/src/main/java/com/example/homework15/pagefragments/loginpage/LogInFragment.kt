package com.example.homework15.pagefragments.loginpage

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
import com.example.homework15.databinding.LogInFragmentBinding
import com.example.homework15.responsestate.ResponseState
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {

    private var binding: LogInFragmentBinding? = null
    private val model: LogInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LogInFragmentBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collect()
        loginOnClickListener()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loginOnClickListener() {
        binding!!.login.setOnClickListener {
            model.login(binding!!.userName.text.toString(), binding!!.password.text.toString())
        }
    }

    private fun collect() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.processState.collect {
                    when (it) {
                        is ResponseState.Success -> {
                            if (it.body.token != "") {
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