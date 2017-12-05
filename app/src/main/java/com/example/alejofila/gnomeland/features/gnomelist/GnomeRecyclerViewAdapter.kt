package com.example.alejofila.gnomeland.features.gnomelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.alejofila.gnomeland.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.gnome_item.view.*

/**
 *
 */
class GnomeRecyclerViewAdapter(var gnomes: List<GnomeUIModel> = emptyList()) : RecyclerView.Adapter<GnomeRecyclerViewAdapter.GnomeViewHolder>(), Filterable {

    private val clickSubject = PublishSubject.create<GnomeUIModel>()


    private var gnomesFiltered = gnomes
    override fun onBindViewHolder(holder: GnomeViewHolder?, position: Int) {
        holder?.bind(gnomesFiltered[position])
    }

    override fun getItemCount(): Int {
        return gnomesFiltered.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GnomeViewHolder {
        val rootView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.gnome_item, parent, false)

        return GnomeViewHolder(rootView)
    }

    fun swapData(newGnomes: List<GnomeUIModel>) {
        gnomes = newGnomes
        gnomesFiltered = gnomes
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    gnomesFiltered = gnomes
                } else {
                    val filteredList = gnomes.filter {
                        it.name.toLowerCase().contains(charString.toLowerCase())
                    }

                    gnomesFiltered = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = gnomesFiltered
                return filterResults
            }


            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                gnomesFiltered = filterResults?.values as List<GnomeUIModel>
                notifyDataSetChanged()
            }
        }
    }

    val clickEvent: Observable<GnomeUIModel> = clickSubject

    inner class GnomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(gnome: GnomeUIModel) {
            view.itemGnomeName.text = gnome.name
            view.itemGnomeId.text = gnome.id.toString()
            view.itemGnomeAge.text = gnome.age.toString()
            view.itemGnomeHairColor.setBackgroundResource(getHairColor(gnome.hairColor))
            view.setOnClickListener {
                clickSubject.onNext(gnome)
            }

        }

        fun getHairColor(color: String): Int {
            return when (color) {
                "Pink" -> R.color.hair_pink
                "Red" -> R.color.hair_red
                "Green" -> R.color.hair_green
                "Black" -> R.color.hair_black
                "Gray" -> R.color.hair_gray
                else -> R.color.hair_pink
            }
        }
    }

}


