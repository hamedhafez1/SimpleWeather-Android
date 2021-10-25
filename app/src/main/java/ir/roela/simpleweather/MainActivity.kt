package ir.roela.simpleweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import ir.roela.simpleweather.databinding.ActivityMainBinding
import ir.roela.simpleweathermodule.SimpleWeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowFragment.setOnClickListener {
            val count = supportFragmentManager.backStackEntryCount
            if (count > 0) {
                removeWeatherFragment()
                supportFragmentManager.popBackStack()
            } else {
                addWeatherFragment()
            }
        }

    }

    private fun addWeatherFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(R.id.fragment_container, SimpleWeatherFragment())
            .addToBackStack("weatherFragment")
            .commit()
    }

    private fun removeWeatherFragment() {
        val fragmentWeather = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragmentWeather != null) {
            supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .remove(fragmentWeather)
                .commit()
        }
    }
}