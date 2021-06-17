package bootcamp.snt.bootcampsantandertodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bootcamp.snt.bootcampsantandertodo.network.NetworkClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}