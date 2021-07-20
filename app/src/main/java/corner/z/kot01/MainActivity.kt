package corner.z.kot01

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

class MainActivity : AppCompatActivity() {
    private lateinit var varToDoAdapter: adptToDo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        varToDoAdapter= adptToDo(mutableListOf())
        rvToDo.adapter= varToDoAdapter
        rvToDo.layoutManager = LinearLayoutManager(this)

        btAdd.setOnClickListener{
            val strToDo = etToDo.text.toString()
            if(strToDo.isNotEmpty()){
                val tmpToDo = ToDo(strToDo)
                val generalReturn = varToDoAdapter.addToDo(tmpToDo)
                if(generalReturn.ReturnCode >= 0) {
                    etToDo.text.clear()
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage(generalReturn.ReturnString)
                    builder.show()
                }
            }
        }

        btRemoveDone.setOnClickListener {
            varToDoAdapter.removeDone()
        }
    }
}