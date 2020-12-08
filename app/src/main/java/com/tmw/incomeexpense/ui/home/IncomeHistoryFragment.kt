package com.tmw.incomeexpense.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import com.tmw.incomeexpense.ui.income.IncomeAdapter
import com.tmw.incomeexpense.ui.income.IncomeCategoriesAdapter
import com.tmw.incomeexpense.ui.income.IncomeHistoryAdapter
import com.tmw.incomeexpense.ui.income.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_income_history.view.*

class IncomeHistoryFragment : Fragment(), IncomeHistoryAdapter.RowClickListener {

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var incomeHistoryAdapter: IncomeHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_income_history, container, false)

        view.recycler_income_history.apply{
            layoutManager=LinearLayoutManager(requireContext())
            incomeHistoryAdapter=IncomeHistoryAdapter(this@IncomeHistoryFragment)
            adapter=incomeHistoryAdapter
        }

        incomeViewModel=ViewModelProviders.of(this).get(IncomeViewModel::class.java)
        incomeViewModel.allIncome.observe(viewLifecycleOwner, Observer { income->
            incomeHistoryAdapter.setDataIncomeHistory(income)
            incomeHistoryAdapter.notifyDataSetChanged()
            for (data in income){
                Log.d("IncomeHistorydate>>",data.date)
            }
        })

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllIncome()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllIncome() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All Income")
        builder.setMessage("Are you sure want to delete all income?")
        builder.setPositiveButton("Yes"){_, _ ->
            incomeViewModel.deleteAllIncome()
            Toast.makeText(context,"Successfully Delete All Income", Toast.LENGTH_LONG)
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.create().show()
    }

    override fun onDeleteIncomeClick(income: Income) {
        val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Income")
        builder.setMessage("Are you sure want to delete income?")
        builder.setPositiveButton("Yes"){_, _ ->
            incomeViewModel.deleteIncome(income)
            Toast.makeText(context,"Successfully Delete Income",Toast.LENGTH_LONG)
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.create().show()
    }


}