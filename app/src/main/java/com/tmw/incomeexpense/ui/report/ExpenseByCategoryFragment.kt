package com.tmw.incomeexpense.ui.report

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.ui.expense.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_expense_by_category.view.*
import kotlinx.android.synthetic.main.fragment_expense_by_category.view.recycler_category
import kotlinx.android.synthetic.main.fragment_income_by_category.view.*
import java.text.SimpleDateFormat
import java.util.*


class ExpenseByCategoryFragment : Fragment() {

    private lateinit var expenseViewModel:ExpenseViewModel
    private lateinit var expenseByCategoryAdapter: ExpenseByCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_expense_by_category, container, false)

        expenseByCategoryAdapter= ExpenseByCategoryAdapter()

        view.recycler_category.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=expenseByCategoryAdapter
        }

        val c= Calendar.getInstance()
        val startDate= SimpleDateFormat("dd/MM/yyyy").format(c.time)
        val endDate= SimpleDateFormat("dd/MM/yyyy").format(c.time)
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        val currentDate= SimpleDateFormat("dd/MM/yyyy").format(c.time)

//        view.txt_start_date.setText(currentDate)
//        view.txt_end_date.setText(currentDate)
//
//        view.start_date_picker_actions.setOnClickListener {
//            val dpd= DatePickerDialog(requireContext(),
//                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->
//                    txt_start_date.setText(""+ mDay +"/"+ mMonth +"/"+ mYear)
//                },year,month,day)
//            dpd.show()
//        }
//
//        view.end_date_picker_actions.setOnClickListener {
//            val dpd= DatePickerDialog(requireContext(),
//                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->
//                    txt_end_date.setText(""+ mDay +"/"+ mMonth +"/"+ mYear)
//                },year,month,day)
//            dpd.show()
//        }

        expenseViewModel=ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        expenseViewModel.allExpense.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                if(it!=null){
                    var expenseCategoryName=LinkedList<String>()
                    for(data in it){
                        if(!expenseCategoryName.contains(data.category)) {
                            expenseCategoryName.add(data.category)
                        }
                    }

                    var count=0
                    var expenseReportList=ArrayList<ExpenseReport>()
                    for(data in expenseCategoryName){
                        count+=1
                        expenseViewModel.getExpenseByCategory(data).observe(
                            viewLifecycleOwner, androidx.lifecycle.Observer {
                                var expenseReport=ExpenseReport(data,it)
                                expenseReportList.add(expenseReport)
                                if(expenseCategoryName.size==count){
                                    expenseByCategoryAdapter.setDataExpenseReport(expenseReportList)
                                }
                            }
                        )
                    }
                }
            }
        )

        return view
    }

}

data class ExpenseReport(val category:String, val amount:Int)