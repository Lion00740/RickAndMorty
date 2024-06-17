package com.example.rickandmorty.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rickandmorty.databinding.DescriptionItemLayoutBinding
import com.example.rickandmorty.ui.viewmodel.DescriptionItemViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionItemActivity : AppCompatActivity() {
    private lateinit var binding: DescriptionItemLayoutBinding
    private val viewModel: DescriptionItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DescriptionItemLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.name.text = intent.extras!!.getString("name", "")
        Picasso.get().load(intent.extras!!.getString("avatar", "")).into(binding.avatar)

        viewModel.getCharacter(intent.extras!!.getInt("id", 0))

        viewModel.test.observe(this@DescriptionItemActivity, Observer {
            with(binding) {
                statusAndSpecies.text = "${it.species} - ${it.status}"
                location.text = "Локация: ${it.location.name}"
                origin.text = "Происхождение: ${it.origin.name}"
            }
        })
    }
}