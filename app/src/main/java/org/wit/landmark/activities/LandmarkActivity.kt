package org.wit.landmark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.landmark.R
import org.wit.landmark.databinding.ActivityLandmarkBinding
import org.wit.landmark.main.MainApp
import org.wit.landmark.models.LandmarkModel
import timber.log.Timber
import timber.log.Timber.i

class LandmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandmarkBinding
    var landmark = LandmarkModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Landmark App boot...")
        binding.btnAdd.setOnClickListener() {
            landmark.title = binding.landmarkTitle.text.toString()
            landmark.description = binding.description.text.toString()
            if (landmark.title.isNotEmpty()) {
                app.landmarks.add(landmark.copy())
                i("add Button Pressed: ${landmark}")
                for (i in app.landmarks.indices) {
                    i("Landmark[$i]:${this.app.landmarks[i]}")
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_landmark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}