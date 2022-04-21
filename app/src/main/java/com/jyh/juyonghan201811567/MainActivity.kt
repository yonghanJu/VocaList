package com.jyh.juyonghan201811567

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jyh.juyonghan201811567.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var data: ArrayList<MyData> = ArrayList()
    private lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(data)
        adapter.setOnItemClickListener{ pos->
            adapter.changeState(pos)
        }
        binding.recyclerView.adapter = adapter
        val simpleTouchItemTouchHelper = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.move(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleTouchItemTouchHelper)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun readFileScan(scan:Scanner){
        while(scan.hasNext()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            data.add(MyData(word, meaning,false))
        }
    }

    private fun initData() {
        try{
            readFileScan(Scanner(openFileInput("out.txt")))
        }catch (e:Exception){
            Toast.makeText(this,"추가된 단어 없음",Toast.LENGTH_SHORT).show()
        }
        readFileScan(Scanner(resources.openRawResource(R.raw.words)))
    }
}