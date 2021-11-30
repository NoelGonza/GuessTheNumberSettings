package cat.copernic.ngonzalezs.guessthenumber.pantallas.inicio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cat.copernic.ngonzalezs.guessthenumber.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_hum = binding.btnHumano
        val btn_maq = binding.btnMaquina
        val action = MainFragmentDirections.actionMainFragmentToMenuFragment("Humano")
        val action2 = MainFragmentDirections.actionMainFragmentToMenuFragment("Maquina")

        btn_hum.setOnClickListener {
            findNavController().navigate(action)
        }
        btn_maq.setOnClickListener{
            findNavController().navigate(action2)
        }
    }

}