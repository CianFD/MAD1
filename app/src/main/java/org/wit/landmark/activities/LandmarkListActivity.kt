package org.wit.landmark.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.landmark.R
import org.wit.landmark.adapters.LandmarkAdapter
import org.wit.landmark.adapters.LandmarkListener
import org.wit.landmark.databinding.ActivityLandmarkListBinding
import org.wit.landmark.main.MainApp
import org.wit.landmark.models.LandmarkModel

class LandmarkListActivity : AppCompatActivity(), LandmarkListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityLandmarkListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandmarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadLandmarks()

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, LandmarkActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLandmarkClick(landmark: LandmarkModel) {
        val launcherIntent = Intent(this, LandmarkActivity::class.java)
        launcherIntent.putExtra("landmark_edit", landmark)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadLandmarks() }
    }

    private fun loadLandmarks() {
        showLandmarks(app.landmarks.findAll())
    }

    fun showLandmarks (landmarks: List<LandmarkModel>) {
        binding.recyclerView.adapter = LandmarkAdapter(landmarks, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}