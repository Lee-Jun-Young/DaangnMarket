package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.FragmentLaunchAuthBinding
import com.smparkworld.daangnmarket.extension.setKeyboard
import com.smparkworld.daangnmarket.extension.showSnackbar
import javax.inject.Inject

class AuthFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by activityViewModels<LoginViewModel> { viewModelFactory }

    lateinit var binding: FragmentLaunchAuthBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLaunchAuthBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = loginViewModel
            onClick = ::onClick

            initToolbar(toolbar)
            initPhoneNumberLimit(etPhoneNumber)
        }
        return binding.root
    }

    private fun onClick(v: View) {
        when(v.id) {
            R.id.btnAuth -> {
                setKeyboard(false, binding.etPhoneNumber)
                showAuthMode()
                if (!checkPhoneNum()) {
                    showSnackbar(R.string.fragmentAuth_incorrectPhoneNum)
                    return
                }
                if (!checkRetryTime()) {
                    showSnackbar(R.string.fragmentAuth_authRetryNotYet)
                    return
                }
                showSnackbar(R.string.error_notYetSMS)
                loginViewModel.authorization()
            }
            R.id.tvFindByEmail -> {
                showSnackbar(R.string.error_notYet)
            }
            R.id.btnConfirmSecurityNum -> {
                setKeyboard(false, binding.etSecurityNumber)
                loginViewModel.confirmSecurityNumber()
            }
        }
    }

    private fun showAuthMode() {
        binding.btnAuth.setText(R.string.fragmentAuth_authRetryBtn)
        binding.tvFindByEmail.visibility = View.GONE
        binding.llSecurityNum.visibility = View.VISIBLE
    }

    private fun checkPhoneNum(): Boolean {
        val et = binding.etPhoneNumber
        return et.length() > 11 && et.text.contains("-")
    }

    private fun checkRetryTime(): Boolean {
        val time = loginViewModel.authTimer.value?.split(":")
        if (time != null) {
            return (time[0].toInt() * 60) + time[1].toInt() < 280
        }
        return true
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initPhoneNumberLimit(et: EditText) {
        et.addTextChangedListener(PhoneNumberFormattingTextWatcher("KR"))
    }
}