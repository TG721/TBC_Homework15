package com.example.tbc_homework15.ui.register

//import android.content.res.ColorStateList
//import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_homework15.R
import com.example.tbc_homework15.databinding.FragmentRegisterBinding
import com.example.tbc_homework15.network.repository.Repository
import com.example.tbc_homework15.ui.BaseFragment
import com.example.tbc_homework15.utils.ResponseState
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


fun checkEmpty(Etext: TextInputLayout): Boolean {
    return if (Etext.editText?.text.toString().trim().isEmpty()) {
        Etext.helperText = "Required"
        true
    } else {
        Etext.helperText = ""
        false
    }
}

fun isValidEmail(Etext: TextInputLayout): Boolean {
    val email = Etext.editText?.text.toString().trim()
    return if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        Etext.helperText = "invalid email"
        false
    } else {
        Etext.helperText = ""
        true
    }
}

fun isValidUsername(Etext: TextInputLayout): Boolean {
    val username = Etext.editText?.text.toString().trim()
    return if (username.length < 10) {
        Etext.helperText = "username should be at least 10 characters"
        false
    } else {
        Etext.helperText = ""
        true
    }

}

fun notGoodPass(Epassword: TextInputLayout): Boolean {
    val password = Epassword.editText?.text.toString()
    when {
        password.length <= 8 -> {
            Epassword.helperText = "Password should be more than 8 characters"
            return true
        }
        password.contains(Regex("^[0-9]+[^a-zA-z]*$")) -> {
            Epassword.helperText = "Password should contain at least one alphabet character"
            return true
        }
        password.contains(Regex("^[a-zA-Z]+[^0-9]*$")) -> {
            Epassword.helperText = "Password should contain at least one numeric character"
            return true
        }
        else -> {
            Epassword.helperText = ""
            return false
        }
    }
}

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private lateinit var viewModel: RegisterViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
        binding.apply {


            registerPasswordET.background.alpha = 76
            registerEmailET.background.alpha = 76
            registerUsernameET.background.alpha = 76

        }
        listeners()
        observers()


    }

    private fun listeners(){
        binding.apply {
        var eyeClicked = 0
        registerPassword.setEndIconOnClickListener {
            if (eyeClicked % 2 == 0) {
                registerPassword.editText!!.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                registerPassword.endIconDrawable = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layered_eye_no
                )
            } else {
                registerPassword.editText!!.inputType =
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                registerPassword.endIconDrawable = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.layered_eye
                )
            }
            eyeClicked++

        }
        registerRegisterButton.setOnClickListener {
            when {
                checkEmpty(registerEmail) || checkEmpty(registerUsername) || checkEmpty(
                    registerPassword
                ) -> {
                }
                notGoodPass(registerPassword) -> {}
                !isValidEmail(registerEmail) -> {}
                !isValidUsername(registerUsername) -> {}
                else -> {
                    viewModel.register(
                        email = registerEmailET.text.toString(),
                        password = registerPasswordET.text.toString()
                    )

                }
            }
        }
        }
    }

    private fun observers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerResponseState.collect{
                    when(it){
                        is ResponseState.Loading -> {
                            showProgressBar()
                        }
                        is ResponseState.Error -> {
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                            hideProgressBar()
                        }
                        is ResponseState.Success -> {
                            Toast.makeText(requireContext(), "registered successfully", Toast.LENGTH_SHORT).show()
                            hideProgressBar()
                        }
                        else -> {}
                    }
                }
            }
        }
    }


    private fun showProgressBar() {
        binding.registerProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.registerProgressBar.visibility = View.GONE
    }

}

//planned to use it to change helper text color but no need anymore
// method to generate color state list programmatically
//fun generateColorStateList(
//    enabledColor: Int = Color.parseColor("#fc0303"), // Red
//    disabledColor: Int = Color.parseColor("#A2A2D0"), // Blue bell
//    checkedColor: Int = Color.parseColor("#7BB661"), // Bud green
//    uncheckedColor: Int = Color.parseColor("#A3C1AD"), // Cambridge blue
//    activeColor: Int = Color.parseColor("#1034A6"), // Egyptian blue
//    inactiveColor: Int = Color.parseColor("#614051"), // Eggplant
//    pressedColor: Int = Color.parseColor("#FFD300"), // Cyber yellow
//    focusedColor: Int = Color.parseColor("#00FFFF") // Aqua
//): ColorStateList {
//    val states = arrayOf(
//        intArrayOf(android.R.attr.state_enabled),
//        intArrayOf(-android.R.attr.state_enabled),
//        intArrayOf(android.R.attr.state_checked),
//        intArrayOf(-android.R.attr.state_checked),
//        intArrayOf(android.R.attr.state_active),
//        intArrayOf(-android.R.attr.state_active),
//        intArrayOf(android.R.attr.state_pressed),
//        intArrayOf(android.R.attr.state_focused)
//    )
//    val colors = intArrayOf(
//        enabledColor, // enabled
//        disabledColor, // disabled
//        checkedColor, // checked
//        uncheckedColor, // unchecked
//        activeColor, // active
//        inactiveColor, // inactive
//        pressedColor, // pressed
//        focusedColor // focused
//    )
//    return ColorStateList(states, colors)
//}