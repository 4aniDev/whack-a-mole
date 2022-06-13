package ru.chani.whackamole.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.chani.whackamole.R
import ru.chani.whackamole.databinding.FragmentGameFinishedBinding
import ru.chani.whackamole.databinding.FragmentStartMenuBinding

class StartMenuFragment : Fragment() {


    private lateinit var viewModel: StartMenuViewModel


    private var _binding: FragmentStartMenuBinding? = null
    private val binding: FragmentStartMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentStartMenuBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[StartMenuViewModel::class.java]

        binding.tvBestResultValue.text = viewModel.getBestScore()

        binding.btPlay.setOnClickListener { launchGameFragment() }
    }

    private fun launchGameFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance())
            .commit()
    }

    companion object {
             fun newInstance() = StartMenuFragment()
    }
}