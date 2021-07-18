package corner.z.kot01

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class adptToDo(
    private val mlstToDo: MutableList<ToDo>
): RecyclerView.Adapter<adptToDo.vhToDoList>() {
    class vhToDoList(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vhToDoList {
        return vhToDoList(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mlstToDo.size
    }

    override fun onBindViewHolder(holder: vhToDoList, position: Int) {
        val toDoCurrent = mlstToDo[position]
        holder.itemView.apply{
            tvToDo.text = toDoCurrent.strToDo
            cbDone.isChecked = toDoCurrent.bDone
            fnToggleStrikeThrough(tvToDo, toDoCurrent.bDone)
            cbDone.setOnCheckedChangeListener{ _, isChecked ->
                fnToggleStrikeThrough(tvToDo, isChecked)
                toDoCurrent.bDone = !toDoCurrent.bDone
            }
        }
    }

    private fun fnToggleStrikeThrough(tvToDo: TextView, bChecked: Boolean){
        if(bChecked){
            tvToDo.paintFlags = tvToDo.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDo.paintFlags = tvToDo.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addToDo(toDo: ToDo){
        mlstToDo.add(toDo)
        notifyItemInserted(mlstToDo.size - 1)
    }

    fun removeDone() {
        mlstToDo.removeAll { toDo ->
            toDo.bDone
        }

        notifyDataSetChanged()
    }
}