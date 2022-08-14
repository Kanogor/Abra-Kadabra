package ru.kanogor.abra_kadabra

import android.animation.Animator
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.content.res.ResourcesCompat
import ru.kanogor.abra_kadabra.databinding.ActivityMenuDisplayBinding
import kotlin.math.hypot
import kotlin.math.max

class MenuDisplay : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDisplayBinding

    private var isRevealed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.green,
                null
            )
        )

        binding.fab.setOnClickListener {
            revealLayoutFun()
        }
    }

    private fun revealLayoutFun() {
        if (!isRevealed) {
            val x: Int = binding.revealLayout.right / 2
            val y: Int = binding.revealLayout.bottom / 2
            val startRadius = 0
            val endRadius = hypot(
                binding.revealLayout.width.toDouble(),
                binding.revealLayout.height.toDouble()
            ).toInt()

            binding.fab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    resources,
                    R.color.white,
                    null
                )
            )
            binding.fab.setImageResource(R.drawable.ic_baseline_close_24)

            val anim = ViewAnimationUtils.createCircularReveal(
                binding.revealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            binding.revealLayout.visibility = View.VISIBLE
            anim.start()

            isRevealed = true

        } else {
            val x: Int = binding.revealLayout.right / 2
            val y: Int = binding.revealLayout.bottom / 2
            val startRadius: Int = max(binding.revealLayout.width, binding.revealLayout.height)
            val endRadius = 0
            binding.fab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    resources,
                    R.color.green,
                    null
                )
            )

            binding.fab.setImageResource(R.drawable.ic_add)

            val anim = ViewAnimationUtils.createCircularReveal(
                binding.revealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    binding.revealLayout.visibility = View.GONE
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })

            anim.start()

            isRevealed = false
        }
    }
}
