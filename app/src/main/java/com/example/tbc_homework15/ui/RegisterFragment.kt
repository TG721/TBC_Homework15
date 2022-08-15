package com.example.tbc_homework15.ui

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tbc_homework15.R
import com.example.tbc_homework15.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment()
//: BaseFragment
{
//    override fun getFragmentView()=R.layout.fragment_register
//    override fun GetViewModel(RegisterViewModel): Class<>{
//
//    }
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            registerPasswordET.background.alpha = 76
            registerEmailET.background.alpha = 76
            registerUsernameET.background.alpha = 76
            var eyeClicked=0
            registerPassword.setEndIconOnClickListener {
                if (eyeClicked%2==0)
                {
                    registerPassword.editText!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    registerPassword.endIconDrawable = ContextCompat.getDrawable(requireContext(),
                        R.drawable.layered_eye_no
                    )
                } else {
                    registerPassword.editText!!.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                    registerPassword.endIconDrawable = ContextCompat.getDrawable(requireContext(),
                        R.drawable.layered_eye
                    )
                }
                eyeClicked++

            }


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}