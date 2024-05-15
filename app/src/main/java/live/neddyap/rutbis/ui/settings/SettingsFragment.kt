package live.neddyap.rutbis.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import live.neddyap.rutbis.R
import live.neddyap.rutbis.databinding.FragmentSettingsBinding
import live.neddyap.rutbis.databinding.SettingsItemBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val accountItemBinding = SettingsItemBinding.bind(root.findViewById(R.id.account_item))
        accountItemBinding.imageView.setImageResource(R.drawable.ic_user)
        accountItemBinding.textView.text = getString(R.string.user)

        val appSettingsItemBinding =
            SettingsItemBinding.bind(root.findViewById(R.id.app_settings_item))
        appSettingsItemBinding.imageView.setImageResource(R.drawable.ic_setting)
        appSettingsItemBinding.textView.text = getString(R.string.app_settings)

        val aboutItemBinding = SettingsItemBinding.bind(root.findViewById(R.id.about_item))
        aboutItemBinding.imageView.setImageResource(R.drawable.ic_exclamation)
        aboutItemBinding.textView.text = getString(R.string.about)

        val supportItemBinding = SettingsItemBinding.bind(root.findViewById(R.id.support_item))
        supportItemBinding.imageView.setImageResource(R.drawable.ic_support)
        supportItemBinding.textView.text = getString(R.string.support)

        val logoutItemBinding = SettingsItemBinding.bind(root.findViewById(R.id.logout_item))
        logoutItemBinding.imageView.setImageResource(R.drawable.ic_logout)
        logoutItemBinding.textView.text = getString(R.string.logout)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}