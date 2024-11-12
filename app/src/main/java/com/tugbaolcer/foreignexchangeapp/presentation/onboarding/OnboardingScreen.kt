package com.tugbaolcer.foreignexchangeapp.presentation.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.BlackTwoColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.CharcoalColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.ForeignExchangeAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    item: List<OnboardingData>,
    pagerState: PagerState
) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackTwoColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPage(item[page], pagerState)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (pagerState.currentPage < 2) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    // Son sayfada yapılacak işlem (örn. onboarding'i bitir)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            Text(text = "Let's go", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(data: OnboardingData, pagerState: PagerState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BlackTwoColor)
    ) {
        Image(
            painter = painterResource(id = data.image),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .background(color = CharcoalColor, shape = androidx.compose.foundation.shape.CircleShape)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = data.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6C63FF)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = data.description, fontSize = 16.sp, color = Color.White)
        Spacer(modifier = Modifier.height(30.dp))
        PagerIndicator(pageCount = 3, currentPage = pagerState.currentPage)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun OnBoardingPagerPreview() {
    ForeignExchangeAppTheme {
        val pagerState = rememberPagerState(
            pageCount = { 3 },
            initialPage = 0,
            initialPageOffsetFraction = 0f
        )
        OnboardingScreen(item = pages, pagerState = pagerState)
    }
}


