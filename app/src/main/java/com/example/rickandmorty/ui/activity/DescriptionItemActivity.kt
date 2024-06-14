package com.example.rickandmorty.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.DescriptionItemLayoutBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionItemActivity : AppCompatActivity() {
    private lateinit var binding: DescriptionItemLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DescriptionItemLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.text = intent.extras!!.getString("name", "")
        Picasso.get().load(intent.extras!!.getString("avatar", "")).into(binding.avatar)
    }
}