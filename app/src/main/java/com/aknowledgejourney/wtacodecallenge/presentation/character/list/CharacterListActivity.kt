package com.aknowledgejourney.wtacodecallenge.presentation.character.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aknowledgejourney.wtacodecallenge.R
import com.aknowledgejourney.wtacodecallenge.WTACodeChallenge
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.aknowledgejourney.wtacodecallenge.databinding.ActivityMainBinding
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRemoteRepository
import com.aknowledgejourney.wtacodecallenge.presentation.character.detail.CharacterDetailActivity
import com.aknowledgejourney.wtacodecallenge.presentation.character.list.adapater.CharacterListAdapter
import com.aknowledgejourney.wtacodecallenge.presentation.character.list.viewmodel.CharacterListViewModel
import com.aknowledgejourney.wtacodecallenge.presentation.character.list.viewmodel.CharacterListViewModelFactory
import com.aknowledgejourney.wtacodecallenge.util.RepositoryStatus

class CharacterListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHARACTER = "character"
    }

    private val characterListViewModel: CharacterListViewModel by viewModels {
        CharacterListViewModelFactory(
            (application as WTACodeChallenge).characterRepository,
            CharacterRemoteRepository()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCharacters.apply {
            adapter = CharacterListAdapter(
                ::showDetail,
                ::changeFab
            )
            layoutManager = LinearLayoutManager(this@CharacterListActivity)
        }

        characterListViewModel.characterList.observe(this) { characterList ->
            characterList?.let {
                (binding.rvCharacters.adapter as CharacterListAdapter).submitList(it)
            }
        }

        characterListViewModel.serviceStatus.observe(this) {
            when (it!!) {
                RepositoryStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                RepositoryStatus.COMPLETE -> binding.progressBar.visibility = View.GONE
                RepositoryStatus.FAIL -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@CharacterListActivity, getString(R.string.list_msj_fail), Toast.LENGTH_LONG).show()
                }
            }
        }

        characterListViewModel.loadData(0)
    }

    private fun showDetail(characterEntity: CharacterEntity) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(EXTRA_CHARACTER, characterEntity)
        startActivity(intent)
    }

    private fun changeFab(characterEntity: CharacterEntity) {
        characterListViewModel.update(characterEntity)
    }

}