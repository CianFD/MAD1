package org.wit.landmark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.landmark.databinding.ActivityLandmarkBinding
import org.wit.landmark.models.LandmarkModel
import timber.log.Timber
import timber.log.Timber.i

class LandmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandmarkBinding
    var landmark = LandmarkModel()
    val landmarks = ArrayList<LandmarkModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Landmark Activity booted up...")

        binding.btnAdd.setOnClickListener() {
            landmark.title = binding.landmarkTitle.text.toString()
            landmark.description = binding.description.text.toString()
            if (landmark.title.isNotEmpty()) {

                landmarks.add(landmark.copy())
                i("add Button Pressed: ${landmark}")
                for (i in landmarks.indices) {
                    i("Landmark[$i]:${this.landmarks[i]}")
                }
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}