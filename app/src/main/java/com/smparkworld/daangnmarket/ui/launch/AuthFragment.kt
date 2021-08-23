package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.databinding.FragmentLaunchAuthBinding
import javax.inject.Inject
import kotlin.math.min

class AuthFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by activityViewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentLaunchAuthBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = loginViewModel

            initToolbar(toolbar)
            initPhoneNumberLimit(etPhoneNumber)
        }.root
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