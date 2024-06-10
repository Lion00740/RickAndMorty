package com.example.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.DescriptionItemLayoutBinding

class DescriptionItem : AppCompatActivity() {
    private lateinit var binding: DescriptionItemLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DescriptionItemLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}