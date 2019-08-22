#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}#end

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class ${NAME} : RecyclerView.Adapter<${NAME}.${Model}Viewholder>()  {
    var data:ArrayList<${Model}> = arrayListOf()
        set(newList) {
            val calculateDiff = DiffUtil.calculateDiff(${Model}DiffCallback(field,newList))
            calculateDiff.dispatchUpdatesTo(this)
            field = newList
        }
    override fun getItemCount(): Int = data.size
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.${Layout}, parent, false)
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
    
    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
         fun bind(item: ${Model}) = with(itemView) {
               
        }
    }
}

class ${Model}DiffCallback(val oldList:ArrayList<${Model}>,val newList:ArrayList<${Model}>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.id == new.id
    }
    override fun getOldListSize(): Int {
        return oldList.size
    }
    override fun getNewListSize(): Int {
        return newList.size
    }
}