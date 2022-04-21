package com.jyh.juyonghan201811567

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jyh.juyonghan201811567.databinding.ActivityAddVocaBinding
import java.io.PrintStream

class AddVocaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddVocaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVocaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
    }

    private fun initLayout() {
        binding.button3.setOnClickListener {
            val word = binding.word.text.toString()
            val meaning = binding.meaning.text.toString()
            writeFile(word,meaning)
        }

        binding.button4.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun writeFile(word: String, meaning: String) {
        val output = PrintStream(openFileOutput("out.txt", Context.MODE_APPEND))
        output.println(word)
        output.println(meaning)
        output.close()
        val intent = Intent()
        intent.putExtra("voc", MyData(word,meaning))
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}