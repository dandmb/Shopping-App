package com.dmbdan.myshop.ui.screens


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmbdan.myshop.domain.model.ProductResponseItem
import com.dmbdan.myshop.ui.state.Uistate
import com.dmbdan.myshop.ui.viewmodels.MainViewModel
import com.dmbdan.myshop.ui.theme.MyshopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyshopTheme {
                // A surface container using the 'background' color from the theme
                val viewModel: MainViewModel = hiltViewModel()
                DisplayProducts(
                    onGetProducts = { viewModel.getProducts() },
                    uistate = viewModel.uistate.value,
                    selectedItem = {
                        Toast.makeText(this,it.category,Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayProducts(
    onGetProducts: () -> Unit,
    uistate: Uistate,
    selectedItem: (ProductResponseItem) -> Unit
) {
    val productItems = uistate.products

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Outlet Shop",
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize
                        )
                    )
                },
                actions = {
                    IconButton(onClick = onGetProducts) {
                        Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)

                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (uistate.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (!uistate.error.isNullOrEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                        items(
                            items = productItems,
                            itemContent = {
                                ProductListItem(productResponseItem = it, selectedItem = selectedItem)
                            }


                        )
                    }.takeIf { productItems.isNotEmpty() }
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = null).takeIf { productItems.isEmpty() }
                    Text(text = uistate.error, textAlign = TextAlign.Center).takeIf { productItems.isEmpty() }

                }
            } else {
                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                    items(
                        items = productItems,
                        itemContent = {
                            ProductListItem(productResponseItem = it, selectedItem = selectedItem)
                        }
                    )
                }
            }
            
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyshopTheme {

    }
}