package com.example.authorization.ui.splash

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.authorization.R
import com.example.authorization.common.BaseFragment
import com.example.authorization.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSplashBinding::inflate

    override fun attachListeners() {
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.actionToLoginFragment)
        }
    }

}