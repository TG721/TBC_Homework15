package com.example.tbc_homework15.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import com.example.tbc_homework15.databinding.FragmentWelcomeBinding
import com.example.tbc_homework15.ui.BaseFragment


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

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

}