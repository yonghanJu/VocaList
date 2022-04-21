package com.jyh.juyonghan201811567

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.jyh.juyonghan201811567.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding:ActivityIntroBinding
    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == RESULT_OK){
            val voc = it.data?.getSerializableExtra("voc") as MyData
            Toast.makeText(this,voc.word+" 추가됨", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
    }

    private fun initLayout() {
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, AddVocaActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }
}