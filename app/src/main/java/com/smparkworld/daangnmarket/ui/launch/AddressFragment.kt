package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.databinding.FragmentAddressBinding
import javax.inject.Inject

class AddressFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by activityViewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentAddressBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = loginViewModel
        }.root
    }
}