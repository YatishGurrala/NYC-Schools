package com.nyc.school.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nyc.school.R
import com.nyc.school.model.SchoolResponse


// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
class ListAdapter(
    private var schoolData: List<SchoolResponse>,
    callback: AdapterCallBack?
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var mAdapterCallback: AdapterCallBack? = callback
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        // Passing view to ViewHolder
        return ViewHolder(view)
    }

    // Binding data to the into specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TypeCast Object to int type
        val image = schoolData[position]
        holder.name.text = image.schoolName
        holder.location.text = image.location
//        Glide.with(holder.itemView.context).load(image.media?.m).into(holder.image)
        holder.cardView.setOnClickListener {
            mAdapterCallback?.itemOnClick(position)
        }
    }

    override fun getItemCount(): Int {
        // Returns number of items
        // currently available in Adapter
        return schoolData.size
    }

    // Initializing the Views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var image: ImageView
        var name: TextView
        var location: TextView
        var cardView: CardView

        init {
//            image = view.findViewById<View>(R.id.imageview) as ImageView
            name = view.findViewById<View>(R.id.name) as TextView
            location = view.findViewById<View>(R.id.location) as TextView
            cardView = view.findViewById<View>(R.id.cardView) as CardView
        }
    }
}