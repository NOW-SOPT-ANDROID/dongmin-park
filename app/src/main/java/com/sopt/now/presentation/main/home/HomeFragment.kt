package com.sopt.now.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.R
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.model.Friend
import com.sopt.now.presentation.main.FriendAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { getString(R.string.binding_error) }

    private val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가자.",
        ),
        Friend(
            profileImage = R.drawable.ic_home_white_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        Friend(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가자.",
        ),
        Friend(
            profileImage = R.drawable.ic_home_white_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        Friend(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가자.",
        ),
        Friend(
            profileImage = R.drawable.ic_home_white_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendAdapter()
        binding.rvFriends.run {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
