package com.tmw.incomeexpense.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        incomeViewModel = ViewModelProviders.of(this).get(IncomeViewModel::class.java)
        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        view.txt_today_income.text="0"
        view.txt_today_expense.text="0"

        incomeViewModel.i_sumAmount().observe(
            viewLifecycleOwner, Observer {
                if (it != null){
                    view.txt_today_income.text = it.toString()
                }
                val income=view.txt_today_income.text.toString().toInt()
                Log.d("income>>",income.toString())
            }
        )

        expenseViewModel.e_sumAmount().observe(
            viewLifecycleOwner, Observer {
                if (it != null){
                    view.txt_today_expense.text = it.toString()
                }
                val expense =view.txt_today_expense.text.toString().toInt()
                Log.d("expense>>",expense.toString())
            }
        )

        incomeViewModel.i_sumAmount().observe(
            viewLifecycleOwner, Observer {
                if(it!=null){
                    val i_balance=it
                    expenseViewModel.e_sumAmount().observe(
                        viewLifecycleOwner, Observer {
                            if(it!=null){
                                val e_balance=it

                                if(i_balance>e_balance) {
                                    val balance = i_balance - e_balance
                                    view.txt_balance.text = balance.toString()
                                }
                                else{
                                    val balance=e_balance - i_balance
                                    view.txt_balance.text="-" + balance.toString()
                                }
                            }
                        }
                    )
                }


            }
        )

        view.card_income.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_home_to_incomeHistoryFragment)
        }

        view.btn_new_income.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_home_to_incomeFragment)
        }

        view.card_expense.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_home_to_expenseHistoryFragment)
        }

        view.btn_new_expense.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_home_to_expenseFragment)
        }

        return view
    }

}