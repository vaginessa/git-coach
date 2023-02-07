package com.vishal2376.gitcoach.fragments

import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vishal2376.gitcoach.R
import com.vishal2376.gitcoach.adapters.GitLessonStepAdapter
import com.vishal2376.gitcoach.databinding.FragmentLessonBinding
import com.vishal2376.gitcoach.models.lesson.GitLesson

class LessonFragment : Fragment() {

    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    private val args: LessonFragmentArgs by navArgs()

    private lateinit var gitLessonStepAdapter: GitLessonStepAdapter
    private lateinit var gitLessonList: GitLesson

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //load settings
        loadSettings()

        // Inflate the layout for this fragment
        _binding = FragmentLessonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data
        val gson = Gson()
        val jsonString = requireActivity().assets.readFile("git_lessons.json")
        gitLessonList = gson.fromJson(jsonString, GitLesson::class.java)

        gitLessonStepAdapter =
            GitLessonStepAdapter(requireContext(), gitLessonList.gitLessons[args.position])

        //set recycler view
        binding.rvLessonStep.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gitLessonStepAdapter
        }
    }

    private fun loadSettings() {
        //load saved values
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val userTheme = sp.getString("user_theme", "yellow")

        //set theme
        setUserTheme(userTheme)
    }

    private fun setUserTheme(userTheme: String?) {
        when (userTheme) {
            "red" -> {
                requireActivity().setTheme(R.style.Theme_RED)
            }
            "blue" -> {
                requireActivity().setTheme(R.style.Theme_BLUE)
            }
            "green" -> {
                requireActivity().setTheme(R.style.Theme_GREEN)
            }
            else -> {
                requireActivity().setTheme(R.style.Theme_GitCoach)
            }
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}