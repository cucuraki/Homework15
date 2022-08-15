package com.example.homework15.pagefragments.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework15.databinding.HomePageFragmentBinding


class HomePageFragment : Fragment() {

    private var binding: HomePageFragmentBinding? = null
    private val args: HomePageFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomePageFragmentBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.root.text = args.token
        binding!!.root.setOnClickListener{
            findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToWelcomePage())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}