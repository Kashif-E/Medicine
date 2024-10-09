package com.kashif.feature_home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kashif.core.domain.model.Medicine
import com.kashif.feature_home.ui.component.MedicineCard
import java.util.Calendar

@Composable
fun HomeScreen(
    email: String,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (Medicine) -> Unit,
) {
    val medicines by viewModel.medicines.collectAsState()
    val greeting by remember { derivedStateOf { getGreetingMessage() } }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) { paddingValues ->
        Body(paddingValues, greeting, email, medicines, onNavigate)
    }

}

@Composable
private fun Body(
    paddingValues: PaddingValues,
    greeting: String,
    email: String,
    medicines: List<Medicine>,
    onNavigate: (Medicine) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "$greeting, $email", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(medicines, key = { medicines ->
                medicines.id
            }, contentType = { medicines ->
                medicines::class.java.name
            }) { medicine ->
                MedicineCard(medicine) {
                    onNavigate(medicine)
                }
            }
        }
    }
}


fun getGreetingMessage(): String {
    val calendar = Calendar.getInstance()
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

    return when (currentHour) {
        in 5..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
}
