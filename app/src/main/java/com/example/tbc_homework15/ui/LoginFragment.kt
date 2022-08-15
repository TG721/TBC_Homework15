package com.example.tbc_homework15.ui

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tbc_homework15.R
import com.example.tbc_homework15.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loginEmailET.background.alpha = 76
            loginPasswordET.background.alpha = 76
            var loginEyeClicked=0
            loginPassword.setEndIconOnClickListener {
                if (loginEyeClicked%2==0)
                {
                    loginPassword.editText!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    loginPassword.endIconDrawable = ContextCompat.getDrawable(requireContext(),
                        R.drawable.layered_eye_no
                    )
                } else {
                    loginPassword.editText!!.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                    loginPassword.endIconDrawable = ContextCompat.getDrawable(requireContext(),
                        R.drawable.layered_eye
                    )
                }
                loginEyeClicked++

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}