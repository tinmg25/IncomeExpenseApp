package com.tmw.incomeexpense.ui.income

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmw.incomeexpense.R
import com.tmw.incomeexpense.model.IncomeCategory
import kotlinx.android.synthetic.main.item_income_categories.view.*

class IncomeCategoriesAdapter(var listener: RowClickListener) :
    RecyclerView.Adapter<IncomeCategoriesAdapter.IncomeCategoriesViewHolder>() {

    private var incomecategoriesList = emptyList<IncomeCategory>()

    fun clickListener(icategory:List<IncomeCategory>){
        this.incomecategoriesList=icategory
    }


    inner class IncomeCategoriesViewHolder(itemView: View, val listener: RowClickListener) :
        RecyclerView.ViewHolder(itemView){

        val inc_category=itemView.txt_income_category
        val del_category=itemView.img_income_category_delete

        fun bind(income_category: IncomeCategory){
            inc_category.text=income_category.inc_cname

            del_category.setOnClickListener {
                listener.onDeleteCategoryClick(income_category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeCategoriesViewHolder {
        var inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_income_categories, parent, false)
        return IncomeCategoriesViewHolder(inflater,listener)
    }

    override fun onBindViewHolder(holder: IncomeCategoriesViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
             listener.onCategoryClickListener(incomecategoriesList[position])
        }
        holder.bind(incomecategoriesList[position])
    }

    override fun getItemCount(): Int {
        return incomecategoriesList.size
    }

    fun setDataIncomeCategory(incomeCategory: List<IncomeCategory>) {
        this.incomecategoriesList = incomeCategory
        notifyDataSetChanged()
    }

    interface RowClickListener {
        fun onDeleteCategoryClick(income_category: IncomeCategory)
        fun onCategoryClickListener(income_category: IncomeCategory)
    }
}