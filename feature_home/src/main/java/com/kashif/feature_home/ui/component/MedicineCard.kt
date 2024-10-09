package com.kashif.feature_home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kashif.core.domain.model.Medicine
import com.kashif.feature_home.R

@Composable
fun MedicineCard(medicine: Medicine, onClick: () -> Unit) {
    val click = remember { Modifier.clickable { onClick() } }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(click),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.medicine_name, medicine.name),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(R.string.medicine_dose, medicine.dose),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.medicine_strength, medicine.strength),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}