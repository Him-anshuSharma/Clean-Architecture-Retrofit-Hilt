package com.example.news.presentation.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.domain.model.Article
import com.example.news.utils.Resource

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsHomeScreen(modifier: Modifier, viewModel: NewsViewModel = hiltViewModel()) {
    val articles by viewModel.articles.collectAsState()
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (articles) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Error -> {
                Text(articles.message.toString())
            }

            is Resource.Success -> {
                Text("${articles.data!!.size} Articles Loaded")
                Spacer(Modifier.size(20.dp))
                LazyColumn {
                    items(articles.data!!.size) {
                        NewsItem(articles.data!![it])
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsItem(article: Article) {
    //create composable here
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {

            // Content
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Title
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Published Date
                Text(
                    text = article.publishedAt.toLocalDate().toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))


                // Description
                if (article.description.isNotBlank()) {
                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Optional: Read More / Source (if you want to show it explicitly)
                // Text(
                //     text = "Read more",
                //     style = MaterialTheme.typography.labelLarge,
                //     color = MaterialTheme.colorScheme.primary,
                //     modifier = Modifier.align(Alignment.End)
                // )
            }
        }
    }
}