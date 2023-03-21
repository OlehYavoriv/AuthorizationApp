package com.example.authorization.ui.signUp

import android.view.LayoutInflater
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.authorization.R
import com.example.authorization.common.BaseFragment
import com.example.authorization.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSignUpBinding::inflate

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    override fun attachListeners() {
        binding.buttonRegister.setOnClickListener {
            performRegister()
        }
        binding.tvLoginNow.setOnClickListener {
            findNavController().navigate(R.id.actionToLoginFragment)
        }

    }

    private fun performRegister() {
        val inputName = binding.tilNameRegister.editText?.text.toString();
        val inputEmail = binding.tilEmailRegister.editText?.text.toString();
        val inputPassword = binding.tilPasswordRegister.editText?.text.toString();
        val inputConfirmPassword =
            binding.tilConfirmPasswordRegister.editText?.text.toString();

        if (inputName.isBlank() || inputEmail.isBlank() || inputPassword.isBlank() || inputConfirmPassword.isBlank()) {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }
        if (inputPassword != inputConfirmPassword) {
            Toast.makeText(requireContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = inputName
                    }
                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.actionToHomeFragment)
                            Toast.makeText(
                                requireContext(), "Registration successful!", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}