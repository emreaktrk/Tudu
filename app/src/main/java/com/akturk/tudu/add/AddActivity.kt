package com.akturk.tudu.add

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.AlarmManagerCompat
import com.akturk.tudu.BaseActivity
import com.akturk.tudu.BindingSupport
import com.akturk.tudu.R
import com.akturk.tudu.ViewModelSupport
import com.akturk.tudu.databinding.ActivityAddBinding
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddActivity : BaseActivity(R.layout.activity_add),
    ViewModelSupport<AddViewModel>,
    BindingSupport<ActivityAddBinding>,
    AddViewModel.Presenter {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AddActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel: AddViewModel by invoke(this, AddViewModel::class.java)

    override val binding: ActivityAddBinding by invoke(this, R.layout.activity_add)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.presenter = this
    }

    override fun presentApplication(): Application = application

    override fun presentDatePicker() {
        DatePickerDialog.newInstance { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
        }.show(supportFragmentManager, null)
    }

    override fun presentAlarm(timeInMillis: Long): Boolean {
        val alarm = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarm?.let {
            val intent = Intent(this, AlarmReceiver::class.java)
            val pending = PendingIntent.getService(
                this,
                123123,
                intent,
                PendingIntent.FLAG_ONE_SHOT
            )
            AlarmManagerCompat.setExact(
                it,
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pending
            )

            return true
        }

        return false
    }

    override fun navigateBack() {
        finish()
    }
}