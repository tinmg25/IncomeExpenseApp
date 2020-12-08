package com.tmw.incomeexpense.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tmw.incomeexpense.R
import kotlinx.android.synthetic.main.fragment_report.view.*

class ReportFragment : Fragment() {

    private lateinit var slideshowViewModel: ReportViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ReportViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        view.card_report_1.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_incomeByCategoryFragment2)
        }

        view.card_report_2.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_expenseByCategoryFragment)
        }

        view.card_report_3.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_incomeExpenseDetailFragment)
        }

        view.card_report_4.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_expenseDetailFragment)
        }

        view.card_report_5.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_incomeByCashFragment)
        }

        view.card_report_6.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_expenseByCashFragment)
        }

        view.card_report_7.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_incomeByChartFragment)
        }

        view.card_report_8.setOnClickListener {
            findNavController().navigate(R.id.action_nav_report_to_expenseByChartFragment)
        }
        return view
    }
}