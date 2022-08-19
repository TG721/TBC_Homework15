package com.example.tbc_homework15.ui.welcome

import androidx.navigation.Navigation.findNavController
import com.example.tbc_homework15.databinding.FragmentWelcomeBinding
import com.example.tbc_homework15.ui.BaseFragment


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

    override fun setup() {
        binding.welcomeRegisterButton.setOnClickListener {
            val action =
                WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
            findNavController(requireView()).navigate(action)
        }
        binding.welcomeLoginButton.setOnClickListener {
            val action2 =
                WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
            findNavController(requireView()).navigate(action2)
        }
    }

}