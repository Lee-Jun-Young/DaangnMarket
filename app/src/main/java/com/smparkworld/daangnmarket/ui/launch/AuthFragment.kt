package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.FragmentLaunchAuthBinding
import com.smparkworld.daangnmarket.extension.setKeyboard
import com.smparkworld.daangnmarket.extension.showSnackbar
import javax.inject.Inject
import kotlin.math.min

class AuthFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by activityViewModels<LoginViewModel> { viewModelFactory }

    private var isAuthMode = false

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
                if (!isAuthMode) {
                    showSnackbar(R.string.error_notYetSMS)
                    loginViewModel.authorization()
                    authMode()
                } else {
                    val time = loginViewModel.authTimer.value?.split(":")
                    if (time != null) {
                        val intTime = (time[0].toInt() * 60) + time[1].toInt()
                        if (intTime < 280) {
                            showSnackbar(R.string.error_notYetSMS)
                            loginViewModel.authorization()
                        } else {
                            showSnackbar(R.string.fragmentAuth_authRetryNotYet)
                        }
                    }
                }
                setKeyboard(false, binding.etPhoneNumber)
            }
            R.id.tvFindByEmail -> {
                showSnackbar(R.string.error_notYet)
            }
            R.id.btnConfirmSecurityNum -> {
                loginViewModel.confirmSecurityNumber()
                setKeyboard(false, binding.etSecurityNumber)
            }
        }
    }

    private fun authMode() {
        isAuthMode = true
        binding.btnAuth.setText(R.string.fragmentAuth_authRetryBtn)
        binding.tvFindByEmail.visibility = View.GONE
        binding.llSecurityNum.visibility = View.VISIBLE
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initPhoneNumberLimit(et: EditText) {
        val mask = { origin: String ->
            val builder = StringBuilder(origin.replace(("[^\\d]").toRegex(), ""))
            if (builder.length > 2) builder.insert(3, " ")
            if (builder.length > 7) builder.insert(8, " ")
            builder.toString().let { it.substring(0, min(13, it.length)) }
        }
        et.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before == 0 && s != null) {
                    val converted = mask(s.toString())
                    et.setText(converted)
                    et.setSelection(converted.length)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}