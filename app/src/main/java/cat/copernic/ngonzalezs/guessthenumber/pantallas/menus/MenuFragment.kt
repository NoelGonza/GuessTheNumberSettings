package cat.copernic.ngonzalezs.guessthenumber.pantallas.menus

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cat.copernic.ngonzalezs.guessthenumber.R
import cat.copernic.ngonzalezs.guessthenumber.databinding.MenuFragmentBinding

class MenuFragment  : Fragment() {

    private lateinit var binding: MenuFragmentBinding

    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs: MenuFragmentArgs by navArgs()
        val nom = safeArgs.tip

        binding.btnLvl10.setText(nom + " LVL 10")
        binding.btnLvl50.setText(nom + " LVL 50")
        binding.btnLvl100.setText(nom + " LVL 100")

    }
}