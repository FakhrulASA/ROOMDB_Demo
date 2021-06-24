package com.example.notetodo_roomdb


import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetodo_roomdb.adapter.WordListAdapter
import com.example.notetodo_roomdb.room.entitiy.Word
import com.example.notetodo_roomdb.viewModel.WordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity2 : AppCompatActivity() {

    val mWordViewModel:WordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(WordListAdapter.WordDiff())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->

            val intent = Intent(this@MainActivity2, NewWordActivity::class.java)
            resultLauncher.launch(intent)


        }


    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
                val word = Word(data?.getStringExtra(NewWordActivity.EXTRA_REPLY)!!)
                mWordViewModel.insert(word)

        }
    }


}

