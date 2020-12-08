package com.tmw.incomeexpense.ui.income

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Expense
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.ui.cash.CashViewModel
import kotlinx.android.synthetic.main.fragment_income.view.*
import kotlinx.android.synthetic.main.fragment_update_expense.*
import kotlinx.android.synthetic.main.fragment_update_expense.txt_update_expense_date
import kotlinx.android.synthetic.main.fragment_update_expense.view.*
import kotlinx.android.synthetic.main.fragment_update_income.*
import kotlinx.android.synthetic.main.fragment_update_income.view.*
import kotlinx.android.synthetic.main.fragment_update_income.view.spinner_category_income
import java.text.SimpleDateFormat
import java.util.*


class UpdateIncomeFragment : Fragment() {

    private val args by navArgs<UpdateIncomeFragmentArgs>()

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var incomeCategoriesViewModel: IncomeCategoriesViewModel
    private lateinit var cashViewModel:CashViewModel

    var formatDate= SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_income, container, false)

        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)

        incomeCategoriesViewModel=ViewModelProviders.of(this).get(IncomeCategoriesViewModel::class.java)

        var incomecategoriesAdapter=ArrayAdapter<Any>(requireContext(),R.layout.custom_spinner_layout)
        incomecategoriesAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_layout)

        incomeCategoriesViewModel.allIncomeCategories.observe(viewLifecycleOwner, Observer {incomecategories->
            incomecategories?.forEach {
                incomecategoriesAdapter.add(it.inc_cname)
            }
        })

        view.spinner_category_income.adapter=incomecategoriesAdapter

        cashViewModel=ViewModelProviders.of(this).get(CashViewModel::class.java)

        var cashcategoriesAdapter=ArrayAdapter<Any>(requireContext(),R.layout.custom_spinner_layout)
        cashcategoriesAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_layout)

        cashViewModel.allCashCategories.observe(viewLifecycleOwner,Observer{cashcategories->
            cashcategories?.forEach {
                cashcategoriesAdapter.add(it.cash_name)
            }
        })

        view.spinner_update_cash_income.adapter=cashcategoriesAdapter

        val c= Calendar.getInstance()
        val currentDate= SimpleDateFormat("dd/MM/yyyy").format(c.time)
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        view.update_income_date_picker_actions.setOnClickListener {
            val getDate=Calendar.getInstance()
            val datePicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate=Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH,i2)
                selectDate.set(Calendar.DAY_OF_MONTH,i3)

                val date=formatDate.format(selectDate.time)

                view.txt_update_income_date.setText(date)

            },getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        view.txt_update_income_date.setText(args.currentIncome.date)
        view.spinner_update_cash_income.setTag(args.currentIncome.cash)
        view.spinner_category_income.setTag(args.currentIncome.category)
        view.etd_update_income_remark.setText(args.currentIncome.remark)
        view.etd_update_income_amount.setText(args.currentIncome.amount.toString())

        view.btn_update_income.setOnClickListener {
            updateIncome()
        }

        return view
    }

    private fun updateIncome() {
        val date=txt_update_income_date.text.toString()
        val cash=spinner_update_cash_income.selectedItem.toString()
        val category=spinner_category_income.selectedItem.toString()
        val remark=etd_update_income_remark.text
        val amount=etd_update_income_amount.text

        if(inputCheck(date,cash,category,remark,amount)){
            val income= Income(args.currentIncome.income_id,date,cash,category,remark.toString(),Integer.parseInt(amount.toString()))
            incomeViewModel.updateIncome(income)
            Toast.makeText(context,"Successfully Updated", Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_updateIncomeFragment2_to_nav_home)
        }else{
            Toast.makeText(context,"Please Fill All Fields", Toast.LENGTH_LONG)
        }
    }

    private fun inputCheck(date: String, cash: String, category: String, remark: Editable, amount: Editable): Boolean {
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(cash) && TextUtils.isEmpty(category) && remark.isEmpty()
                && amount.isEmpty())
    }

}