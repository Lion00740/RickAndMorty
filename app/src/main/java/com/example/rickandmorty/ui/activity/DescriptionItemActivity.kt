package com.example.rickandmorty.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rickandmorty.R
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

        viewModel.getCharacter(intent.extras!!.getInt("id"))

        viewModel.character.observe(this@DescriptionItemActivity, Observer {
            with(binding) {
                Picasso.get().load(it.image).into(avatar)
                name.text = it.name
                statusAndSpecies.text = "${it.species} - ${it.status}"
                location.text = "${resources.getString(R.string.location)} ${it.location.name}"
                origin.text = "${resources.getString(R.string.origin)} ${it.origin.name}"
                if (it.isBookmark) {
                    fabBookmark.setImageResource(R.drawable.bookmark_check)
                } else {
                    fabBookmark.setImageResource(R.drawable.bookmark)
                }
            }
        })

        binding.fabBookmark.setOnClickListener {
            viewModel.setBookmark()
            viewModel.character.observe(this@DescriptionItemActivity, Observer { character ->
                if(character.isBookmark) {
                    binding.fabBookmark.setImageResource(R.drawable.bookmark_check)
                } else {
                    binding.fabBookmark.setImageResource(R.drawable.bookmark)
                }
            })
        }
    }
}