package dev.arganaphangquestian.github.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.utils.SETREMINDER
import dev.arganaphangquestian.github.utils.SharedPrefs
import kotlinx.android.synthetic.main.fragment_setting.*

@AndroidEntryPoint
class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isReminder = SharedPrefs.get(SETREMINDER, false) as Boolean
        sm_reminder.isChecked = isReminder

        sm_reminder.setOnCheckedChangeListener { _, isChecked ->
            SharedPrefs.set(SETREMINDER, isChecked)
        }
    }

}