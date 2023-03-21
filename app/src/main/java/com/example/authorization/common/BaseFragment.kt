package com.example.authorization.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out T : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        attachListeners()
        observeViewModel()
    }

    open fun setupUi() {
        // implement setup ui
    }

    open fun attachListeners() {
        // implement setup ui
    }

    open fun observeViewModel() {
        // implement setup ui
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}