package feliperrm.com.wordscrambler.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import feliperrm.com.wordscrambler.databinding.FragmentGameBinding
import feliperrm.com.wordscrambler.databinding.FragmentMenuBinding
import feliperrm.com.wordscrambler.utils.getViewModel

class GameFragment : Fragment() {

    private val vm by lazy { getViewModel<GameViewModel>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentGameBinding.inflate(inflater, container, false).apply {
            viewModel = vm
            lifecycleOwner = this@GameFragment.viewLifecycleOwner
        }.root


}
