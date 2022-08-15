package ru.kanogor.abra_kadabra

import android.animation.Animator
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ru.kanogor.abra_kadabra.databinding.ActivityMenuDisplayBinding
import ru.kanogor.abra_kadabra.retrofit.MainViewModel
import ru.kanogor.abra_kadabra.retrofit.State
import kotlin.math.hypot
import kotlin.math.max

class MenuDisplay : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDisplayBinding

    private val vm: MainViewModel by viewModels()

    private var isRevealed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            vm.state.collect { state ->
                when (state) {
                    is State.Fail -> {
                        binding.main.visibility = View.GONE
                        binding.pleaseWaitMessage.text = state.text
                        binding.pleaseWaitMessage.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                    State.Loading -> {
                        binding.main.visibility = View.GONE
                        binding.pleaseWaitMessage.text = getString(R.string.wait)
                        binding.pleaseWaitMessage.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    State.Success -> {
                        binding.main.visibility = View.VISIBLE
                        binding.pleaseWaitMessage.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.userName.text = vm.name.value
                        binding.userGender.text = vm.gender.value
                        binding.userLocation.text = vm.location.value
                        binding.userEmail.text = vm.email.value
                        binding.userLogin.text = vm.login.value
                        binding.userDob.text = vm.dob.value
                        binding.userRegistered.text = vm.registered.value
                        binding.userPhone.text = vm.phone.value
                        binding.userCell.text = vm.cell.value
                        binding.userId.text = vm.id.value
                        binding.userNat.text = vm.nat.value
                        Glide.with(this@MenuDisplay)
                            .load(vm.url.value)
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.avatar)
                    }
                }
            }
        }
        vm.updateApi()
        binding.updateButton.setOnClickListener {
            vm.updateApi()
        }

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
