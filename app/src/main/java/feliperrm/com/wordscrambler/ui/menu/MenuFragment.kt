package feliperrm.com.wordscrambler.ui.menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import feliperrm.com.wordscrambler.databinding.FragmentMenuBinding
import feliperrm.com.wordscrambler.utils.getViewModel

class MenuFragment : Fragment() {

    private val vm: MenuViewModel by lazy { getViewModel<MenuViewModel>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentMenuBinding.inflate(inflater, container, false).apply {
            viewModel = vm
            lifecycleOwner = this@MenuFragment.viewLifecycleOwner
        }.root


}
