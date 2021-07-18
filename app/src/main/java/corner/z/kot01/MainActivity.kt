package corner.z.kot01

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
                varToDoAdapter.addToDo(tmpToDo)
                etToDo.text.clear()
            }
        }

        btRemoveDone.setOnClickListener {
            varToDoAdapter.removeDone()
        }
    }
}