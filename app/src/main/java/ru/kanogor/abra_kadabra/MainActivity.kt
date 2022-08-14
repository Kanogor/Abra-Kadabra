package ru.kanogor.abra_kadabra

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import ru.kanogor.abra_kadabra.databinding.ActivityMainBinding
import kotlin.math.hypot

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dogs.addAnimatorUpdateListener { valueAnimator ->
            val doubleProgress = 1.53 * (valueAnimator.animatedValue as Float * 100).toInt()
            val progress = doubleProgress.toInt()
            binding.linearProgress.progress = progress
            binding.topText.text = getString(R.string.loading, progress)
            when (progress) {
                100 -> {
                    revealLayoutFun()
                }
            }
        }
        binding.buttonOne.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuDisplay::class.java)
            startActivity(intent)
        }
        binding.buttonTwo.setOnClickListener {
            Toast.makeText(this, "It's part now in the development stage", Toast.LENGTH_SHORT)
                .show()
        }
        binding.buttonThree.setOnClickListener {
            val scaleX = PropertyValuesHolder.ofFloat("scaleX", 2.0F)
            val scaleY = PropertyValuesHolder.ofFloat("scaleY", 2.0F)

            val obj = ObjectAnimator.ofPropertyValuesHolder(binding.textMenu, scaleX, scaleY)
            obj.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {}
                override fun onAnimationEnd(p0: Animator?) {
                    val scaleX2 = PropertyValuesHolder.ofFloat("scaleX", 1.0F)
                    val scaleY2 = PropertyValuesHolder.ofFloat("scaleY", 1.0F)
                    ObjectAnimator.ofPropertyValuesHolder(binding.textMenu, scaleX2, scaleY2)
                        .setDuration(1000L)
                        .start()
                }

                override fun onAnimationCancel(p0: Animator?) {}
                override fun onAnimationRepeat(p0: Animator?) {}
            })
            obj.setDuration(1000L).start()
        }
    }

    private fun revealLayoutFun() {
        val x: Int = binding.revealLayout.right / 2
        val y: Int = binding.revealLayout.bottom / 2
        val startRadius = 0
        val endRadius = hypot(
            binding.revealLayout.width.toDouble(),
            binding.revealLayout.height.toDouble()
        ).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.revealLayout,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        binding.revealLayout.visibility = View.VISIBLE
        binding.dogs.visibility = View.GONE
        anim.start()
    }
}