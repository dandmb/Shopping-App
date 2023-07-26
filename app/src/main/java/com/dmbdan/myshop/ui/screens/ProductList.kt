package com.dmbdan.myshop.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dmbdan.myshop.domain.model.ProductResponseItem


@Composable
fun ProductListItem(
    productResponseItem: ProductResponseItem,
    selectedItem: (ProductResponseItem) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedItem(productResponseItem) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemImage(productResponseItem = productResponseItem)
            Column {
                productResponseItem.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    productResponseItem.description?.let { it1 ->
                        Text(
                            text = it1,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "€${productResponseItem.price.toString()}",
                            style = TextStyle(
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                                ){
                            Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                            Text(
                                text = productResponseItem.rating?.rate.toString(),
                                style = MaterialTheme.typography.bodySmall

                            )
                        }
                    }


                }

            }
        }
    }
}
