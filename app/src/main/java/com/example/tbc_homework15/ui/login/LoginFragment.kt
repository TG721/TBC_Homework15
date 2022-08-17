package com.example.tbc_homework15.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.tbc_homework15.R
import com.example.tbc_homework15.databinding.FragmentLoginBinding
import com.example.tbc_homework15.network.repository.Repository
import com.example.tbc_homework15.ui.BaseFragment
import com.example.tbc_homework15.ui.register.checkEmpty
import com.example.tbc_homework15.ui.register.isValidEmail
import com.example.tbc_homework15.utils.ResponseState
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    private lateinit var viewModel: LoginViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        binding.apply {
            loginEmailET.background.alpha = 76
            loginPasswordET.background.alpha = 76
            var loginEyeClicked = 0
            loginPassword.setEndIconOnClickListener {
                if (loginEyeClicked % 2 == 0) {
                    loginPassword.editText!!.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    loginPassword.endIconDrawable = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.layered_eye_no
                    )
                } else {
                    loginPassword.editText!!.inputType =
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    loginPassword.endIconDrawable = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.layered_eye
                    )
                }
                loginEyeClicked++

            }
            loginLoginButton.setOnClickListener {
                when {
                    checkEmpty(loginEmail) || checkEmpty(loginPassword) -> {
                    }
                    !isValidEmail(loginEmail) -> {}
                    else -> {
                        viewModel.logIn(
                            email = loginEmailET.text.toString(),
                            password = loginPasswordET.text.toString()
                        )
                        observers()
                    }
                }
            }
        }
    }

    private fun observers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginResponseState.collect {
                    when (it) {
                        is ResponseState.Loading -> {
                            showProgressBar()
                        }
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "${it.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            hideProgressBar()
                        }
                        //if login was successful
                        is ResponseState.Success -> {
                            val actionToLoggedIn = LoginFragmentDirections.actionLoginFragmentToLoggedinFragment()
                            Navigation.findNavController(requireView()).navigate(actionToLoggedIn)
                            hideProgressBar()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.loginProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.loginProgressBar.visibility = View.INVISIBLE
    }


}