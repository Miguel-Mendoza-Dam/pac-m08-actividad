package com.miguelmendoza.pac_desarrollo_m08_mendozamiguel

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.miguelmendoza.pac_desarrollo_m08_mendozamiguel.databinding.Activity1Binding

class Activity1 : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var isReady: Boolean = false

//    private val playAudioBtn: Button = findViewById(R.id.playAudioBtn)
//    private val stopAudioBtn: Button = findViewById(R.id.stopAudioBtn)

    private lateinit var binding: Activity1Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = Activity1Binding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAudio()

        binding.apply {

            stopAudioBtn.isEnabled = false

            // Boton PLAY
            playAudioBtn.setOnClickListener {
                playAudio()
                stopAudioBtn.isEnabled = true
            }


            // Boton STOP
            stopAudioBtn.setOnClickListener {
                stopAudio()
                stopAudioBtn.isEnabled = false
            }
        }





    }

    private fun initAudio(){
        mediaPlayer = MediaPlayer()

//            Toast.makeText(this, "Audio finalizado", Toast.LENGTH_SHORT).show()

        val attribute = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        mediaPlayer?.setAudioAttributes(attribute)

        val afd = applicationContext.resources.openRawResourceFd(R.raw.triforce)

        try {
            mediaPlayer?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mediaPlayer?.setOnPreparedListener {
            isReady = true
            mediaPlayer?.start()
        }

        mediaPlayer?.setOnErrorListener { _, _, _ -> false }

    }

    // Accion del boton PLAY. Inicia la reproduccion del audio
    private fun playAudio() {
        if (!isReady) {
            mediaPlayer?.prepareAsync()
        } else {
            mediaPlayer?.start()
        }
    }

    // Accion del boton STOP. Detiene la reproduccion del audio
    private fun stopAudio() {
        if (mediaPlayer?.isPlaying as Boolean || isReady) {
            mediaPlayer?.stop()
            isReady = false
        }

    }

}