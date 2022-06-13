package ru.chani.whackamole.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.chani.whackamole.R
import ru.chani.whackamole.databinding.FragmentGameBinding
import ru.chani.whackamole.domain.entity.GameResult

class GameFragment : Fragment() {


    private lateinit var viewModel: GameViewModel

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?:throw RuntimeException("FragmentGameBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        observeViewModel()
        setOnClickListeners()

        viewModel.startGame()
    }


    private fun observeViewModel() {
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimerValue.text = it
        }

        viewModel.currentPositionOfMole.observe(viewLifecycleOwner) {
            setAllHolesInvisible()
            setMoleVisibleInCurrentHole(it)
        }

        viewModel.currentScore.observe(viewLifecycleOwner) {
            binding.tvScoreValue.text = it
        }

        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun setOnClickListeners() {
        binding.ivHole1.setOnClickListener { viewModel.selectedHole(1) }
        binding.ivHole2.setOnClickListener { viewModel.selectedHole(2) }
        binding.ivHole3.setOnClickListener { viewModel.selectedHole(3) }
        binding.ivHole4.setOnClickListener { viewModel.selectedHole(4) }
        binding.ivHole5.setOnClickListener { viewModel.selectedHole(5) }
        binding.ivHole6.setOnClickListener { viewModel.selectedHole(6) }
        binding.ivHole7.setOnClickListener { viewModel.selectedHole(7) }
        binding.ivHole8.setOnClickListener { viewModel.selectedHole(8) }
        binding.ivHole9.setOnClickListener { viewModel.selectedHole(9) }
    }


    private fun setAllHolesInvisible() {
        binding.ivHole1.setImageResource(R.drawable.mole_invisible)
        binding.ivHole2.setImageResource(R.drawable.mole_invisible)
        binding.ivHole3.setImageResource(R.drawable.mole_invisible)
        binding.ivHole4.setImageResource(R.drawable.mole_invisible)
        binding.ivHole5.setImageResource(R.drawable.mole_invisible)
        binding.ivHole6.setImageResource(R.drawable.mole_invisible)
        binding.ivHole7.setImageResource(R.drawable.mole_invisible)
        binding.ivHole8.setImageResource(R.drawable.mole_invisible)
        binding.ivHole9.setImageResource(R.drawable.mole_invisible)
    }

    private fun setMoleVisibleInCurrentHole(currentHole: Int) {
        when(currentHole){
            1 -> binding.ivHole1.setImageResource(R.drawable.mole_visible)
            2 -> binding.ivHole2.setImageResource(R.drawable.mole_visible)
            3 -> binding.ivHole3.setImageResource(R.drawable.mole_visible)
            4 -> binding.ivHole4.setImageResource(R.drawable.mole_visible)
            5 -> binding.ivHole5.setImageResource(R.drawable.mole_visible)
            6 -> binding.ivHole6.setImageResource(R.drawable.mole_visible)
            7 -> binding.ivHole7.setImageResource(R.drawable.mole_visible)
            8 -> binding.ivHole8.setImageResource(R.drawable.mole_visible)
            9 -> binding.ivHole9.setImageResource(R.drawable.mole_visible)
        }
    }


    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .commit()
    }

    companion object {
        fun newInstance() = GameFragment()
    }
}
