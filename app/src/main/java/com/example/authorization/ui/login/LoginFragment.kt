package com.example.authorization.ui.login

import android.view.LayoutInflater
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.authorization.R
import com.example.authorization.common.BaseFragment
import com.example.authorization.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    override fun attachListeners() {
        binding.buttonLogin.setOnClickListener {
            performLogin()
        }

        binding.tvRegisterNow.setOnClickListener {
            findNavController().navigate(R.id.actionToRegisterFragment)

        }
    }

    private fun performLogin() {
        val inputEmail = binding.tilEmailLogin.editText?.text.toString();
        val inputPassword = binding.tilPasswordLogin.editText?.text.toString();

        if (inputEmail.isBlank() || inputPassword.isBlank()) {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.actionToHomeFragment)
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Authentication failed ${it.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}