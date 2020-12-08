package com.tmw.incomeexpense.ui.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import kotlinx.android.synthetic.main.fragment_income.view.*

class IncomeAdapter :RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    private var incomeList= emptyList<Income>()

    fun setOnClickListener(inc:List<Income>){
        this.incomeList=inc
    }
    class IncomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val inc_date=itemView.txt_income_date
        val inc_cash=itemView.spinner_cash_income
        val inc_category=itemView.spinner_category_income
        val inc_remark=itemView.etd_income_remark
        val inc_amount=itemView.etd_income_amount

        fun bind(income: Income){
            inc_date.text=income.date
            inc_cash.selectedItem
            inc_category.selectedItem
            inc_remark.setText(income.remark)
            inc_amount.setText(income.amount.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_income,parent,false)
        return IncomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        holder.bind(incomeList[position])
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    fun setDataIncome(income: List<Income>){
        this.incomeList=income
        notifyDataSetChanged()
    }

}