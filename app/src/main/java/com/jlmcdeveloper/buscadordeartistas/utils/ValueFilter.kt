package com.jlmcdeveloper.buscadordeartistas.utils

import android.widget.Filter;
import java.util.*
import kotlin.collections.ArrayList


class ValueFilter<T>(
    private val list: ArrayList<T>,
    val condition: (T) -> String,
    val result: (ArrayList<T>) -> Unit
) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val results = FilterResults()

        if (constraint!!.isNotEmpty()) {
            val filterList = ArrayList<T>()

            list.forEach {
                val cont = condition(it)
                if (cont.toUpperCase(Locale.ROOT)
                        .contains(constraint.toString().toUpperCase(Locale.ROOT)))
                    filterList.add(it)
            }
            results.count = filterList.size
            results.values = filterList
        }else{
            results.count = list.size
            results.values = list
        }
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        if (results != null)
            result(results.values as ArrayList<T>)
    }
}