package com.example.app_sochi.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.app_sochi.ui.utils.getImageForFish
import java.io.InputStreamReader
import java.io.IOException

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishDescriptionScreen(fish: String) {
    val fishImage = getImageForFish(fish)

    // Преобразуем имя рыбы в формат файла, где пробелы заменены на нижнее подчеркивание, и все буквы в нижний регистр
    val fishHtmlFileName = "${fish.lowercase().replace(" ", "_")}.html"
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Описание рыбки") },
                navigationIcon = {
                    IconButton(onClick = { /* Navigate back action */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Показываем картинку рыбы с улучшением оформления
            Image(
                painter = painterResource(id = fishImage),
                contentDescription = "Image of $fish",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp)) // Закругленные углы для изображения
                    .shadow(4.dp, RoundedCornerShape(12.dp)) // Легкая тень для изображения
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Название рыбы с красивым стилем
            Text(
                text = fish,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Добавляем WebView для отображения HTML контента
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Заполняем оставшуюся часть экрана
                    .padding(top = 16.dp),
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient() // Обрабатываем ссылки внутри WebView
                        settings.javaScriptEnabled = true // Включаем поддержку JavaScript
                    }
                },
                update = { webView ->
                    try {
                        // Пытаемся открыть HTML-файл из папки assets
                        val inputStream = context.assets.open("html/$fishHtmlFileName")
                        val reader = InputStreamReader(inputStream)
                        val content = reader.readText()

                        // Загружаем HTML в WebView
                        webView.loadData(content, "text/html; charset=UTF-8", null)

                    } catch (e: IOException) {
                        // Если файл не найден, выводим ошибку
                        e.printStackTrace()
                        webView.loadData("<h2>Файл с описанием не найден</h2>", "text/html; charset=UTF-8", null)
                    }
                }
            )
        }
    }
}
