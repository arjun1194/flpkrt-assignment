package com.arjun1194.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arjun1194.myapplication.databinding.FragmentDashboardBinding
import com.arjun1194.myapplication.domain.toTimeString
import com.arjun1194.myapplication.ui.list.ChatListViewModel
import kotlinx.coroutines.launch

class ChatDetailFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel by activityViewModels<ChatListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchChatList()
            }
        }

        val id = requireArguments().getInt("id")
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {


                renderChatDetailScreen(viewModel, id)

            }
        }
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable
fun renderChatDetailScreen(
    viewModel: ChatListViewModel,
    id: Int,
) {
    val (currentMessages, _) = remember {
        mutableListOf(viewModel.getCurrentMessageList(id))
    }

    LazyColumn {
        items(currentMessages) {
            val senderIsBot = it.sender == "BOT"
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                horizontalArrangement = if (senderIsBot) Arrangement.Start else Arrangement.End
            ) {
                Row(modifier = Modifier
                    .padding(20.dp)
                    .background(color = if (senderIsBot) Color.White else Color.Blue)
                ) {
                    Column {
                        Text(it.message, color = if (senderIsBot) Color.Black else Color.White, fontSize = 16.sp)
                        Row {
                            Text(text = it.timestamp.toTimeString())
                            if (!senderIsBot) {
                                Icon( Icons.Default.Check, contentDescription = "")
                            }
                        }
                    }
                }
            }
        }
    }
}