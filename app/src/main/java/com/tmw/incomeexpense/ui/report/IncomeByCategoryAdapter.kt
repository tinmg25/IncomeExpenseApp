package com.tmw.incomeexpense.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.Income
import kotlinx.android.synthetic.main.item_income_by_category.view.*

class IncomeByCategoryAdapter: RecyclerView.Adapter<IncomeByCategoryAdapter.IncomeByCategoryViewHolder>(){

    private var incomebycategoryList= emptyList<IncomeReport>()

    class IncomeByCategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(income: IncomeReport){
            itemView.category_name.text=income.category
            itemView.category_amount.text=income.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeByCategoryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_income_by_category,parent,false)
        return IncomeByCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeByCategoryViewHolder, position: Int) {
        holder.bind(incomebycategoryList[position])
    }

    override fun getItemCount(): Int {
        return incomebycategoryList.size
    }

    fun setDataIncome(income: List<IncomeReport>){
        this.incomebycategoryList=income
        notifyDataSetChanged()
    }

}