package com.tmw.incomeexpense.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.tmw.incomeexpense.R
import kotlinx.android.synthetic.main.fragment_categories.view.*

class CategoriesFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        view.btn_income.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_categories_to_incomeCategoriesFragment)
        }

        view.btn_expense.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_categories_to_expenseCategoriesFragment)
        }

        view.btn_cash.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_categories_to_cashCategoriesFragment)
        }
        return view
    }
}