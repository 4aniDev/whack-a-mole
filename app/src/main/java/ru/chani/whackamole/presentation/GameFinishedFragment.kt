package ru.chani.whackamole.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.chani.whackamole.R
import ru.chani.whackamole.data.RepositoryImp
import ru.chani.whackamole.databinding.FragmentGameFinishedBinding
import ru.chani.whackamole.domain.entity.GameResult
import ru.chani.whackamole.domain.usecases.GetRecordOfTheBestScore

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private lateinit var viewModel: GameFinishedViewModel


    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[GameFinishedViewModel::class.java]

        binding.tvYourScoredValue.text = gameResult.score.toString()

        setOnClickListeners()
    }

    override fun onStart() {
        super.onStart()
        binding.tvBestResultValue.text = viewModel.getBestScore()
    }


    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun setOnClickListeners() {
        binding.btPlayAgain.setOnClickListener { launchGameFragment() }
        binding.btMenu.setOnClickListener { launchStartMenuFragment() }
    }

    private fun launchGameFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance())
            .commit()
    }

    private fun launchStartMenuFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, StartMenuFragment.newInstance())
            .commit()
    }


    companion object {

        private const val KEY_GAME_RESULT = "KEY_GAME_RESULT"


        fun newInstance(gameResult: GameResult) =
            GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
    }
}