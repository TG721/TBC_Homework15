package com.example.tbc_homework15.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.example.tbc_homework15.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.welcomeLoginButton.background.alpha = 76
        super.onViewCreated(view, savedInstanceState)
        binding.welcomeRegisterButton.setOnClickListener {
            val action =
                WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
            findNavController(view).navigate(action)
        }
        binding.welcomeLoginButton.setOnClickListener {
            val action2 =
                WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
            findNavController(view).navigate(action2)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}