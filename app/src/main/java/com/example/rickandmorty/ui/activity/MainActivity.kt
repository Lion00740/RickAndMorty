package com.example.rickandmorty.ui.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.CharacterAdapter
import com.example.rickandmorty.domain.Characters
import com.example.rickandmorty.RickAndMortyApi
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CharacterAdapter()
        onLoading()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        viewModel.getAllCharacters()
        viewModel.test.observe(this@MainActivity, Observer {
            adapter.submitList(it.results)
            onResponse()
        })

        adapter.onItemClick = {
            val intent = Intent(this@MainActivity, DescriptionItemActivity::class.java)
            intent.putExtra("name", "${it.name}")
            intent.putExtra("avatar", "${it.image}")
            startActivity(intent)
        }
    }
    private fun dialog() {
        val dialogButtonListener = DialogInterface.OnClickListener { dialog, element ->
            when(element) {
                DialogInterface.BUTTON_POSITIVE -> dialog.cancel()
                DialogInterface.BUTTON_NEGATIVE -> finish()
            }
        }

        val internetErrorDialog = AlertDialog.Builder(this)
            .setTitle("Проблема!")
            .setMessage("Отсутствует подключение к интернету!")
            .setPositiveButton("Продолжить", dialogButtonListener)
            .setNegativeButton("Выход", dialogButtonListener)
            .create()

        internetErrorDialog.show()
    }
    private fun onLoading() {
        binding.recyclerView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun onResponse() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }
}